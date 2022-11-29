package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import TCP.RegistrationPayload;
import TCP.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static Controllers.AdminController.user;

public class AllUsersController {
    private ObservableList<RegistrationPayload> userList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> action;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private TableColumn<RegistrationPayload, String> login;

    @FXML
    private TableColumn<RegistrationPayload, String> name;

    @FXML
    private TableColumn<RegistrationPayload, String> password;

    @FXML
    private TableColumn<RegistrationPayload, Integer> role;

    @FXML
    private TableColumn<RegistrationPayload, String> surname;

    @FXML
    private TableView<RegistrationPayload> table;

    @FXML
    void onBackButtonClick(ActionEvent event) {

    }

    ArrayList<RegistrationPayload> users = user.getData();

    @FXML
    void initialize() {
//        RegistrationPayload registrationPayload = new RegistrationPayload();
        initData();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(user.getData().get(i).getUserName());
            surname.setCellValueFactory(new PropertyValueFactory<>("userSurname"));
            name.setCellValueFactory(new PropertyValueFactory<RegistrationPayload, String>("userName"));
            login.setCellValueFactory(new PropertyValueFactory<RegistrationPayload, String>("login"));
            password.setCellValueFactory(new PropertyValueFactory<RegistrationPayload, String>("password"));
//        role.setCellValueFactory(new PropertyValueFactory<UserData, Integer>(registrationPayload.getIdRole());
        }
        table.setItems(userList);

    }

    private void initData() {

        for (int i = 0; i < users.size(); i++) {
            userList.add(users.get(i));
        }
//        for (UserData us : user.getData()) {
//            userList.add(us);
//        }

    }

}
