package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.RoleEntity;
import Entities.UsersEntity;
import Enums.Roles;
import Start.Client;
import TCP.RegistrationPayload;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import TCP.RegistrationPayload;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private ComboBox<String> roles;

    @FXML
    private TextField surname;

    public RegistrationController() throws IOException, ClassNotFoundException {

    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("main.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void onRegisterButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        RegistrationPayload payload = new RegistrationPayload();
        payload.setUserName(name.getText());
        payload.setUserSurname(surname.getText());
        payload.setLogin(login.getText());
        payload.setPassword(password.getText());
        for (Roles r : Roles.values()) {
            if (r.getValue() == roles.getValue()) {
               int id = r.getId();
                payload.setRole(id);
            }
        }
        System.out.println(payload.toString());

        Request request = new Request(RequestType.REGISTER, payload);
        ClientSocket.send(request);
        ClientSocket.listen();
//        register.getScene().getWindow().hide();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getClassLoader().getResource("main.fxml"));
//
//        loader.load();
//        Parent root = loader.getRoot();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show();

//        Response response = new Gson().fromJson()
    }

    @FXML
    void initialize() {
        ArrayList<String> list = new ArrayList<>();
        list.add((Roles.ADMIN.getValue()));
        System.out.println(Roles.ADMIN.getValue());
        list.add((Roles.VISITOR.getValue()));
        list.add((Roles.SALON_ADMIN.getValue()));
        roles.setItems(FXCollections.observableList(list));
    }

}
