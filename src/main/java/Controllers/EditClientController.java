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

import static Controllers.LoginController.userData;
import static Controllers.AdminUsersController.userModal;

public class EditClientController {

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

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Response response = new Response<>();
        user.setUserName(name.getText());
        user.setUserSurname(surname.getText());
        user.setLogin(userModal.getLogin());
        System.out.println(user.toString());

        Request request = new Request(RequestType.UPDATE_CLIENT, user);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(new Gson().fromJson(response.getResponseMessage(), Response.class));
        userData = new Gson().fromJson(response.getResponseMessage(), UserData.class);
        Cancel(event);
    }

    private User user = new User();

    @FXML
    void initialize() {
        name.setText(userModal.getUserName());
        surname.setText(userModal.getUserSurname());
    }

}
