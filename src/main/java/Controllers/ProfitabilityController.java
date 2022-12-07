package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.Master;
import Models.Profitability;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Controllers.AdminController.masterData;
import static Controllers.SalonController.profitabilityData;

public class ProfitabilityController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Profitability, Double> cost;

    @FXML
    private TableColumn<Profitability, Integer> count;

    @FXML
    private TableColumn<Profitability, Double> finalcost;

    @FXML
    private TableColumn<Profitability, Double> price;

    @FXML
    private TableColumn<Profitability, String> service;

    @FXML
    private Label sum;

    @FXML
    private TableView<Profitability> table;

    @FXML
    private Label text;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    static Profitability profitabilityModal = new Profitability();
    private ObservableList<Profitability> profitabilityList = FXCollections.observableArrayList();

    void createTable(ArrayList<Profitability> profitabilities) {
        initData(profitabilities);
        for (int i = 0; i < profitabilities.size(); i++) {
            cost.setCellValueFactory(new PropertyValueFactory<Profitability, Double>("cost"));
            count.setCellValueFactory(new PropertyValueFactory<Profitability, Integer>("count"));
            price.setCellValueFactory(new PropertyValueFactory<Profitability, Double>("price"));
            finalcost.setCellValueFactory(new PropertyValueFactory<Profitability, Double>("finalCost"));
            service.setCellValueFactory(new PropertyValueFactory<Profitability, String>("service"));
        }
        table.setItems(profitabilityList);
    }

    private void initData(ArrayList<Profitability> profitabilities) {

        for (int i = 0; i < profitabilities.size(); i++) {
            profitabilityList.add(profitabilities.get(i));
        }
    }

    @FXML
    public void selectMaster(javafx.scene.input.MouseEvent mouseEvent) {
        Profitability selectProfitability = (Profitability) table.getSelectionModel().getSelectedItem();
        profitabilityModal = selectProfitability;
    }

    ArrayList<Profitability> profitabilities = profitabilityData.getData();

    @FXML
    void initialize() {
        createTable(profitabilities);
        sum.setText(String.valueOf(profitabilityData.getSum()));

    }

}
