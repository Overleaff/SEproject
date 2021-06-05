package se.project.clustering.meanshift;

import se.project.components.Cluster;
import se.project.components.Point;

import java.util.ArrayList;

public class MeanShiftClustering extends Cluster {
    private Point point;

    public MeanShiftClustering(Point point) {
        this.point = point;
    }

    @Override
    public ArrayList<Point> finalResult() {
        return null;
    }

    @Override
    public ArrayList<Point> step() {
        return null;
    }

    private double distance(Point source, Point des) {
        double squareX = Math.pow(des.getX() - source.getY(), 2); //x^2
        double squareY = Math.pow(des.getY() - source.getX(), 2); //y^2
        return Math.sqrt(squareX + squareY);
    }

    private double kernel(double distance, int bandwidth) {
        double squareDistance = Math.pow(distance, 2);
        double squareBandwidth = Math.pow(bandwidth, 2);
        return Math.pow(Math.E, -0.5 * (squareDistance / squareBandwidth));
    }

    public ArrayList<Point> meanShiftClustering(Point point, int bandwidth) {
        return null;
    }

    public Point shift() {
        return null;
    }
}
