package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TCP.*;
import Utility.ClientSocket;
import com.google.gson.Gson;
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
    private Label response;

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
    private TextField balance;
//    @FXML
//    private ComboBox<String> roles;

    @FXML
    private TextField surname;

    public RegistrationController() throws IOException, ClassNotFoundException {
    }

    private Response resp;
    private FXMLLoader loader = new FXMLLoader();

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
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
        payload.setBalance(balance.getText());

        Request request = new Request(RequestType.REGISTER, payload);
        ClientSocket.send(request);
        resp = ClientSocket.listen();
        if (resp.getResponseType().equals(ResponseType.Ok)) {
            register.getScene().getWindow().hide();
            loader.setLocation(getClass().getClassLoader().getResource("main.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println(new Gson().fromJson(resp.getResponseMessage(), Response.class));
            response.setText(resp.getResponseMessage());
        }
    }

    @FXML
    void initialize() {
    }

}
