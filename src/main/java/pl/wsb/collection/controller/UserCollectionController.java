package pl.wsb.collection.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UserCollectionController {
    Stage stage;
    @FXML
    private Button btnReturn;

    @FXML
    private void initialize() {
        if (this.btnReturn != null) {
            this.btnReturn.setOnAction(this::handleReturnClick);
        }

    }

    private void handleReturnClick(ActionEvent actionEvent) {
        stage = (Stage) btnReturn.getScene().getWindow();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getClassLoader().getResource("view/mainUserView.fxml"));
        AnchorPane rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(Objects.requireNonNull(rootLayout));
        stage.setScene(scene);
        stage.show();
    }
}
