package com.vaa25.postgis;

import org.postgis.*;
import org.postgresql.PGConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alexander Vlasov
 */
public class JavaGIS {

    public static void main(String[] args) {

        Connection conn;

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgis";
            conn = DriverManager.getConnection(url, "postgres", "1234");
            ((PGConnection)conn).addDataType("geometry", Class.forName("org.postgis.PGgeometry"));
            ((PGConnection)conn).addDataType("box3d",Class.forName("org.postgis.PGbox3d"));
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select point,id, description from points");
            while( r.next() ) {
                PGgeometry geom = (PGgeometry)r.getObject(1);
                int id = r.getInt(2);
                String description=r.getString(3);
                System.out.println("Row " + id + ": "+description);
                System.out.println(geom.toString());
                if( geom.getGeoType() == Geometry.POLYGON ) {
                    Polygon pl = (Polygon)geom.getGeometry();
                    for( int i = 0; i < pl.numRings(); i++) {
                        LinearRing rng = pl.getRing(i);
                        System.out.println("Ring: " + i);
                        for( int p = 0; p < rng.numPoints(); p++ ) {
                            Point pt = rng.getPoint(p);
                            System.out.println("Point: " + p);
                            System.out.println(pt.toString());
                        }
                    }
                }System.out.println();
                if( geom.getGeoType() == Geometry.POINT ) {
                    Point pl = (Point)geom.getGeometry();
                    for( int p = 0; p < pl.numPoints(); p++ ) {
                        Point pt = pl.getPoint(p);
                        System.out.println("Point: " + p);
                        System.out.println(pt.toString());
                    }

                }
                System.out.println();
            }
            s.close();
            conn.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}