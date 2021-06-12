package se.project.clustering.meanshift;

import se.project.components.Cluster;
import se.project.components.Point;

import java.util.ArrayList;
import java.util.Random;

public class MeanShiftClustering extends Cluster {
    protected Point seedPoint;
    protected int bandwidth;

    public MeanShiftClustering(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public void setSeedPoint(Point seedPoint) {
        this.seedPoint = seedPoint;
    }

    public Point getSeedPoint() {
        return seedPoint;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public ArrayList<Point> initPoint() {
        Random rand = new Random();
        for (int i = 0; i < MAX_NUM_POINT; i++) {
            double upperbound = 10;
            double x = rand.nextDouble() * upperbound;
            double y = rand.nextDouble() * upperbound;
            Point tmp = new Point(x, y);
            tmp.updateCluster(3);
            this.listPoint.add(tmp);
        }
        return listPoint;
    }

    public ArrayList<Point> step() {
        return null;
    }


    private double distance(Point source, Point des) {
        double squareX = Math.pow(des.getX() - source.getX(), 2); //x^2
        double squareY = Math.pow(des.getY() - source.getY(), 2); //y^2
        return Math.sqrt(squareX + squareY);
    }

    private double kernel(double distance, int bandwidth) {
        double squareDistance = Math.pow(distance, 2);
        double squareBandwidth = Math.pow(bandwidth, 2);
        return Math.pow(Math.E, -0.5 * (squareDistance / squareBandwidth));
    }

    public Point createSeedPoint(Point p) {
        for (Point point : this.listPoint) {
            if (point.getX() == p.getX() && point.getY() == p.getY()) {
                this.seedPoint.setX(point.getX());
                this.seedPoint.setY(point.getY());
            }
        }
        return this.seedPoint;
    }

    //parameter point is an initial seed.
    public ArrayList<Point> meanShiftClustering(Point point, ArrayList<Point> pointList, int bandwidth) {
        double shiftingDistance = 0;
        do {
            double shiftX = 0;
            double shiftY = 0;
            double scaleFactor = 0;
            for (Point p : pointList) {
                double dist = distance(point, p);
                if (dist <= bandwidth) {
                    double weight = kernel(dist, bandwidth);
                    if (weight > 0) {
                        shiftX += p.getX() * weight;
                        shiftY += p.getY() * weight;
                        scaleFactor += weight;
                    }
                }
            }
            shiftX = shiftX / scaleFactor;
            shiftY = shiftY / scaleFactor;

            shiftingDistance = Math.sqrt(Math.pow(shiftX - point.getX(), 2) + Math.pow(shiftY - point.getY(), 2));
            point.setX(shiftX);
            point.setY(shiftY);

        } while (shiftingDistance > 0.00005);
        return pointList;
    }

    @Override
    public ArrayList<Point> result() {
        return meanShiftClustering(seedPoint, this.listPoint, bandwidth);
    }

    public static void main(String[] args) {
        MeanShiftClustering meanShift = new MeanShiftClustering(3);
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1,2));
        points.add(new Point(1,3.6));
        points.add(new Point(3.4,5));
        points.add(new Point(1,7.9));
        points.add(new Point(9,2));
        points.add(new Point(4.5,2.2));
        points.add(new Point(1.6,6.2));
        points.add(new Point(5.1,2.7));
        points.add(new Point(0.8,8.2));
        points.add(new Point(7,4.2));
        meanShift.setSeedPoint(points.get(0));
        ArrayList<Point> meanShiftPoints = meanShift.meanShiftClustering(meanShift.getSeedPoint(), points, meanShift.getBandwidth());
        for (int i = 0; i < meanShiftPoints.size(); i++) {
            System.out.println(meanShiftPoints.get(i).getX() + " " + meanShiftPoints.get(i).getY());
        }
    }
}
