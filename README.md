# SongGuo_Learning

> 松果在线学习平台

## 功能列举

### 学生

- **登陆注册** 可以进行登陆注册
- **个人信息** 个人信息可以修改
- **加入课程** 浏览可选择的课程并且加入
- **学习课程** 可以选择章节进行学习并且做题
- **查看学习情况** 可以查看自己的章节学习记录
- **讨论** 可以在课程评论区进行评论

### 教师

- **创建课程** 可以创建新的课程
- **课程管理** 可以设置课程的开课时间,结课时间,限制人数以及描述.
- **个人信息** 可以修改个人信息
- **管理课程** 
  - 对课程的章节进行增删
  - 对课程章节的内容以及题目进行增删
  - 可以删除课程的评论区的不友好评论
- **查看报名学生** 可以查看参加课程的学生名单
- **讨论** 可以在课程评论区进行评论

---

## 项目亮点

### 服务器

1. 使用了**自制数据库连接池, 自制mybatis**, 极大简化dao层开发
2. 自制框架避免了SQL注入
3. 使用**常量类** 
4. 使用slf4j日志框架记录平台活动
5. 验证登陆
6. 密码加密存储

### 前端

1. 使用Vue+ElementUI搭配vue-cli脚手架搭建
2. 对表单输入采取**正则表达式校验**
3. 前端页面布局清晰明了, 结构分明，交互体验有保证
4. 不传输明文密码, 加密传输

---

## 须知

分支下有三个文件夹

 `Docs&Others` 存放文档还有其他文件

`SongGuo_Learning` 存放源码以及war包

`nginx_SongGuo_Learning` 下存放前端项目

其中, 前端项目只需启动`nginx.exe` 之后, 浏览器输入`localhost` 即可访问.

后台各个依赖引入以及版本如下:
    <dependency>
<!--      单元测试-->
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.2</version>
      <scope>test</scope>
    </dependency>

<!--    数据库驱动-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.29</version>
    </dependency>

<!--    base_servlet-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>

<!--    json序列化-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>2.0.9.graal</version>
    </dependency>

<!--    日志框架-->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.30</version>
    </dependency>
      <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-simple</artifactId>
          <version>1.7.30</version>
      </dependency>
<!--      jwt-->
      <dependency>
          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt</artifactId>
          <version>0.9.1</version>
      </dependency>

jdk版本为1.8

Tomcat版本为9.0.88, 其服务器访问路径设置为`http://localhost:8080/SongGuo_Learning/`

