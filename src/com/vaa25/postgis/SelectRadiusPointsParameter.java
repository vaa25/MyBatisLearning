package com.vaa25.postgis;

import org.postgis.Point;

/**
 * @author Alexander Vlasov
 */
public class SelectRadiusPointsParameter {
    private Point center;
    private double radius;
    public SelectRadiusPointsParameter(){};
    public SelectRadiusPointsParameter(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public String getCenter() {
        return center.toString();
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
