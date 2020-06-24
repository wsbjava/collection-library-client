package pl.wsb.collection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Aplikacja do zarządzania wypożyczeniami");
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getClassLoader().getResource("view/login.fxml"));
            AnchorPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}