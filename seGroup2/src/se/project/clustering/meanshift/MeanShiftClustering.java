package se.project.clustering.meanshift;


public class MeanShiftClustering extends Cluster implements Algorithm {
    private Point point;

    public MeanShiftClustering(Point point) {
        this.point = point;
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
            shiftingDistance = Math.sqrt(Math.pow(shiftX, 2) + Math.pow(shiftY, 2));
            point.setX(shiftX);
            point.setY(shiftY);
        } while (shiftingDistance > 0);
        return pointList;
    }

    @Override
    public ArrayList<Point> step() {
        return null;
    }

    @Override
    public ArrayList<Point> finalResult() {
        return null;
    }
}
