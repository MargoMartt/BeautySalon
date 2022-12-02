package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Master;
import Models.Service;
import Models.ServiceData;
import TCP.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Controllers.AdminController.masterData;
import static Controllers.AdminController.serviceData;

public class ServiceController {
    @FXML
    static Service serviceModal = new Service();

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
    void AddService(ActionEvent event) {

    }

    @FXML
    void DeleteService(ActionEvent event) {

    }

    @FXML
    void EditService(ActionEvent event) {

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
        createTable(services);
    }

}
