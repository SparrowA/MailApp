package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {

    public static URL itemResource;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader root = new FXMLLoader(getClass().getResource("View/sample.fxml"));
        itemResource = getClass().getResource("View/MessageItem.fxml");

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root.load(), 300, 275));

        primaryStage.show();
    }

    public static URL getResource(String url){
        return ClassLoader.getSystemResource(url);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
