package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Record;
import Models.ServiceData;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.LoginController.UsersNameSurname;
import static Controllers.ClientController.recordData;

public class ClientRecordController {
    Response response;
    private ObservableList<Record> recordsList = FXCollections.observableArrayList();
    public static ServiceData serviceData = new ServiceData();

    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;
    public static ServiceData serviceList = new ServiceData();

    @FXML
    private TableColumn<Record, Double> cost;

    @FXML
    private TableColumn<Record, String> date;

    @FXML
    private Button record;

    @FXML
    private TableColumn<Record, Double> finalcost;

    @FXML
    private TableColumn<Record, String> master;

    @FXML
    private TableColumn<Record, String> service;

    @FXML
    private TableView<Record> table;

    @FXML
    private TableColumn<Record, String> time;

    @FXML
    void NewRecord(ActionEvent event) throws IOException, ClassNotFoundException {

        String message = "Добавление записи";
        Request request = new Request(RequestType.ADD_MASTER, message);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        serviceList = new Gson().fromJson(response.getResponseMessage(), ServiceData.class);

        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/addclientrecord.fxml"));
        stage.setTitle("Добавление записи");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();

        recordsList.clear();
        createTable(LoginController.recordData.getData());
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("client.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    void createTable(ArrayList<Record> records) {
        initData(records);
        for (int i = 0; i < records.size(); i++) {
            date.setCellValueFactory(new PropertyValueFactory<Record, String>("date"));
            time.setCellValueFactory(new PropertyValueFactory<Record, String>("time"));
            service.setCellValueFactory(new PropertyValueFactory<Record, String>("service"));
            master.setCellValueFactory(new PropertyValueFactory<Record, String>("master"));
            finalcost.setCellValueFactory(new PropertyValueFactory<Record, Double>("finalCost"));
            cost.setCellValueFactory(new PropertyValueFactory<Record, Double>("cost"));
        }
        table.setItems(recordsList);
    }

    private void initData(ArrayList<Record> records) {

        for (int i = 0; i < records.size(); i++) {
            recordsList.add(records.get(i));
        }
    }

    ArrayList<Record> records = recordData.getData();

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
        createTable(records);
    }
}
