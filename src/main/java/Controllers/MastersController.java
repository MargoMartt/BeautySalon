package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Master;
import Models.MasterData;
import Models.ServiceData;
import TCP.*;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.AdminController.masterData;

public class MastersController {
    static Master masterModal = new Master();
    private ObservableList<Master> masterList = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Master, String> activity;

    @FXML
    private Button add;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Master, Integer> experience;

    @FXML
    private TableColumn<Master, String> name;

    @FXML
    private TableColumn<Master, ArrayList<String>> service;

    @FXML
    private TableColumn<Master, String> surname;

    @FXML
    private TableView<Master> table;

    public static ServiceData serviceData = new ServiceData();

    Response response;

    @FXML
    void AddMaster(ActionEvent event) throws ClassNotFoundException {
        try {
            stage = new Stage();
            String message = "Добавление мастера";
            Request request = new Request(RequestType.ADD_MASTER, message);
            ClientSocket.send(request);
            response = ClientSocket.listen();
            serviceData = new Gson().fromJson(response.getResponseMessage(), ServiceData.class);

            Parent root = FXMLLoader.load(getClass().getResource("/masteraction.fxml"));
            stage.setTitle("Добавление");
            stage.setMinHeight(500);
            stage.setMinWidth(500);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

            masterList.clear();
            createTable(masterData.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteMaster(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.DELETE_MASTER, masterModal);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(response.getResponseMessage());
        masterData = new Gson().fromJson(response.getResponseMessage(), MasterData.class);
        masterList.clear();
        createTable(masterData.getData());
    }

    @FXML
    void EditMaster(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/editmaster.fxml"));
        stage.setTitle("Редактирование записи");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
        masterList.clear();
        createTable(masterData.getData());
    }

    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        loader.setLocation(getClass().getClassLoader().getResource("admin.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    void createTable(ArrayList<Master> masters) {
        initData(masters);
        for (int i = 0; i < masters.size(); i++) {
            surname.setCellValueFactory(new PropertyValueFactory<Master, String>("masterSurname"));
            name.setCellValueFactory(new PropertyValueFactory<Master, String>("masterName"));
            activity.setCellValueFactory(new PropertyValueFactory<Master, String>("activity"));
            experience.setCellValueFactory(new PropertyValueFactory<Master, Integer>("workExperience"));
            service.setCellValueFactory(new PropertyValueFactory<Master, ArrayList<String>>("services"));
        }
        table.setItems(masterList);
    }

    private void initData(ArrayList<Master> masters) {

        for (int i = 0; i < masters.size(); i++) {
            masterList.add(masters.get(i));
        }
    }

    @FXML
    public void selectMaster(javafx.scene.input.MouseEvent mouseEvent) {
        Master selectMaster = (Master) table.getSelectionModel().getSelectedItem();
        masterModal = selectMaster;
    }
    ArrayList<Master> masters = masterData.getData();

    @FXML
    void initialize() {
        createTable(masters);

    }

}
