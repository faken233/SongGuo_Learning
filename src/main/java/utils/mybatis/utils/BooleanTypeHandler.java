package utils.mybatis.utils;

import utils.mybatis.iface.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanTypeHandler implements TypeHandler<Boolean> {
    @Override
    public void setParam(PreparedStatement statement, int index, Boolean value) throws SQLException {
        statement.setBoolean(index, value);
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getBoolean(columnName);
    }
}
