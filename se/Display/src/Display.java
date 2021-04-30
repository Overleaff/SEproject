
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class Display extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static final int WIDTH = 1200; // 7
    private static final int HEIGHT = 750; // 10
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 520;
    private static Button[] buttons;

    // border pain to resizing
    @Override
    public void start(Stage stage) throws Exception {

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        // SCENE 1 //
        // Creating an image
        Image image1 = new Image(new FileInputStream("C:\\Users\\Admin\\Desktop\\text_java\\NEw\\src\\image.png"));

        // Setting the image view
        ImageView imageView = new ImageView(image1);

        // Setting the position of the image
        imageView.setX(210.0);
        imageView.setY(50.0);

        // setting the fit height and width of the image view
        imageView.setFitHeight(330);
        imageView.setFitWidth(320);

        // Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        // Label
        Label label1 = new Label("Data Visualization");
        label1.setTextFill(Color.web("#4169E1"));
        label1.setStyle("-fx-font: 80px Tahoma");

        // Button
        Button button1 = new Button("Start");
        button1.setMaxSize(150, 320);
        button1.setStyle("-fx-background-color: green; -fx-text-fill: white;-fx-font: 20 Tahoma;");
        
        // lAYOUT
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, Color.WHITESMOKE);
        layout.getChildren().addAll(label1, imageView, button1);
        layout.setSpacing(20.0);
        //
        





        // SCENE 2 //
        VBox layout2 = new VBox();
        Scene scene2 = new Scene(layout2, Color.WHITESMOKE);
        VBox box = new VBox();
        BorderPane pane = new BorderPane();

        layout2.setSpacing(15);
        layout2.setAlignment(Pos.CENTER);
        button1.setOnAction(e -> stage.setScene(scene2));

        // LABEL 2
        Label label2 = new Label("Clustering Algorithm");
        label2.setTextFill(Color.web("#4169E1"));
        label2.setStyle("-fx-font: 80px Tahoma");

        // IMAGE
        Image imageCluster = new Image(
                new FileInputStream("C:\\Users\\Admin\\Desktop\\text_java\\NEw\\src\\cluster.png"));

        // BUTTON
        Button button2 = new Button("K Means");
        Button button3 = new Button("K-Nearest Neighbours");
        Button button4 = new Button("Mean Shift Clustering");
        Button button5 = new Button("Continue");

        // BUTTON SIZE
        button2.setMaxSize(300, 520);
        button3.setMaxSize(300, 520);
        button4.setMaxSize(300, 520);
        button5.setMaxSize(150, 320);

        // BUTTON STYLE
        button2.setStyle("-fx-background-color: #64C3EB; -fx-text-fill: #ffffff; ");
        button3.setStyle("-fx-background-color: #5BB381; -fx-text-fill: white;");
        button4.setStyle("-fx-background-color: #E3834C; -fx-text-fill: white;");

        // BUTTON FONT
        button2.setFont(Font.font("Tahoma", 20));
        button3.setFont(Font.font("Tahoma", 20));
        button4.setFont(Font.font("Tahoma", 20));
        button5.setFont(Font.font("Tahoma", 20));

        box.getChildren().addAll(button2, button3, button4, button5);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);

        pane.setTop(label2);
        pane.setLeft(new ImageView(imageCluster));
        pane.setCenter(box);
    
        BorderPane.setAlignment(label2, Pos.TOP_CENTER);
        BorderPane.setAlignment(box, Pos.CENTER);
     
        // ADD ALL NODE
        layout2.getChildren().addAll(pane);
        //







        // SCENE 3  //
        VBox layout3 = new VBox();
        Scene scene3 = new Scene(layout3, Color.WHITESMOKE);
        button5.setOnAction(e -> stage.setScene(scene3));
        //

        // ADD ICON
        Image icon = new Image("image.png");
        stage.getIcons().add(icon);
        stage.setTitle("Clustering Visualization");
        //

        stage.setScene(scene);
        stage.show();

    }

}
