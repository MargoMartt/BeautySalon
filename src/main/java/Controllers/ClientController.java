package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Finance;
import Models.FinanceData;
import Models.User;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Controllers.LoginController.*;

public class ClientController {

    Response response;
    public  static Finance financeData = new Finance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Button finance;

    @FXML
    private Button record;

    @FXML
    private Button users;

    @FXML
    void Finance(ActionEvent event) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setLogin(loginClient);
        Request request = new Request(RequestType.CLIENT_FINANCE, user);
        ClientSocket.send(request);

        response = ClientSocket.listen();
        System.out.println(response);
        financeData = new Gson().fromJson(response.getResponseMessage(), Finance.class);
        System.out.println(financeData.toString());
        System.out.println(response.getResponseMessage());

        finance.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("clientfinance.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void Records(ActionEvent event) throws IOException {
        record.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("clientrecord.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void Users(ActionEvent event) throws IOException {
        users.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("clientdata.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("login.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
    }

}
