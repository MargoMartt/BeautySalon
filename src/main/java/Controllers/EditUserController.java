package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Enums.Roles;
import Models.User;
import Models.UserData;
import TCP.*;
//import Utility.ClientSocket;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static Controllers.AdminController.userData;
import static Controllers.AllUsersController.userModal;

public class EditUserController {

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
    private PasswordField password;

    @FXML
    private ComboBox<String> role;

    ObservableList<String> list = FXCollections.observableArrayList(Roles.VISITOR.getValue(), Roles.ADMIN.getValue(), Roles.SALON_ADMIN.getValue());
    @FXML
    private TextField surname;

    @FXML
    void Cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Role(ActionEvent event) {

    }

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Response response = new Response<>();
        for (Roles r : Roles.values()) {
            if (r.getValue() == role.getValue()) {
                int id = r.getId();
                user.setIdRole(id);
            }
        }
        user.setUserName(name.getText());
        user.setUserSurname(surname.getText());
        user.setPassword(password.getText());
        user.setLogin(userModal.getLogin());
        System.out.println(user.toString());

        Request request = new Request(RequestType.UPDATE_USER, user);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(new Gson().fromJson(response.getResponseMessage(), Response.class));
        userData = new Gson().fromJson(response.getResponseMessage(), UserData.class);
        Cancel(event);
    }

    private User user = new User();

    @FXML
    void initialize() {
        role.setItems(list);
        role.setValue(Roles.VISITOR.getValue());
        name.setText(userModal.getUserName());
        surname.setText(userModal.getUserSurname());
        password.setText(userModal.getPassword());

//        list.add((Roles.ADMIN.getValue()));
//        list.add((Roles.VISITOR.getValue()));
//        list.add((Roles.SALON_ADMIN.getValue()));

//        role.setItems(FXCollections.observableList(list));
    }

}
