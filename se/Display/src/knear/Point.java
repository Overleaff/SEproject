


// Represents an observation vector with a class label and a list of features with corresponding values.  
public class Point implements Comparable<Point> {
    private Double x,y;
    private String classLabel;
    private Double distance;
    private Integer k;
    // Mutators and accessors.
    
    Point(){
        this.x =  0.0;
        this.y = 0.0;
    }

 



    public void setClassLabel(String classLabel) { this.classLabel = classLabel; }
    public String getClassLabel() { return classLabel; }

    public void setDistance(Double distance) { this.distance = distance; }
    public Double getDistance(){ return distance; }

    public void setX(Double x) { this.x = x; }
    public Double getX() { return x; }

    public void setY(Double y) { this.y = y; }
    public Double getY() { return y; }
    
    public void setK(Integer k) { this.k = k; }
    public Integer getK() { return k; }
   
  
    public int compareTo(Point other) {
        return Double.compare(this.distance, other.distance);
    }
}

/*
ArrayList<Student> arraylist = new ArrayList<Student>();
Collections.sort(arraylist); */