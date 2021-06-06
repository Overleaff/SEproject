package se.project.components;

import java.util.ArrayList;
import java.util.Random;

public class Cluster {
    private ArrayList<Point> listPoint = new ArrayList<Point>();
    private final int maxNumPoint = 10;

    public ArrayList<Point> getPoint() {
        return listPoint;
    }

    public ArrayList<Point> initPoint() {
        Random rand = new Random(); //instance of random class
        for (int i = 0; i < maxNumPoint; i++) {
            double upperbound = 10; //generate random values from 0-24
            double x = rand.nextDouble() * upperbound;
            double y = rand.nextDouble() * upperbound;
            Point tmp = new Point(x, y);
            this.listPoint.add(tmp);
        }
        return listPoint;
    }

    public void showAllPoint() {
        for (int i = 0; i < maxNumPoint; i++) {
            System.out.println(this.listPoint.get(i).getX() + " " + listPoint.get(i).getY() + " " + this.listPoint.get(i).getClusterNo());
        }
    }

}
