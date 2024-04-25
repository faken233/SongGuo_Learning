package utils.mybatis.utils;

import utils.mybatis.iface.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FloatTypeHandler implements TypeHandler<Float> {
    @Override
    public void setParam(PreparedStatement statement, int index, Float value) throws SQLException {
        statement.setFloat(index, value);
    }

    @Override
    public Float getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getFloat(columnName);
    }
}
