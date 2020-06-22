package pl.wsb.collection.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserController {
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
    }

   
}
