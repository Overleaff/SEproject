import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class SE {
    public static void main (String args[]){
        try {
            // read data
            DataSet data = new DataSet("sample.csv");

            // remove prior classification attr if it exists (input any irrelevant attributes)
            data.removeAttr("Class");

            // cluster
            KMeans cluster = new KMeans();
            cluster.kmeans(data, 2);



            // output into a csv
            data.createCsvOutput("sampleout.csv");

           
        } catch (IOException e){
            e.printStackTrace();
        } 
    }
} 

