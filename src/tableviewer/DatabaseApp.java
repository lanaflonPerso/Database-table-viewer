package tableviewer; /**
 * Created by Tomov on 3.8.2017 г..
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DatabaseApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableviewer/MainView.fxml"));
        primaryStage.setTitle("Database table viewer");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setOnCloseRequest(x -> Platform.exit());
        primaryStage.show();
    }
}
