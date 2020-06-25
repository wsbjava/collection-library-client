package pl.wsb.collection.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.wsb.collection.controller.consts.ApiEndpoints;
import pl.wsb.collection.model.AuthenticationResponse;
import pl.wsb.collection.model.Item;
import pl.wsb.collection.model.ItemList;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class collectionViewController {

    Stage stage;

    @FXML
    private Button btnReturn;
    @FXML
    private TableView tbCollection;



    @FXML
    private void initialize() throws MalformedURLException {
        if (this.btnReturn != null) {
            this.btnReturn.setOnAction(this::handleReturnClick);

        }
        ItemList responseItemList = null;

        TableColumn<String, Item> title = new TableColumn<>("Tytuł");
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<String, Item> publisher = new TableColumn<>("Wydawca");
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        TableColumn<String, Item> type = new TableColumn<>("Typ");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<String, Item> genre = new TableColumn<>("Gatunek");
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));



        tbCollection.getColumns().add(title);
        tbCollection.getColumns().add(publisher);
        tbCollection.getColumns().add(type);
        tbCollection.getColumns().add(genre);


        if(SessionController.get().IsAdmin()) {
           addEraseButton();
        }
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.valueOf(new URL(ApiEndpoints.MainPath +
                ApiEndpoints.COLLECTION_ENTRY)));
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
                for(int i = 0; i< responseItemList.getData().size();i++)
                {
                    tbCollection.getItems().add(responseItemList.getData().get(i));
                }
                System.out.println(tbCollection.getItems());
            }

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

    private void addEraseButton()
    {
        TableColumn erase = new TableColumn<>("Usuń");
        tbCollection.getColumns().add(erase);
        Callback<TableColumn<Item, String>, TableCell<Item, String>> cellFactory
                = //
                new Callback<TableColumn<Item, String>, TableCell<Item, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Item, String> param) {
                        final TableCell<Item, String> cell = new TableCell<Item, String>() {

                            final Button btn = new Button("Usuń");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Item Item = getTableView().getItems().get(getIndex());
                                        System.out.println(Item);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        erase.setCellFactory(cellFactory);
    }
    private void handleErase(int id)
    {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://127.0.0.1:8080/webapi/collectionEntry");
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
    }

}
