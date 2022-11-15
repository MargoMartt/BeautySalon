
package Controllers;

import Entities.UsersEntity;
import Enums.Roles;
import TCP.RegistrationPayload;
import TCP.Request;
import TCP.RequestType;
import Utility.ClientSocket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML
    private TextField login;

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
    void onLoginButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        UsersEntity user = new UsersEntity();
        user.setLogin(login.getText());
        user.setPassword(password.getText());

        Request request = new Request(RequestType.LOGIN, user);
        ClientSocket.send(request);
        ClientSocket.listen();
    }

}
