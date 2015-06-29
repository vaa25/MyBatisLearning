package com.vaa25.postgis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alexander Vlasov
 */
@MappedTypes(org.postgis.Point.class)
public class PointTypeHandler extends BaseTypeHandler<Point> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Point parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i,parameter);
    }

    @Override
    public Point getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return (Point)((PGgeometry)rs.getObject(columnName)).getGeometry();
    }

    @Override
    public Point getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return (Point)((PGgeometry)rs.getObject(columnIndex)).getGeometry();
    }

    @Override
    public Point getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return (Point)((PGgeometry)cs.getObject(columnIndex)).getGeometry();
    }
}