package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.User;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import TCP.ResponseType;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import static Controllers.LoginController.*;
import static Controllers.LoginController.surnameClient;

public class EditPasswordController {
    Response resp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel;

    @FXML
    private PasswordField newpassword;

    @FXML
    private Button ok;

    @FXML
    private PasswordField oldpassword;


    @FXML
    private Label response;

    @FXML
    void Cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private User user = new User();

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        user.setPassword(oldpassword.getText());
        user.setNewPassword(newpassword.getText());
        user.setLogin(loginClient);

        Request request = new Request(RequestType.UPDATE_CLIENT_PASSWORD, user);
        ClientSocket.send(request);
        resp = ClientSocket.listen();

        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            response.setText(new Gson().fromJson(resp.getResponseMessage(), String.class));
        } else if (resp.getResponseType().equals(ResponseType.Ok)){
            Cancel(event);
        }
    }

    @FXML
    void initialize() {

    }

}
