package utils.connectionpool;

import config.DataSourceConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


//实现类
public class ConnectionPool implements MyConnectionPool {

    private int connectionCount = 0;
    //Vector:线程安全
    //存放空闲连接
    Vector<Connection> connectionsFree = new Vector<>();
    //存放正在使用的连接,使用ConnectionEntity包裹,存有连接以及其被调用的时刻
    Vector<ConnectionEntity> connectionsUsed = new Vector<>();

    public ConnectionPool()
            throws SQLException, ClassNotFoundException {
        for (int i = 0; i < Integer.parseInt(DataSourceConfig.getInitSize()); i++) {
            connectionsFree.add(createConnection());
        }
        System.out.println("Pool initialized, created " + connectionsFree.size() + " connections");
        //开启健康检查,检测是否有连接超时的操作
        if (Boolean.parseBoolean(DataSourceConfig.getHealth())) {
            checkConnectionTimeout();
        }
    }

    private void checkConnectionTimeout() {
        Worker worker = new Worker();
        //worker对象在延迟两秒后执行其内的run方法, 并且每两秒执行一次
        new Timer().schedule(worker,
                Long.parseLong(DataSourceConfig.getDelay()),
                Long.parseLong(DataSourceConfig.getPeriod()));
    }

    //任务类
    class Worker extends TimerTask {

        @Override
        public void run() {
            System.out.println("Time Check");
            //遍历正在使用的连接池,检查是否超时
            for (ConnectionEntity connectionEntity : connectionsUsed) {
                Connection connection = connectionEntity.getConnection();
                //获取连接开始使用的时间
                Long useStartTime = connectionEntity.getUseStartTime();
                if (System.currentTimeMillis() - useStartTime > Long.parseLong(DataSourceConfig.getTimeout())) {
                    //超时
                    //直接关闭,移除,总数自减
                    connectionsUsed.remove(connectionEntity);
                    connectionCount--;
                    System.out.println(Thread.currentThread().getName()
                            + "connected for too long:"
                            + connection
                            + ",abandoned connection, empty connection:"
                            + connectionsFree.size()
                            + ",now using connections:" + connectionsUsed.size());
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public synchronized Connection createConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(DataSourceConfig.getDriver());
        Connection connection = DriverManager.getConnection(
                DataSourceConfig.getUrl(),
                DataSourceConfig.getUsername(),
                DataSourceConfig.getPassword());
        connectionCount++;
        return connection;
    }

    @Override
    public synchronized Connection getConnection()
            throws SQLException, ClassNotFoundException, InterruptedException {
        Connection connection;
        //判断是否有空闲连接
        if (!connectionsFree.isEmpty()) {
            //还有空余连接
            connection = connectionsFree.get(0);
            //从空闲池移除
            connectionsFree.remove(0);
            //包裹成ConnectionEntity对象,封装调用连接的时间以及连接
            ConnectionEntity connectionEntity = new ConnectionEntity(connection, System.currentTimeMillis());
            //加入正在使用的连接池
            connectionsUsed.add(connectionEntity);
            System.out.println("get one connection:" + connection + ",now empty connection:" + connectionsFree.size());
            return connection;
        } else {
            //没有空闲的
            //判断当前总连接数是否大于最大数
            if (connectionCount < Integer.parseInt(DataSourceConfig.getMaxSize())) {
                //总连接数没到最大数
                connection = createConnection();
                ConnectionEntity connectionEntity = new ConnectionEntity(connection,
                        System.currentTimeMillis());
                //创建完,直接放进正在使用的连接池
                connectionsUsed.add(connectionEntity);
                System.out.println("no empty connection, now create new connection:"
                        + connection + ",total connections:"
                        + connectionCount);
                return connection;
            } else {
                //总链接数大于最大数
                System.out.println("no empty connection! waiting for new connection:");
                this.wait(Integer.parseInt(DataSourceConfig.getWaitTime()));
                //递归,再次尝试
                connection = getConnection();
            }
        }
        return connection;
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        //寻找连接对应的包裹类,将其remove
        for (ConnectionEntity connectionEntity : connectionsUsed) {
            if (connectionEntity.getConnection().equals(connection)) {
                connectionsUsed.remove(connectionEntity);
                break;
            }
        }
        //唤醒正在等待
        notifyAll();
        //放入空闲连接池
        connectionsFree.add(connection);
        System.out.println("one connection finished, now released connection:"
                + connectionsFree.size() + "using connections:" + connectionsUsed.size()
                );
    }
}
