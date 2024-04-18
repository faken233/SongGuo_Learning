package utils.mybatis.utils;

import utils.mybatis.iface.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TimestampTypeHandler implements TypeHandler<Timestamp> {
    @Override
    public void setParam(PreparedStatement statement, int index, Timestamp value) throws SQLException {
        statement.setTimestamp(index, value);
    }

    @Override
    public Timestamp getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getTimestamp(columnName);
    }
}
