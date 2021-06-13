package se.project.clustering.kmeans;

import se.project.components.Cluster;
import se.project.components.Point;
import java.util.*;

public class KMeansClustering extends Cluster {
    ArrayList<Point> Kmeans = new ArrayList<Point>();
    int clusters;
    ArrayList<Point> centroid = new ArrayList<Point>();
    
    public KMeansClustering(){
       
    }

    public int getClusters() {
        return clusters;
    }

    public KMeansClustering(ArrayList<Point> tmp, int K){
        this.Kmeans = tmp;
        this.clusters = K;
    }

    public void init(){
        for (int i = 0; i < this.clusters; i++) {
            this.centroid.add(this.Kmeans.get(i));
        }
    }

    public ArrayList<Point> updateCentroid(){
        int i;
        for (i = 0; i < clusters; i++){
            int count = 0;
            double tmpX = 0;
            double tmpY = 0;
            for (int j = 0; j < Kmeans.size(); j++){
                if (Kmeans.get(j).getClusterNo() == i){
                    count ++;
                    tmpX += Kmeans.get(j).getX();
                    tmpY += Kmeans.get(j).getY();
                }
            }
            centroid.set(i, new Point(tmpX/count, tmpY/count));
            centroid.get(i).updateCluster(i);
        }
        return centroid;
    }

    public ArrayList<Point> step(){
        for (int i = 0; i  < Kmeans.size(); i ++){
            double ref = this.Kmeans.get(i).
            calculateDistance(centroid.get(0));
            this.Kmeans.get(i).updateCluster(0);

            for (int j = 1; j < clusters; j++){
                double tmp = this.centroid.get(j).
                        calculateDistance(this.Kmeans.get(i));
                if (tmp < ref){
                    this.Kmeans.get(i).updateCluster(j);
                    ref = tmp;
                }
            }
        }
        return this.Kmeans;
    }

    public boolean compareCentroid(ArrayList<Point> tmp1, ArrayList<Point> tmp2){
        for (int i = 0; i < this.clusters; i++){
            if (tmp1.get(i).getX() != tmp2.get(i).getX()) return true;
            if (tmp1.get(i).getY() != tmp2.get(i).getY()) return true;
        }
        return false;
    }

    public ArrayList<Point> result(){
        this.init();
        
        ArrayList<Point> tmp = new ArrayList<Point>();
        for (int i = 0; i < this.clusters; i++){
            tmp.add(new Point(0.0, 0.0));
        }
        
        int loop = 0;
        while (compareCentroid(tmp, this.centroid)){
            loop ++;
            for (int i = 0; i < this.clusters; i++){
                tmp.set(i,new Point(this.centroid.get(i).getX(),this.centroid.get(i).getY()));
            }
            step();
            System.out.println();
            showPoint();
            updateCentroid();
            if (loop == 100000) return this.Kmeans;
        }
        
        return this.Kmeans;
    }

    public void showPoint(){
        for (int i = 0; i < this.Kmeans.size(); i++) {
            System.out.println(this.Kmeans.get(i).getX() + " " + this.Kmeans.get(i).getY() + " " + this.Kmeans.get(i).getClusterNo());
        }
    }

    public static void main (String[] args){
        Cluster abc = new KMeansClustering();
        KMeansClustering testing = new KMeansClustering(abc.initPoint(), 3);
        testing.result();
        testing.showPoint();
    }
}

