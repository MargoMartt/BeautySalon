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

import static Controllers.AdminController.financeData;
import static Controllers.AdminController.masterData;
import static Controllers.ServiceController.serviceModal;

public class FinanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Finance, String> balance;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Finance, Integer> discount;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Finance, Integer> certificate;

    @FXML
    private TableView<Finance> table;

    @FXML
    private TableColumn<Finance, String> user;

    @FXML
    void DeleteService(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.DELETE_FINANCE, financeModal);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(response.getResponseMessage());
        financeData = new Gson().fromJson(response.getResponseMessage(), FinanceData.class);
        financeList.clear();
        createTable(financeData.getData());
    }

    @FXML
    void EditService(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/financeadd.fxml"));
        stage.setTitle("Редактирование финансовых сведений");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
        financeList.clear();
        createTable(financeData.getData());
    }

    static Finance financeModal = new Finance();
    private ObservableList<Finance> financeList = FXCollections.observableArrayList();
    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();
    public static ServiceData serviceData = new ServiceData();

    Response response;
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

    void createTable(ArrayList<Finance> finances) {
        initData(finances);
        for (int i = 0; i < finances.size(); i++) {
            user.setCellValueFactory(new PropertyValueFactory<Finance, String>("userName"));
            balance.setCellValueFactory(new PropertyValueFactory<Finance, String>("balance"));
            discount.setCellValueFactory(new PropertyValueFactory<Finance, Integer>("discount"));
            certificate.setCellValueFactory(new PropertyValueFactory<Finance, Integer>("certificate"));
        }
        table.setItems(financeList);
    }

    private void initData(ArrayList<Finance> finances) {

        for (int i = 0; i < finances.size(); i++) {
            financeList.add(finances.get(i));
        }
    }

    @FXML
    public void selectFinance(javafx.scene.input.MouseEvent mouseEvent) {
        Finance selectFinance = (Finance) table.getSelectionModel().getSelectedItem();
        financeModal = selectFinance;
    }
    ArrayList<Finance> finances = financeData.getData();

    @FXML
    void initialize() {
        createTable(finances);

    }

}