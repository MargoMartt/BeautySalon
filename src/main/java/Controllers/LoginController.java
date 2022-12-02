
package Controllers;

import Entities.UsersEntity;
import Entities.UsersHasRoleEntity;
import Models.User;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    Response resp;

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML
    private TextField login;
    @FXML
    private Label response;
    Gson gson;

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

    UsersHasRoleEntity usersHasRole = new UsersHasRoleEntity();

    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        UsersEntity user = new UsersEntity();
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        Request request = new Request(RequestType.LOGIN, user);
        ClientSocket.send(request);

        resp = ClientSocket.listen();
        if (resp.getResponseType().equals(ResponseType.Ok)) {
            User userData =  new Gson().fromJson(resp.getResponseMessage(), User.class);

//            ArrayList<UsersHasRoleEntity> roles = RoleHasUsersService.findAllRoles();
//            System.out.println(roles);
//            for (int i = 0; i < roles.size(); i++) {
//                System.out.println(user.getIdUser());
//                if (user.getIdUser() == roles.get(i).getUsersIdUser()) {
//                    id = roles.get(i).getRoleIdRole();
//                }
//            }
            System.out.println(userData.getIdRole());
            if (userData.getIdRole() == 3) {
                login.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("admin.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            response.setText("Неверный логин или пароль");
        }
    }

}
