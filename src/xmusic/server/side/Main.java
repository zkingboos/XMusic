package xmusic.server.side;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("server-display.fxml"));
        primaryStage.setTitle("X-Music Server [Offline]");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(-1);
        });
        primaryStage.setResizable(false);
        stage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
