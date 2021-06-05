package home.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller {
	
   @FXML
   private Button start;
	
   @FXML
   private ImageView home;
   
   
 
   
   @FXML
   private Button gui;
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	
	   @FXML
	    private TextField kField;

	    @FXML
	    void chooseK(KeyEvent event) {
	    	if (event.getCode().toString().equals("ENTER")) {
				
				System.out.println(kField.getText());
			}
	    }
	
	    @FXML
	    private MenuButton chooseAlgo;

	  

	    @FXML
	    void choose(ActionEvent event) {
           System.out.println(((Button)event.getSource()).getText());
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
	        Image icon = new Image("home/image/image.png");
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
			 Image icon = new Image("home/image/image.png");
		     stage.getIcons().add(icon);
		     stage.setTitle("DataVisualization");
	        stage.setMaxHeight(900);
	        stage.setMaxWidth(1300);
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
     

}
