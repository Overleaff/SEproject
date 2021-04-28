import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.*;
 
public class ScatterChartSample extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("Kmeans Clustering");
        final NumberAxis xAxis = new NumberAxis(0, 20, 1);
        final NumberAxis yAxis = new NumberAxis(0, 20, 1);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        //xAxis.setLabel("Age (years)");                
        //yAxis.setLabel("Returns to date");
        sc.setTitle("Investment Overview");
       
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Labeled as 0");
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("labeled as 1");


        String row;
        try(BufferedReader csvReader = new BufferedReader(new FileReader("out.csv"))) {

            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (Double.valueOf(data[2]) == 0){
                    series1.getData().add(new XYChart.Data(Double.valueOf(data[0]), Double.valueOf(data[1])) );
                }else {
                    series2.getData().add(new XYChart.Data(Double.valueOf(data[0]), Double.valueOf(data[1])) );
                }
                
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        
 
        sc.getData().addAll(series1, series2);
        Scene scene  = new Scene(sc, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

 
    public static void main(String[] args) {
        launch(args);
    }
}