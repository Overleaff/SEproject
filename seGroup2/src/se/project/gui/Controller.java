package se.project.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.project.clustering.knn.KNNClustering;
import se.project.components.Point;

public class Controller implements Initializable {
	
   @FXML
   private Button start;
	
   @FXML
   private Button runBut;
   
   @FXML
   private ImageView home;
   
   @FXML
   private ChoiceBox<String> myChoiceBox = new ChoiceBox<>(); 
   
   private String[] algorithm = {"K Means","K-Nearest Neighbours","Mean Shift Clustering"};
   @FXML
   private Button gui;
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	
	@FXML
	private TextField kField;

	    
	@FXML
	private MenuButton chooseAlgo;
	
	@FXML
    private VBox vbox;
	  

	  
	  
	@FXML
	public void run() {
		  System.out.println(kField.getText());
		  System.out.println(myChoiceBox.getValue());
		  if(((myChoiceBox.getValue())== "K-Nearest Neighbours")&& kField.getText() != "" ) {
				KNNClustering knn = new KNNClustering();
		        ArrayList<Point> inputObservations = knn.initPoint();  // train
		        
		        knn.showAllPoint();
		         
		        Point test1 = new Point(10.0,8.0);
		     
		        ArrayList<Integer> className = new ArrayList<>();
		        
		        for(int i =0; i<inputObservations .size();i++){
		            int flag = 0;
		            for(int j=0;j<i;j++){
		                if(inputObservations.get(i).getClusterNo() == (inputObservations.get(j).getClusterNo())){
		                flag = 1;   
		                break;}
		            }
		            if(flag == 0){
		            className.add(inputObservations.get(i).getClusterNo());
		            }
		        }
		 
		         test1 = knn.classify(test1, inputObservations,Integer.parseInt(kField.getText()));
		           
		             
		           // Resets the label and adds the Observation back to the list. 
		         System.out.println(test1.getClusterNo());
		         NumberAxis xAxis = new NumberAxis();
			     xAxis.setLabel("X");

			    NumberAxis yAxis = new NumberAxis();
			    yAxis.setLabel("Y");
			    ScatterChart<Number,Number> scatterChart = new ScatterChart<Number,Number>(xAxis, yAxis);
	    
	   
	        for(Integer clust:className){
	            XYChart.Series dataSeries1 = new XYChart.Series();
	            dataSeries1.setName(clust.toString());

	            for(Point point:inputObservations){
	                if(clust == (point.getClusterNo())){
	                     dataSeries1.getData().add(new XYChart.Data(point.getX(),point.getY()));
	                }
	            } 
	            scatterChart.getData().add(dataSeries1);
	        }
	        vbox.getChildren().add(scatterChart);
		  }
		  
	}
	
	  @FXML
	 void clear(ActionEvent event) {
		  vbox.getChildren().clear();
	 }
	
	
	
	// load scene 2
	@FXML
	public void click() {
		try {
			
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("next.fxml"));
			Stage stage =  (Stage) start.getScene().getWindow();
			stage.setScene(new Scene(root));
	        stage.setMaxHeight(900);
	        stage.setMaxWidth(1300);
		    stage.show();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void clickGUI() {
		try {
			
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("cluster.fxml"));
			Stage stage =  (Stage) gui.getScene().getWindow();
			stage.setScene(new Scene(root));
	        Image icon = new Image("se/project/image/image.png");
	        stage.getIcons().add(icon);
	        stage.setTitle("DataVisualization");
	        stage.setMaxHeight(900);
	        stage.setMaxWidth(1300);
		    stage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void goHome() {
		try {
			
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("home.fxml"));
			Stage stage = (Stage) home.getScene().getWindow();
			stage.setScene(new Scene(root));
			 Image icon = new Image("se/project/image/image.png");
		     stage.getIcons().add(icon);
		     stage.setTitle("DataVisualization");
	        stage.setMaxHeight(900);
	        stage.setMaxWidth(1300);
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myChoiceBox.getItems().addAll(algorithm);
		
		
	}

	
     

}
