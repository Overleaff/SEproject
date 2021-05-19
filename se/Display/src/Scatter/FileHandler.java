package Scatter;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileHandler {
  ArrayList<ArrayList<Double>> list = new ArrayList<>(100);
    public static void main(String[] args) {
        FileHandler s = new FileHandler();
        s.read();
    }

    public void read(){
        try {
            File myObj = new File("tu.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
            
              String data = myReader.nextLine();
              String[] s = data.split(" ");
              for(String str: s){
                System.out.println(str);
              
              }
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        }
}
