package se.project.clustering;
import se.project.components.Cluster;
import se.project.components.Point;
import java.util.*;

public interface algorithm {
    public abstract ArrayList<Point> step();
    public abstract ArrayList<Point> finalResult();
}