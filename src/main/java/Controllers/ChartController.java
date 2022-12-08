package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Models.Profitability;
import Models.ProfitabilityData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Controllers.LoginController.UsersNameSurname;
import static Controllers.SalonController.profitabilityData;

public class ChartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private NumberAxis profit;

    @FXML
    private BarChart<Profitability, String> profitability;

    @FXML
    private CategoryAxis service;
    @FXML
    void onBackButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
        XYChart.Series series = new XYChart.Series<>();
        for (Profitability profit : profitabilityData.getData()) {
            series.getData().add(new XYChart.Data(profit.getService(), profit.getFinalCost()));
        }
        profitability.getData().addAll(series);
    }

}
