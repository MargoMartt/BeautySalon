
package Controllers;

import Entities.UsersEntity;
import Entities.UsersHasRoleEntity;
import Models.*;
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
    public static FinanceData financeData = new FinanceData();
    public static ServiceData serviceData = new ServiceData();
    public static UserData userData = new UserData();
    public static MasterData masterData = new MasterData();
    public  static RecordData recordData = new RecordData();

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

    public static String loginClient;
    public static int idClient;
    public static String nameClient;
    public static String surnameClient;

    public static int loginId = 0;
    public static String UsersNameSurname = "";

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

        resp = ClientSocket.listen();
        if (resp.getResponseType().equals(ResponseType.Ok)) {
            User userData =  new Gson().fromJson(resp.getResponseMessage(), User.class);
            System.out.println(userData.getIdRole());

            if (userData.getIdRole() == 3) {
                loginId = 3;
                UsersNameSurname = userData.getUserName() + " " + userData.getUserSurname();
                login.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("admin.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
            if (userData.getIdRole() == 2) {
                loginId = 2;
                UsersNameSurname = userData.getUserName() + " " + userData.getUserSurname();
                login.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("adminsalona.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
            if (userData.getIdRole() == 1) {
                loginId = 1;
                idClient = userData.getUserId();
                loginClient = userData.getLogin();
                nameClient = userData.getUserName();
                surnameClient = userData.getUserSurname();
                UsersNameSurname = userData.getUserName() + " " + userData.getUserSurname();
                login.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("client.fxml"));

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else
        {
            response.setText("Неверный логин или пароль");
        }
    }

}
