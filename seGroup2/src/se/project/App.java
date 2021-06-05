package se.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("gui/home.fxml"));

        Scene scene = new Scene(root);
        
        stage.setMaxHeight(700);
        stage.setMaxWidth(1000);
      
        
        Image icon = new Image("se/project/image/image.png");
        stage.getIcons().add(icon);
        
        stage.setTitle("DataVisualization");
        stage.setScene(scene);
        stage.show();
    }

 
}
