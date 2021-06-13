package se.project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;


import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.project.clustering.kmeans.KMeansClustering;
import se.project.clustering.knn.KNNClustering;

import se.project.clustering.knn.Neighbour;
//import se.project.clustering.meanshift.MeanShiftClustering;
import se.project.components.Cluster;
import se.project.components.Point;

import java.util.Random;

import se.project.clustering.meanshift.MeanShiftClustering;
import se.project.components.Point;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private int clickCount = 0;
    @FXML
    private Button gui;
    @FXML
    private Button start;
    @FXML
    private Label invalid;
    @FXML
    private Controller control;
    @FXML
    private Button runBut;
    @FXML
    private Button nextBut;
    @FXML
    private Button visualBut;
    @FXML
    private ImageView home;
    @FXML
    private ChoiceBox<String> myChoiceBox = new ChoiceBox<>();
    @FXML
    private TextField kField;
    @FXML
    private MenuButton chooseAlgo;
    @FXML
    private VBox vbox;
    @FXML
    private Button addData;
    @FXML
    private TextField xField;
    @FXML
    private TextField yField;
    @FXML
    private TextField clustField;
    @FXML
    private Button addTestPoint;

    @FXML
    private Button setSeedPoint;


    // necessary component
    private String[] algorithm = {"K Means", "K-Nearest Neighbours", "Mean Shift Clustering"};
    private ScatterChart<Number, Number> scatterChart; // chart to Display
    private XYChart.Series<Number, Number> testSeries = new XYChart.Series<>(); // series test Point
    private ArrayList<XYChart.Series> arrayInputSeries = new ArrayList<>(); // arr series of input Point

    private ArrayList<Point> inputObservations = new ArrayList<>(); // input Point
    private ArrayList<Point> testPoint = new ArrayList<>(); // test Point
    private ArrayList<Point> resultPoint = new ArrayList<>(); // result Point

    private KMeansClustering kmean;
    private KNNClustering knn; // private KNNClustering knn;
    public    XYChart.Series<Number, Number> tmp = new XYChart.Series<>();

    
    ArrayList<Point> input = new ArrayList<>();
    //private MeanShiftClustering mshift;

    private MeanShiftClustering mshift;

    //set seed point for mean shift


    @FXML
    public void run() {

        if (kField.getText() == "" || Integer.parseInt(kField.getText()) <= 0 || kField.getText() == "") {
            invalid.setText("Invalid K.Must be positive");
        } else {
            invalid.setText("");
        }


        if (((myChoiceBox.getValue()) == "K Means") && kField.getText() != ""
                && Integer.parseInt(kField.getText()) > 0) {
            kmean = new KMeansClustering();

            testPoint = kmean.initPoint();


            kmean = new KMeansClustering(testPoint, Integer.parseInt(kField.getText()));
            kmean.init();
            kmean.step();
            input = kmean.updateCentroid(); // diem tam
            // run all

               /* for (int i = 0; i < Integer.parseInt(kField.getText()); i++){
                	inputObservations.add(new Point(0.0, 0.0));
                }*/

            for (Point test : testPoint) {
                testSeries.getData().add(new XYChart.Data(test.getX(), test.getY()));
            }
            testSeries.setName("Unknown");
            scatterChart.getData().add(testSeries);

            for( Point test :input) {
                tmp.setName("ClusterInit");
                tmp.getData().add(new XYChart.Data(test.getX(), test.getY()));
            }
            scatterChart.getData().add(tmp);

            /*for( Point test :input) {
                tmp.get(test.getClusterNo()).setName(((Integer)test.getClusterNo()).toString());
                tmp.get(test.getClusterNo()).getData().add(new XYChart.Data(test.getX(), test.getY()));
                scatterChart.getData().add(tmp.get(test.getClusterNo()));
            }*/

            vbox.getChildren().add(scatterChart);
        }

        	
     

        if (((myChoiceBox.getValue()) == "K-Nearest Neighbours") && kField.getText() != ""
                && Integer.parseInt(kField.getText()) > 0) {

            knn = new KNNClustering();
            // replace by user
            if (inputObservations.size() == 0) {
                inputObservations = knn.initPoint();
            } // train default point

            ArrayList<Integer> className = new ArrayList<>();

            if (testPoint.size() == 0) {

            	   Random rand = new Random(); //instance of random class
			        for (int i = 0; i < Integer.parseInt(kField.getText()); i++) {
			          
			            double  x = 10+rand.nextDouble()*5;  //rand 10-15
			            double y = 10+rand.nextDouble()*5;   
			            Point tmp = new Point(x, y);
			            
			            testPoint.add(tmp);
			        }

            }
            //
            for (int i = 0; i < inputObservations.size(); i++) {
                int flag = 0;
                for (int j = 0; j < i; j++) {
                    if (inputObservations.get(i).getClusterNo() == (inputObservations.get(j).getClusterNo())) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    className.add(inputObservations.get(i).getClusterNo());
                }
            }
            // add test Series
            for (Point test : testPoint) {
                testSeries.getData().add(new XYChart.Data(test.getX(), test.getY()));
            }
            testSeries.setName("Unknown");
            scatterChart.getData().add(testSeries);

            // set Name data and add Point
            for (Integer clust : className) {
                XYChart.Series<Number, Number> testSeries1 = new XYChart.Series<>();
                testSeries1.setName(clust.toString());
                for (Point point : inputObservations) {
                    if (clust == (point.getClusterNo())) {
                        testSeries1.getData().add(new XYChart.Data(point.getX(), point.getY()));
                    }
                }
                arrayInputSeries.add(testSeries1);
                scatterChart.getData().add(testSeries1);
            }
            vbox.getChildren().add(scatterChart);
        }


        if (myChoiceBox.getValue() == "Mean Shift Clustering") {
            //Set seed point and bandwidth for mean shift
            mshift = new MeanShiftClustering(Integer.parseInt(kField.getText()));
            if (inputObservations.size() == 0) {
                inputObservations = mshift.initPoint();
            }
            //set seed point by default when using random input
            mshift.setSeedPoint(inputObservations.get(0));
            for (Point point : inputObservations) {
                testSeries.getData().add(new XYChart.Data(point.getX(), point.getY()));
                arrayInputSeries.add(testSeries);
            }
            scatterChart.getData().add(testSeries);
            vbox.getChildren().add(scatterChart);
        }
    }

    @FXML
    public void setSeedPoint() {
        Point tmp = new Point(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));
        mshift.createSeedPoint(tmp);

    }

    private LineChart<Number, Number> lineChart; // support step KNN

    @FXML
    public void next() {

        if (((myChoiceBox.getValue()) == "K Means") && kField.getText() != ""
                && Integer.parseInt(kField.getText()) > 0) {
            scatterChart.getData().remove(testSeries);
            ArrayList<Point> input1 = new ArrayList<>();

            clickCount++;


            for (int i =1; i <= clickCount; i++){
                resultPoint = kmean.step();
                kmean.updateCentroid(); // tap diem tam
            }
            input1 = kmean.updateCentroid();
            System.out.print(input1.size());
            scatterChart.getData().remove(tmp);
            tmp.getData().clear();


            for( Point test :input1) {
                tmp.getData().add(new XYChart.Data(test.getX(), test.getY()));
            }
            scatterChart.getData().add(tmp);


            ArrayList<XYChart.Series> testSeries1 = new ArrayList<>();
            for (int clust = 0; clust < kmean.getClusters(); clust++) {
                XYChart.Series tmp = new XYChart.Series();
                testSeries1.add(tmp);
                if (clickCount ==1 ){
                    testSeries1.get(clust).setName(new Integer(clust).toString());
                }

                for (Point point : resultPoint) {
                    if (clust == (point.getClusterNo())) {
                        if (point.getX() != resultPoint.get(point.getClusterNo()).getX())
                            testSeries1.get(clust).getData().add(new XYChart.Data(point.getX(), point.getY()));
                    }
                }

                scatterChart.getData().add(testSeries1.get(clust));
                if (clickCount % 2 == 0) {
                    testSeries.getData().clear();
                    scatterChart.getData().clear();
                }
            }
            for (int clust = 0; clust < testSeries1.size(); clust++){
                testSeries1.remove(clust);
            }
        }


        if (((myChoiceBox.getValue()) == "K-Nearest Neighbours") && kField.getText() != ""
                && Integer.parseInt(kField.getText()) > 0) {
            if (clickCount == 0) {

                vbox.getChildren().clear();
                clickCount++;
                // add point to line chart
                // draw line by 1 step
                for (Point p : testPoint) {

                    for (Point point : inputObservations) {
                        XYChart.Series dataS = new XYChart.Series();
                        dataS.getData().add(new XYChart.Data(p.getX(), p.getY()));
                        dataS.getData().add(new XYChart.Data(point.getX(), point.getY()));
                        lineChart.getData().add(dataS);
                        ///
                    }
                }
                vbox.getChildren().add(lineChart);
            } else if (clickCount == 1) {

                clickCount++;
                lineChart.getData().clear();
                vbox.getChildren().remove(lineChart);

                for (Point test : testPoint) {
                    // goi den Step tra ve tap Neighbour
                    ArrayList<Point> neighbour = knn.step(test, inputObservations, Integer.parseInt(kField.getText()));

                    for (int i = 0; i < neighbour.size(); i++) {
                        XYChart.Series data = new XYChart.Series();
                        data.getData().add(new XYChart.Data(test.getX(), test.getY()));
                        data.getData().add(new XYChart.Data(neighbour.get(i).getX(), neighbour.get(i).getY()));
                        lineChart.getData().add(data);

                    }
                }
                vbox.getChildren().add(lineChart);
            } else if (clickCount == 2) {
                lineChart.getData().clear();
                vbox.getChildren().remove(lineChart);
                for (Point p : testPoint) {
                    for (XYChart.Series s : arrayInputSeries) {
                        if ((p.getClusterNo()) == Integer.parseInt(s.getName())) {
                            s.getData().add(new XYChart.Data(p.getX(), p.getY()));
                        }
                    }
                }
                scatterChart.getData().remove(testSeries);
                vbox.getChildren().add(scatterChart);
                clickCount++;
            }

        }
    }

    // RUN ALL
    @FXML
    public void visual() {
        nextBut.setDisable(true);

        if (((myChoiceBox.getValue()) == "K-Nearest Neighbours")) {
        
        	testSeries.getData().clear();


            scatterChart.getData().remove(testSeries);
            resultPoint = knn.result(testPoint, inputObservations, Integer.parseInt(kField.getText())); // result point
            // after knn


            for (Point p : resultPoint) {
                for (XYChart.Series s : arrayInputSeries) {
                    if ((p.getClusterNo()) == Integer.parseInt(s.getName())) {
                        s.getData().add(new XYChart.Data(p.getX(), p.getY()));
                        
                    }
                }
                
            }
               
        }



        if (((myChoiceBox.getValue()) == "K Means")) {
            testSeries.getData().clear();
            scatterChart.getData().remove(testSeries);
            scatterChart.getData().remove(tmp);

            resultPoint = kmean.result();
            kmean.showPoint();

            ArrayList<Integer> className = new ArrayList<>();

            for (Point p : testPoint) {
                for (XYChart.Series s : arrayInputSeries) {
                    if ((p.getClusterNo()) == Integer.parseInt(s.getName())) {
                        s.getData().add(new XYChart.Data(p.getX(), p.getY()));
                    }
                }
            }

            for (int i = 0; i < kmean.getClusters(); i++)
                className.add(i);


            // set Name data and add Point
            for (Integer clust : className) {
                XYChart.Series<Number, Number> testSeries1 = new XYChart.Series<>();
                testSeries1.setName(clust.toString());
                for (Point point : testPoint) {
                    if (clust == (point.getClusterNo())) {
                        testSeries1.getData().add(new XYChart.Data(point.getX(), point.getY()));
                        arrayInputSeries.add(testSeries1);
                    }
                }

                scatterChart.getData().add(testSeries1);
            }

        }

        if (myChoiceBox.getValue() == "Mean Shift Clustering") {
            scatterChart.getData().remove(testSeries);
            resultPoint = mshift.meanShiftClustering(mshift.getSeedPoint(), inputObservations, mshift.getBandwidth());

            for (Point point : resultPoint) {
                testSeries.getData().add(new XYChart.Data(point.getX(), point.getY()));
                arrayInputSeries.add(testSeries);
            }
            
            scatterChart.getData().add(testSeries);
           // vbox.getChildren().add(scatterChart);

        }
		

    }


    @FXML
    void addData() {
        Point tmp = new Point(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));
        tmp.updateCluster(Integer.parseInt(clustField.getText()));
        point.getItems().add(tmp);
        inputObservations.add(tmp);
    }

    @FXML
    private Label labelCluster;

    @FXML
    void addTestPoint() {
        Point tm = new Point(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));
        if(clustField.getText()==""||clustField.getText()!="") {
        tm.updateCluster(-1);}
        testPoint.add(tm);
        point.getItems().add(tm);
    }

    @FXML
    void clear(ActionEvent event) {
        clickCount = 0;
        nextBut.setDisable(false);
        inputObservations.clear();
        testPoint.clear();
      
        resultPoint.clear(); 
        
        scatterChart.getData().remove(testSeries);
        testSeries.getData().clear();
        scatterChart.getData().removeAll(arrayInputSeries);
        lineChart.getData().removeAll(arrayInputSeries);
        vbox.getChildren().clear();
        point.getItems().clear();

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

    // load scene3
    @FXML
    public void clickGUI() {
        try {

            Parent root = (Parent) FXMLLoader.load(getClass().getResource("cluster.fxml"));
            Stage stage = (Stage) gui.getScene().getWindow();
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
    private TableView<Point> point;
    @FXML

    private TableColumn<Point, Double> yCol = new TableColumn<>();

    @FXML
    private TableColumn<Point, Double> xCol = new TableColumn<>();

    @FXML
    private TableColumn<Point, Integer> clusterCol = new TableColumn<>();

    // initialize
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        myChoiceBox.getItems().addAll(algorithm);
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("X");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y");
        scatterChart = new ScatterChart<Number, Number>(xAxis, yAxis);
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        xCol.setCellValueFactory(new PropertyValueFactory<Point, Double>("x"));
        yCol.setCellValueFactory(new PropertyValueFactory<Point, Double>("y"));
        clusterCol.setCellValueFactory(new PropertyValueFactory<Point, Integer>("clusterNo"));


    }

    @FXML
    public void click() {
        try {

            Parent root = (Parent) FXMLLoader.load(getClass().getResource("next.fxml"));
            Stage stage = (Stage) start.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaxHeight(900);
            stage.setMaxWidth(1300);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
