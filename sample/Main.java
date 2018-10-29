package sample;

import data.Datasource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    @Override
    public void init() {

        if(! Datasource.getInstance().open()){
            Platform.exit();
        }


    }

    @Override
    public void stop() {
        Datasource.getInstance().stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
