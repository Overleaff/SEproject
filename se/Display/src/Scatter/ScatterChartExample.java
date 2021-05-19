
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the S
public class ScatterChartExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BarChart Experiments");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        ScatterChart<Number,Number> scatterChart = new ScatterChart<Number,Number>(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("2014");
        /*
            File myObj = new File("tu.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
            
              String data = myReader.nextLine();
              String[] s = data.split(" ");

              int a = Integer.parseInt(s[0]);
              int b = Integer.parseInt(s[1]);
              dataSeries1.getData().add(new XYChart.Data(a,b));
              
              
            }
           myReader.close();
         */
        
        dataSeries1.getData().add(new XYChart.Data( 1, 567));
        dataSeries1.getData().add(new XYChart.Data( 5, 612));
        dataSeries1.getData().add(new XYChart.Data(10, 800));
        dataSeries1.getData().add(new XYChart.Data(20, 780));
        dataSeries1.getData().add(new XYChart.Data(40, 810));
        dataSeries1.getData().add(new XYChart.Data(80, 850));
       
        scatterChart.getData().add(dataSeries1);

        VBox vbox = new VBox(scatterChart);

        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(1200);

        primaryStage.show();
        
    }


    
}