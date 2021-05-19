
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class FileHandler {
    // Reads a matrix of features and a vector of class labels and outputs an Observation ArrayList.
    public static ArrayList<Point> read(String labelPath){
    
        File labelFile = new File(labelPath);
        BufferedReader reader = null;
        ArrayList<Point> obsList = new ArrayList<>();
           
        // Reads the class label file. 
        try {
            String text = null;
            reader = new BufferedReader(new FileReader(labelFile));

            while ((text = reader.readLine()) != null) {
                // Sets the class label of the Observation object corresponding to the number on that line.
                Point p = new Point();
                String[] split = text.split(" ");
            
                p.setX(Double.parseDouble(split[0]));
                p.setY(Double.parseDouble(split[1]));
                p.setClassLabel(split[2]);
                obsList.add(p);
            }

            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        
       
        
        return obsList;
    }
}
