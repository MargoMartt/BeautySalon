package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.ProfitabilityData;
import Models.Salon;
import Models.ServiceData;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.LoginController.*;

public class SalonController {
    Response response;
    public static Salon salon = new Salon();
    public static ProfitabilityData profitabilityData = new ProfitabilityData();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Button graph;

    @FXML
    private Button profitability;

    @FXML
    private Button report;

    @FXML
    void Graph(ActionEvent event) throws IOException, ClassNotFoundException {
        stage = new Stage();
        String message = "График рентабельности салона";
        Request request = new Request(RequestType.PROFITABILITY, message);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        profitabilityData = new Gson().fromJson(response.getResponseMessage(), ProfitabilityData.class);

        Parent root = FXMLLoader.load(getClass().getResource("/chart.fxml"));
        stage.setTitle("График");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    void Profitability(ActionEvent event) throws IOException, ClassNotFoundException {
        stage = new Stage();
        String message = "Рентабельность салона";
        Request request = new Request(RequestType.PROFITABILITY, message);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        profitabilityData = new Gson().fromJson(response.getResponseMessage(), ProfitabilityData.class);

        Parent root = FXMLLoader.load(getClass().getResource("/profitability.fxml"));
        stage.setTitle("Рентабельность");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();

    }

    @FXML
    void Report(ActionEvent event) throws ClassNotFoundException {
        try {
            stage = new Stage();
            String message = "Составление отчета";
            Request request = new Request(RequestType.SALON_DATA, message);
            ClientSocket.send(request);
            response = ClientSocket.listen();
            salon = new Gson().fromJson(response.getResponseMessage(), Salon.class);

            Parent root = FXMLLoader.load(getClass().getResource("/report.fxml"));
            stage.setTitle("Отчет");
            stage.setMinHeight(500);
            stage.setMinWidth(500);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
    }

}
