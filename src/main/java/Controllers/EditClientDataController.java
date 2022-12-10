package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Enums.Roles;
import Models.User;
import Models.UserData;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Controllers.AllUsersController.userModal;
import static Controllers.LoginController.*;

public class EditClientDataController {
    Response response;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel;

    @FXML
    private TextField name;

    @FXML
    private Button ok;

    @FXML
    private TextField surname;

    @FXML
    void Cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private User user = new User();

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        user.setUserName(name.getText());
        user.setUserSurname(surname.getText());
        user.setLogin(loginClient);

        Request request = new Request(RequestType.UPDATE_CLIENT_DATA, user);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(new Gson().fromJson(response.getResponseMessage(), Response.class));
        user = new Gson().fromJson(response.getResponseMessage(), User.class);
        nameClient = user.getUserName();
        surnameClient = user.getUserSurname();
        UsersNameSurname = nameClient + " " + surnameClient;
        Cancel(event);
    }

    @FXML
    void initialize() {
        name.setText(nameClient);
        surname.setText(surnameClient);
    }

}
