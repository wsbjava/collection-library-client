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

public class UserController {
    Stage stage;
    @FXML
    private Button btnCollection;
    @FXML
    private Button btnSuggestions;
    @FXML
    private Button btnRentals;
    @FXML
    private Button btnMessages;
    
    @FXML
    private void initialize() {
        if (this.btnCollection != null) {
            this.btnCollection.setOnAction(this::handleCollectionClick);
        }
        if (this.btnSuggestions != null) {
            this.btnSuggestions.setOnAction(this::handleSuggestionsClick);
        }
        if (this.btnRentals != null) {
            this.btnRentals.setOnAction(this::handleRentalsClick);
        }
        if (this.btnMessages != null) {
            this.btnMessages.setOnAction(this::handleMessagesClick);
        }
    }

    private void handleMessagesClick(ActionEvent actionEvent) {
    }

    private void handleRentalsClick(ActionEvent actionEvent) {
    }

    private void handleSuggestionsClick(ActionEvent actionEvent) {
    }


    private void handleCollectionClick(ActionEvent actionEvent) {
        stage = (Stage) btnCollection.getScene().getWindow();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getClassLoader().getResource("view/collectionView.fxml"));
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
