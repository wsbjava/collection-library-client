package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.AuthorRequest;
import pl.wsb.collection.model.ItemRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;
import java.text.SimpleDateFormat;

public class AddItemController {
    Stage stage;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnReturn;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfGenre;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfPublisher;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML

    private ChoiceBox<String> cbType;




    @FXML
    private void initialize() {
        if (this.btnAdd != null) {
            this.btnAdd.setOnAction(this::handleAddClick);
        }
        if (this.btnReturn != null) {
            this.btnReturn.setOnAction(this::handleReturnClick);
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/webapi/collection_type");
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusLine().getStatusCode());
        try {
            System.out.println(EntityUtils.toString(response.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Alert createAlert(String title, String header, String content, Alert.AlertType alertType) {
        final Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.OK);
        return alert;

    }

    private void handleReturnClick(ActionEvent actionEvent) {
        stage = (Stage) btnReturn.getScene().getWindow();
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getClassLoader().getResource("view/mainAdminView.fxml"));
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

    private void handleAddClick(ActionEvent actionEvent) {
        try {
            if (this.tfTitle == null) {
                return;
            }
            if (this.tfName == null) {
            return;
              } //if
            if (this.tfSurname == null) {
                return;
            } //if
            if (this.tfDate == null) {
                return;
            } //if
            if (this.tfGenre == null) {
                return;
            } //if
            if (this.tfType == null) {
                return;
            } //if
            if (this.tfPublisher == null) {
                return;
            } //if
            if (this.handleAddCall(this.tfTitle.getText(),
                    this.tfName.getText(),this.tfSurname.getText(),this.tfDate.getText(),this.tfGenre.getText(),this.tfType.getText(),this.tfPublisher.getText())) {
                createAlert(
                        "Dodawanie - sukces",
                        "Sukces",
                        "Dodawanie zbioru powiodło się.",
                        Alert.AlertType.INFORMATION
                ).showAndWait();
            } else { //if
                createAlert(
                        "Dodawania - błąd",
                        "Ostrzeżenie",
                        "Dane zbioru nie wyglądają na poprawne.",
                        Alert.AlertType.WARNING
                ).showAndWait();
            } //else
        } catch (ValidationException ex) {
            createAlert(
                    "Dodawanie - ostrzeżenie",
                    "Ostrzeżenie",
                    "Proszę uzupełnić dane zbioru.",
                    Alert.AlertType.WARNING
            ).showAndWait();
        } catch (Exception ex) {
            createAlert(
                    "Dodawanie - błąd",
                    "Błąd",
                    "Przepraszamy, wystąpił błąd aplikacji.",
                    Alert.AlertType.ERROR
            ).showAndWait();
        }
    }
    private boolean handleAddCall(String title, String name, String surname, String date, String genre, String type, String publisher)throws
            ValidationException, IOException {
        if (StringUtils.isBlank(title)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(name)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(surname)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(date)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(genre)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(type)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(publisher)) {
            throw new ValidationException();
        } //if
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/webapi/collectionEntry");
        ObjectMapper objectMapper = new ObjectMapper();
        StringEntity requestEntity = null;
        try {
            requestEntity = new StringEntity(
                    objectMapper.writeValueAsString(
                            new ItemRequest().title(title).author(new AuthorRequest().firstName(name).lastName(surname)).dateOfRelease(new SimpleDateFormat("dd-MM-yyyy").parse(date)).type(type).genre(genre).publisher(publisher)
                    ),
                    ContentType.APPLICATION_JSON
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);
        if (response == null) {
            return false;
        } //if
        if (response.getStatusLine() == null) {
            return false;
        } //if
        return (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
    }
}
