package se.project.clustering;

import se.project.components.Point;

import java.util.ArrayList;

public interface Algorithm {
    public abstract ArrayList<Point> step();

    public abstract ArrayList<Point> result();
}