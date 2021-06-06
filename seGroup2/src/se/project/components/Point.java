package se.project.components;

import java.lang.Math;

public class Point {

    private double x;

    private double y;

    public int clusterNo;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void updateCluster(int k){
        this.clusterNo = k;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getClusterNo() {
        return clusterNo;
    }


    public double calculateDistance(Point point) {
        double Ox = this.x - point.getX();
        double Oy = this.y - point.getY();
        return Math.sqrt(Math.abs( Ox * Ox + Oy * Oy ));
    }

}
