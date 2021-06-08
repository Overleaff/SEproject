package se.project.clustering.knn;

public class Neighbour implements Comparable<Neighbour> {
    public double distance;
    

	public int clusterNo;

    public Neighbour(double distance,int clusterNo){
        this.distance = distance;
        this.clusterNo=clusterNo;
        
    }

    // Neighbours are compared based on distance.
    public int compareTo(Neighbour other) {
        return Double.compare(this.distance, other.distance);
    }

    public String toString(){
        String out = "Distance: " + distance + ", class label: " + clusterNo;
        return out;
    }
    
    public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
