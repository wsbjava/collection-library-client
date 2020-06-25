package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
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
import pl.wsb.collection.controller.consts.ApiEndpoints;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.AuthenticationRequest;
import pl.wsb.collection.model.AuthenticationResponse;
import pl.wsb.collection.model.AuthorRequest;
import pl.wsb.collection.model.ItemRequest;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Objects;
import java.text.SimpleDateFormat;

public class AddItemController {
    Stage stage;
    private AuthenticationResponse authResponse;

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
    private ChoiceBox<String> cbGenre;




    @FXML
    private void initialize() {
        if (this.btnAdd != null) {
            this.btnAdd.setOnAction(this::handleAddClick);
        }
        if (this.btnReturn != null) {
            this.btnReturn.setOnAction(this::handleReturnClick);
        }
        System.out.println(SessionController.get().geteMail());
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
        cbType.setItems(FXCollections.observableArrayList("BOOK","COMICS","DISC"));
        cbGenre.setItems(FXCollections.observableArrayList("Action","Comedy","Drama"));
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
            if (this.cbGenre == null) {
                return;
            } //if
            if (this.cbType == null) {
                return;
            } //if
            if (this.tfPublisher == null) {
                return;
            } //if
            System.out.println(this.cbType.getValue());
            if (this.handleAddCall(this.tfTitle.getText(),
                    this.tfName.getText(),this.tfSurname.getText(),this.tfDate.getText(),this.cbGenre.getValue(),this.cbType.getValue(),this.tfPublisher.getText())) {
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
        logOn();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(String.valueOf(new URL(ApiEndpoints.MainPath +
                ApiEndpoints.COLLECTION_ENTRY)));
        ObjectMapper objectMapper = new ObjectMapper();
        StringEntity requestEntity = null;
        try {
            requestEntity = new StringEntity(
                    objectMapper.writeValueAsString(
                            new ItemRequest().title(title).author(new AuthorRequest().firstName(name).
                                    lastName(surname)).dateOfRelease(new SimpleDateFormat("dd-MM-yyyy").parse(date)).
                                    type(type).genre(genre).publisher(publisher)
                    ),
                    ContentType.APPLICATION_JSON
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(requestEntity);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer: "+ this.authResponse.getAccessToken());
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

    void logOn(){
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/webapi/authenticate");
            ObjectMapper objectMapper = new ObjectMapper();
            StringEntity requestEntity = new StringEntity(
                    objectMapper.writeValueAsString(
                            new AuthenticationRequest()
                                    .eMail(SessionController.get().geteMail())
                                    .password(SessionController.get().getPassword())
                    ),
                    ContentType.APPLICATION_JSON
            );
            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpPost);


            this.authResponse = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                    .readValue(response.getEntity().getContent(), AuthenticationResponse.class);


            System.out.println(this.authResponse.getAccessToken());
            System.out.println(this.authResponse.getEmailAddress());
            System.out.println(this.authResponse.getExpiresIn());
            System.out.println(this.authResponse.getUserId());
            this.authResponse.getRole().forEach((e)->
            {
                System.out.println(e.getAbbr() + " "+ e.getName());
            });


        }
        catch (IOException ioExp){

            System.out.println(ioExp.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
