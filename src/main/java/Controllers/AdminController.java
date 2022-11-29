package Controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.UsersEntity;
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
    void Masters(ActionEvent event) {

    }

    @FXML
    void Records(ActionEvent event) {

    }

    @FXML
    void Salon(ActionEvent event) {

    }

    @FXML
    void Services(ActionEvent event) {

    }
    public static UserData user = new UserData();
    @FXML
    void Users(ActionEvent event) throws IOException, ClassNotFoundException {

        Request request = new Request(RequestType.VIEW_USERS, "Посмотреть данные пользователей");
        ClientSocket.send(request);


        response = ClientSocket.listen();
        System.out.println(response);
        user = new Gson().fromJson(response.getResponseMessage(), UserData.class);
        System.out.println(user.toString());
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
        loader.setLocation(getClass().getClassLoader().getResource("admin.fxml"));

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
