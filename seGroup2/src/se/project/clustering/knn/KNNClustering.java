package se.project.clustering.knn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

import se.project.components.Cluster;
import se.project.components.Point;
public class KNNClustering extends Cluster{
	// Performs leave-one-out cross validation across the entire dataset for each value of K from 1-10 for both weighted and unweighted classifiers. 
    // Prints the percentage accuracy achieved with each configuration. 
    // NB: takes several minutes to run.
	public KNNClustering() {
		
	}
	 //init when user not enter anything
	 public  ArrayList<Point> initPoint() {
	        Random rand = new Random(); //instance of random class

	        for (int i = 0; i < maxNumPoint; i++) {
	            //generate random values from 0-24
	        	double x,y;
	        	 Point tmp ;
	         if(i<maxNumPoint/2) {
	            x = 5+rand.nextDouble()*10;     //rand 5-15
	            y = 5+rand.nextDouble()*10;
	            tmp = new Point(x,y);
	            tmp.updateCluster(1);

	            this.listPoint.add(tmp);
	         }else {
	        	  x = 10+rand.nextDouble() *10;     //rand 10-20
		            y = 10+rand.nextDouble() *10;
		            tmp = new Point(x,y);
		            tmp.updateCluster(2);
		            this.listPoint.add(tmp);
	         }
	           
	            
	         
	            
	            
	            
	           
	        }
	        return listPoint;
	  }
   
    
    /* 
     * Takes a Observation of unknown class and gives it a class label based on the votes of its k-nearest neighbours.
     * Parameters:
     *  - test: a Observation object of unknown class
     *  - train: an ArrayList of Observations of known class
     *  - K: the number of neighbours which will vote on the class label
     *  - distanceMeasure: the method by which neighbour distances will be calculated
     *  - weighted: true if neighbour votes are to be weighted based on closeness
     *  */
    public static Point classify(Point test, ArrayList<Point> train, int K){
        // Calculates the neighbour distances between the test example and each training example. 
        Neighbour[] neighbours = new Neighbour[train.size()];
        for (int i = 0; i < neighbours.length; i++){
          
                neighbours[i] = new Neighbour(Distance.getEuclid(train.get(i),test),train.get(i).getClusterNo());
                
        }
        
                                               // Sorts the array of neighbours by distance.
        Arrays.sort(neighbours);
        
        // Calculates the votes of the K nearest neighbours, unweighted or weighted. 
        int decision = 0 ;
    
            LinkedHashMap<Integer, Integer> votes = new LinkedHashMap<Integer, Integer>();
            for (int i = 0; i < K; i++){ 
                // Gets the label of the ith nearest neighbour. 
                int label = neighbours[i].clusterNo;
                
                // Increments the vote for that neighbour's class if already in the list. 
                if (votes.containsKey(label))
                    votes.put(label, votes.get(label) + 1);
                // Adds a vote for that neighbour's class if it is not in the list. 
                else
                    votes.put(label, 1);
            }
            
            // Sets the decision as the label with the greatest number of votes.
            double maxVote = 0;
            for (Entry<Integer, Integer> vote : votes.entrySet()){
                if (vote.getValue() > maxVote){
                    decision = vote.getKey();
                    maxVote = vote.getValue();
                }
            }
        // Sets the test example's label to that label.
        test.updateCluster(decision);
        return test;
    }
    
    
 

	
	public  ArrayList<Point> result(ArrayList<Point> test, ArrayList<Point> train, int K) {
		
	   for(Point point:test) {
		   point = KNNClustering.classify(point,train,K);
	   }
	    return test;
	}

    
	public  ArrayList<Point> step(Point test, ArrayList<Point> train, int K){
	  ArrayList<Point> neigh = new ArrayList<>();
	  Neighbour[] neighbours = new Neighbour[train.size()];
      for (int i = 0; i < neighbours.length; i++){
        
              neighbours[i] = new Neighbour(Distance.getEuclid(train.get(i),test),train.get(i).getClusterNo());
              
      }
                                    // Sorts the array of neighbours by distance.
      Arrays.sort(neighbours);
  
    	  for(int j =0;j<K;j++) {
    		  double distance = neighbours[j].getDistance();
    		  for (int i = 0; i < neighbours.length; i++){
    		    	if( distance  == Distance.getEuclid(train.get(i),test)) {
    		    		neigh.add(train.get(i));
    		    	}
    	  }
      }
      test = KNNClustering.classify(test,train,K);
	  return neigh;
	}
	
	
	
	
}
