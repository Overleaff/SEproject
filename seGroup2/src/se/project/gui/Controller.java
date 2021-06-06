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
	
	//FXML
    @FXML
    private Button start;
    @FXML
    private Button runBut;
    @FXML
    private ImageView home;
    @FXML
    private ChoiceBox<String> myChoiceBox = new ChoiceBox<>(); 
    @FXML
    private Button gui;
	@FXML
	private TextField kField;
	@FXML
	private MenuButton chooseAlgo;
	@FXML
    private VBox vbox;
	
    
	// necessary component
	private String[] algorithm = {"K Means","K-Nearest Neighbours","Mean Shift Clustering"};
	
	private ScatterChart<Number,Number> scatterChart;           // chart
	private ArrayList<Point> inputObservations = new ArrayList<>();   // input Point
    private XYChart.Series<Number,Number> dataSeries = new XYChart.Series<>();  // series test Point
	private ArrayList<Point> testPoint = new ArrayList<>();            // test Point
	private ArrayList<Point> resultPoint = new ArrayList<>();          // result Point
	private KNNClustering knn; 
    private ArrayList<XYChart.Series> arrSeries = new ArrayList<>();
	
	
	@FXML
	public void run() {
		  System.out.println(kField.getText());
		  System.out.println(myChoiceBox.getValue());
		  
		  if(((myChoiceBox.getValue())== "K-Nearest Neighbours")&& kField.getText() != "" ) {	
			    knn = new KNNClustering();
		        inputObservations = knn.initPoint();  // train default point
		        ArrayList<Integer> className = new ArrayList<>();
		        Point test1 = new Point(10.0,8.0);
		        testPoint.add(test1);
		        
		        //knn.showAllPoint();
		        
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
		       /*
		         test1 = knn.classify(test1, inputObservations,Integer.parseInt(kField.getText()));   
		           // Resets the label and adds the Observation back to the list. 
		        System.out.println(test1.getClusterNo());*/
		      
	    
	            
			    
		        dataSeries.getData().add(new XYChart.Data(test1.getX(),test1.getY()));
		        dataSeries.setName("Unknown");
		        scatterChart.getData().add(dataSeries);
		        
		        //set Name data and add Point
	            for(Integer clust:className){
	            	XYChart.Series<Number,Number> dataSeries1 = new XYChart.Series<>();
	            	dataSeries1.setName(clust.toString());
	            	for(Point point:inputObservations){
	            		if(clust == (point.getClusterNo())){
	            			dataSeries1.getData().add(new XYChart.Data(point.getX(),point.getY()));
	            		}
	            	} 
	            arrSeries.add(dataSeries1);
	            scatterChart.getData().add(dataSeries1);
	            }
	            	vbox.getChildren().add(scatterChart);
		  		}
		  
	}
     
	
	 @FXML
		public void visual() {
			 //vbox.getChildren().clear(); 
			 scatterChart.getData().remove(dataSeries);
			 
		    resultPoint = knn.finalResult(testPoint,inputObservations,3);  // result point after knn
	       
	     
	        
	        for(Point p:testPoint){
                for(XYChart.Series s:arrSeries){
                  if((p.getClusterNo()) == Integer.parseInt(s.getName())){
                      s.getData().add(new XYChart.Data(p.getX(),p.getY()));
                  }
              } 
            }
	       
	       // vbox.getChildren().add(scatterChart);
	        
		}
	
	
	
	
	
	 @FXML
	 void clear(ActionEvent event) {
		  dataSeries.getData().removeAll();
		  scatterChart.getData().removeAll(arrSeries);
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
	
	// load scene3
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
	
	
	// back to home
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

    //initialize
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		  myChoiceBox.getItems().addAll(algorithm);
		  NumberAxis xAxis = new NumberAxis();
		  xAxis.setLabel("X");

		  NumberAxis yAxis = new NumberAxis();
		  yAxis.setLabel("Y");
		  scatterChart = new ScatterChart<Number,Number>(xAxis, yAxis);
	  
	}

	
    
}
