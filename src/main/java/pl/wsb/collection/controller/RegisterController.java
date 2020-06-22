package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.RegisterUserRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;

import static jdk.nashorn.internal.objects.Global.println;

public class RegisterController {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfAge;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfPasswordCont;
    @FXML
    private Button btnRegister;

    @FXML
    private void initialize() {
        if (this.btnRegister != null) {
            this.btnRegister.setOnAction(this::handleRegisterClick);
        }
    }

    private void handleRegisterClick(ActionEvent actionEvent) {
        try {
            if (this.pfPassword == null) {
                return;
            } //if
            if (this.tfEmail == null) {
                return;
            } //if
            if (this.pfPasswordCont == null) {
                return;
            } //if
            if (this.tfName == null) {
                return;
            } //if
            if (this.tfSurname == null) {
                return;
            } //if
            if (this.tfAge == null) {
                return;
            } //if
            if (this.handleRegisterCall(this.tfEmail.getText(),
                    this.pfPassword.getText(), this.pfPasswordCont.getText(), this.tfName.getText(), this.tfSurname.getText(), this.tfAge.getText())) {
                createAlert(
                        "Rejestracja - sukces",
                        "Sukces",
                        "Rejestracja powiodła się.",
                        Alert.AlertType.INFORMATION
                ).showAndWait();
            } else { //if
                createAlert(
                        "Rejestracja - błąd",
                        "Ostrzeżenie",
                        "Dane rejestracji nie wyglądają na poprawne.",
                        Alert.AlertType.WARNING
                ).showAndWait();
            } //else
        } catch (ValidationException ex) {
            createAlert(
                    "Rejestracja - ostrzeżenie",
                    "Ostrzeżenie",
                    "Proszę uzupełnić dane do rejestracji.",
                    Alert.AlertType.WARNING
            ).showAndWait();
        } catch (Exception ex) {
            createAlert(
                    "Rejestracja - błąd",
                    "Błąd",
                    "Przepraszamy, wystąpił błąd aplikacji.",
                    Alert.AlertType.ERROR
            ).showAndWait();
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


    private boolean handleRegisterCall(String email, String password, String passwordControl, String name, String surname, String dateOfBirth) throws
            ValidationException, IOException, ParseException {
        if (StringUtils.isBlank(email)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(password)) {
            throw new ValidationException();
        } //if
        if(!StringUtils.equals(password,passwordControl)) {
            throw new ValidationException();
        }
        if (StringUtils.isBlank(name)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(surname)) {
            throw new ValidationException();
        } //if
        if (StringUtils.isBlank(dateOfBirth)) {
            throw new ValidationException();
        } //if
        CloseableHttpClient client = HttpClients.createDefault();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date convertDateOfBirth = formatter.parse(dateOfBirth);

        HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/webapi/user");
        ObjectMapper objectMapper = new ObjectMapper();
        StringEntity requestEntity = new StringEntity(
                objectMapper.writeValueAsString(
                        new RegisterUserRequest().email(email).password(password).name(name).surname(surname).dateOfBirth(convertDateOfBirth)
                ),
                ContentType.APPLICATION_JSON
        );
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
