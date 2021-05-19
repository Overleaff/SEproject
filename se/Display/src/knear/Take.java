
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import java.io.File;  // Import the File class // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;// Import the S

public class Take extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        ArrayList<Point> inputPoints = FileHandler.read("tu.txt");
        ArrayList<String> className = new ArrayList<>();
        
        for(int i =0; i<inputPoints.size();i++){
            int flag = 0;
            for(int j=0;j<i;j++){
                if(inputPoints.get(i).getClassLabel().contains(inputPoints.get(j).getClassLabel())){
                flag = 1;   
                break;}
            }
            if(flag == 0){
            className.add(inputPoints.get(i).getClassLabel());
            }
        }
          // number of class and class label name
        
        for(String str: className){
            System.out.println(str);
        }
        
         
       
        primaryStage.setTitle("BarChart Experiments");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        ScatterChart<Number,Number> scatterChart = new ScatterChart<Number,Number>(xAxis, yAxis);
        
       
        ArrayList<XYChart.Series> arrSeries = new ArrayList<>();
        for(String str:className){
            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(str);

            for(Point point:inputPoints){
                if(str.contains(point.getClassLabel())){
                     dataSeries1.getData().add(new XYChart.Data(point.getX(),point.getY()));
                }
            } 
            scatterChart.getData().add(dataSeries1);
            arrSeries.add(dataSeries1);
        }
          
     
        
         // TEST POINT
         Point test = new Point();
         test.setX(10.0);
         test.setY(18.0);
 
         Point test2 = new Point();
         test2.setX(25.5);
         test2.setY(26.0);
         XYChart.Series dataSeries = new XYChart.Series();
         dataSeries.getData().add(new XYChart.Data(test.getX(),test.getY()));
         dataSeries.getData().add(new XYChart.Data(test2.getX(),test2.getY()));

         ArrayList<Point> testPoint = new ArrayList<>();
         testPoint.add(test);
         testPoint.add(test2);
 
 
         // test classify
         test = classify(test, inputPoints, 3);
         System.out.println("test:"+test.getClassLabel());
         test.setK(3); 

         test2 = classify(test2, inputPoints, 1);
         test2.setK(3);
         System.out.println("test2:"+test2.getClassLabel());
          dataSeries.setName("Unknown");
         scatterChart.getData().addAll(dataSeries);
          // scatterChart.getData().remove(dataSeries);

          //co get Name


        VBox vbox = new VBox(scatterChart);
        Button visual = new Button ("Visual");
        Button next = new Button ("Next");
        vbox.getChildren().add(visual);
        vbox.getChildren().add(next);
        
        next.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                
                LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis, yAxis);
               // add point to line chart
               // draw line by 1 step
                for(Point p:testPoint){
                  
                    for(Point point:inputPoints){
                     
                      
                    XYChart.Series dataS = new XYChart.Series();
                    dataS.getData().add(new XYChart.Data(p.getX(),p.getY()));
                    dataS.getData().add(new XYChart.Data(point.getX(),point.getY()));
                  
                    lineChart.getData().add(dataS);
                    } 
                   
                }
               
                VBox vbox3 = new VBox(lineChart);
                Button next3 = new Button("Next");
                vbox3.getChildren().add(next3);
                vbox3.getChildren().add(visual);
                Scene scene3 = new Scene(vbox3, 800, 800);
                primaryStage.setScene(scene3);

                
                
                next3.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent event){
                        
                        LineChart<Number,Number> lineChar = new LineChart<Number,Number>(xAxis, yAxis);
                        // add point to line chart
                        // draw line by 1 step
                         for(Point p:testPoint){
                           
                            for(Point p1:inputPoints){
                             p1.setDistance(Distance.getEuclid(p1, p));      }

                             Collections.sort(inputPoints);



                             
                               for(int i=0;i<p.getK();i++){
                               
                             XYChart.Series dataS = new XYChart.Series();
                             dataS.getData().add(new XYChart.Data(p.getX(),p.getY()));
                             dataS.getData().add(new XYChart.Data(inputPoints.get(i).getX(),inputPoints.get(i).getY()));
                             dataS.setName(inputPoints.get(i).getClassLabel());
                             lineChar.getData().add(dataS);
                               }
                             
                            
                         }
                        
                         VBox vbox4 = new VBox(lineChar);
                         vbox4.getChildren().add(visual);
                         Scene scene4 = new Scene(vbox4, 800, 800);
                         primaryStage.setScene(scene4);
                    
                    }
                    });
            }
        }); 
         
        // mode for visual immediately
        visual.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                scatterChart.getData().remove(dataSeries);
                
                for(Point p:testPoint){
                  for(XYChart.Series s:arrSeries){
                    if(p.getClassLabel().contains(s.getName())){
                        s.getData().add(new XYChart.Data(p.getX(),p.getY()));
                    }
                } 
            }
                
                VBox vbox2 = new VBox(scatterChart);
                Scene scene2 = new Scene(vbox2, 800, 800);
                primaryStage.setScene(scene2);
            }
        });
    
        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(1200);
        primaryStage.show();
    }


     /* 
     * Takes a Point of unknown class and gives it a class label based on the votes of its k-nearest neighbours.
     * Parameters:
     *  - test: a Point object of unknown class
     *  - train: an ArrayList of Points of known class
     *  - K: the number of neighbours which will vote on the class label
     *  - distanceMeasure: the method by which neighbour distances will be calculated
     *  - weighted: true if neighbour votes are to be weighted based on closeness
     *  */
    public  Point classify (Point test, ArrayList <Point> train, int K){
        // Calculates the neighbour distances between the test example and each training example. 
        
    
       for(Point p:train){
           p.setDistance(Distance.getEuclid(test, p));      }
        
        // Sorts the array of neighbours by distance.
        Collections.sort(train);
        String decision = " ";
        // Calculates the votes of the K nearest neighbours, unweighted or weighted. 
        LinkedHashMap<String, Double> votes = new LinkedHashMap<String, Double>();
            for (int i = 0; i < K; i++){ 
                // Gets the label of the ith nearest neighbour. 
                String label = train.get(i).getClassLabel();

                // Increments the vote for that neighbour's class if already in the list. 
                if (votes.containsKey(label))
                    votes.put(label, votes.get(label) + 1);
                // Adds a vote for that neighbour's class if it is not in the list. 
                else
                    votes.put(label, (double) 1);
            }
            
            // Sets the decision as the label with the greatest number of votes.
            double maxVote = 0;
            for (Entry<String, Double> vote : votes.entrySet()){
                if (vote.getValue() > maxVote){
                    decision = vote.getKey();
                    maxVote = vote.getValue();
                }
            }
        // Sets the test example's label to that label.
        test.setClassLabel(decision);
        return test;
    }
}