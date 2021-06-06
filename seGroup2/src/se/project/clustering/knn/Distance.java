package se.project.clustering.knn;

import se.project.components.Point;

public class Distance {
    public static double getEuclid(Point a, Point b){
        double squaredDistance = 0; 

        squaredDistance = (a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY());
 
        return Math.sqrt(squaredDistance);
    }

}
