package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.*;
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

import static Controllers.LoginController.*;

public class ServiceController {
    @FXML
    static Service serviceModal = new Service();

    static MasterServiceData listMasters = new MasterServiceData();

    private ObservableList<Service> serviceList = FXCollections.observableArrayList();
//    public static ServiceData serviceData = new ServiceData();

    Response response;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableColumn<Service, String> master;

    @FXML
    private TableColumn<Service, Double> price;

    @FXML
    private TableColumn<Service, String> name;

    @FXML
    private TableView<Service> table;

    @FXML
    void AddService(ActionEvent event) throws ClassNotFoundException {
        try {
            Request request = new Request(RequestType.MASTER_LIST, "Список мастеров");
            ClientSocket.send(request);
            response = ClientSocket.listen();
            System.out.println(response.getResponseMessage());
            listMasters = new Gson().fromJson(response.getResponseMessage(), MasterServiceData.class);
            stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("/addservice.fxml"));
            stage.setTitle("Добавление записи записи");
            stage.setMinHeight(500);
            stage.setMinWidth(500);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

            serviceList.clear();
            createTable(serviceData.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void DeleteService(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.DELETE_SERVICE, serviceModal);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(response.getResponseMessage());
        serviceData = new Gson().fromJson(response.getResponseMessage(), ServiceData.class);
        serviceList.clear();
        createTable(serviceData.getData());
    }

    @FXML
    void EditService(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.MASTER_LIST, "Список мастеров");
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(response.getResponseMessage());
        listMasters = new Gson().fromJson(response.getResponseMessage(), MasterServiceData.class);

        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/editservice.fxml"));
        stage.setTitle("Редактирование записи");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
        serviceList.clear();
        createTable(serviceData.getData());
    }

    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        if (loginId == 3) {
            loader.setLocation(getClass().getClassLoader().getResource("admin.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }
        if (loginId == 2) {
            loader.setLocation(getClass().getClassLoader().getResource("adminsalona.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    void createTable(ArrayList<Service> services) {
        initData(services);
        for (int i = 0; i < services.size(); i++) {
            name.setCellValueFactory(new PropertyValueFactory<Service, String>("serviceName"));
            price.setCellValueFactory(new PropertyValueFactory<Service, Double>("servicePrice"));
            master.setCellValueFactory(new PropertyValueFactory<Service, String>("master"));
        }
        table.setItems(serviceList);
    }

    private void initData(ArrayList<Service> services) {

        for (int i = 0; i < services.size(); i++) {
            serviceList.add(services.get(i));
        }
    }

    @FXML
    public void selectMaster(javafx.scene.input.MouseEvent mouseEvent) {
        Service selectService = (Service) table.getSelectionModel().getSelectedItem();
        serviceModal = selectService;
    }

    ArrayList<Service> services = serviceData.getData();

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
        createTable(services);
    }

}
