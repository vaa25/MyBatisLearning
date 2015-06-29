package com.vaa25.postgis;

import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 * @author Alexander Vlasov
 */
public class Points {
    private int id;
    private Point point;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
