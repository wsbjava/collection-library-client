package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.wsb.collection.model.AuthenticationResponse;
import pl.wsb.collection.model.Item;
import pl.wsb.collection.model.ItemList;

import java.io.IOException;
import java.util.Objects;

public class AdminCollectionViewController {

    Stage stage;

    @FXML
    private Button btnReturn;
    @FXML
    private TableView<String> tbCollection;



    @FXML
    private void initialize() {
        if (this.btnReturn != null) {
            this.btnReturn.setOnAction(this::handleReturnClick);

        }
        SessionController.get().geteMail();
        ItemList responseItemList = null;

        TableColumn<String, Item> title = new TableColumn<>("Tytuł");
        TableColumn<String, Item> publisher = new TableColumn<>("Wydawca");

        tbCollection.getColumns().add(title);
        tbCollection.getColumns().add(publisher);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/webapi/collectionEntry");
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            System.out.println(response.getStatusLine());
            System.out.println(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();

        }
        try {
            if (response != null) {
                responseItemList = new ObjectMapper().readValue(response.getEntity().getContent(), ItemList.class);
            }
            System.out.println(responseItemList);
        } catch (IOException e) {
            e.printStackTrace();
        }

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


}
