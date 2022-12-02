package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.MasterData;
import Models.ServiceData;
import Models.UserData;
import TCP.*;
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

public class AdminController {
    Response response;
    public static ServiceData serviceData = new ServiceData();
    public static UserData userData = new UserData();
    public static MasterData masterData = new MasterData();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button admin;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Button discont;

    @FXML
    private Button masters;

    @FXML
    private Button record;

    @FXML
    private Button salon;

    @FXML
    private Button services;

    @FXML
    private Button users;

    @FXML
    void Admin(ActionEvent event) {

    }

    @FXML
    void Discont(ActionEvent event) {

    }

    @FXML
    void Masters(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.VIEW_MASTERS, "Посмотреть данные мастеров");
        ClientSocket.send(request);

        response = ClientSocket.listen();
        System.out.println(response);
        masterData = new Gson().fromJson(response.getResponseMessage(), MasterData.class);
        System.out.println(masterData.toString());
        System.out.println(response.getResponseMessage());

        users.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("masters.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void Records(ActionEvent event) {

    }

    @FXML
    void Salon(ActionEvent event) {

    }

    @FXML
    void Services(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.VIEW_SERVICES, "Посмотреть данные услуг");
        ClientSocket.send(request);

        response = ClientSocket.listen();
        System.out.println(response);
        serviceData = new Gson().fromJson(response.getResponseMessage(), ServiceData.class);
        System.out.println(response.getResponseType());
        System.out.println(response.getResponseMessage());

        users.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("service.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void Users(ActionEvent event) throws IOException, ClassNotFoundException {

        Request request = new Request(RequestType.VIEW_USERS, "Посмотреть данные пользователей");
        ClientSocket.send(request);

        response = ClientSocket.listen();
        System.out.println(response);
        userData = new Gson().fromJson(response.getResponseMessage(), UserData.class);
        System.out.println(userData.toString());
        System.out.println(response.getResponseMessage());

        users.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("allusers.fxml"));
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

    }

}
