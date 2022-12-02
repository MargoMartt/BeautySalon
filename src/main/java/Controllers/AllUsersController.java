package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.User;
import Models.UserData;
import TCP.*;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.AdminController.userData;

public class AllUsersController {
    static User userModal = new User();
    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    private Button delete;

    @FXML
    private Button edit;

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
    private TableColumn<User, String> login;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableColumn<User, String> role;

    @FXML
    private TableColumn<User, String> surname;

    @FXML
    private TableView<User> table;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        loader.setLocation(getClass().getClassLoader().getResource("admin.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void DeleteUser(ActionEvent event) throws IOException, ClassNotFoundException {
        Response response;
        Request request = new Request(RequestType.DELETE_USER, userModal);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(response.getResponseMessage());
        userList.clear();
        userData = new Gson().fromJson(response.getResponseMessage(), UserData.class);
        createTable(userData.getData());
    }

    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();

    @FXML
    void EditUser(ActionEvent event) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/edituser.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(500);
            stage.setMinWidth(500);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();
            userList.clear();
            createTable(userData.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<User> users = userData.getData();

    @FXML
    void initialize() {
        createTable(users);
    }

    void createTable(ArrayList<User> users) {
        initData(users);
        for (int i = 0; i < users.size(); i++) {
            surname.setCellValueFactory(new PropertyValueFactory<User, String>("userSurname"));
            name.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
            login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
            password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
            role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        }
        table.setItems(userList);
    }

    private void initData(ArrayList<User> users) {

        for (int i = 0; i < users.size(); i++) {
            userList.add(users.get(i));
        }
    }

    public void selectUser(javafx.scene.input.MouseEvent mouseEvent) {
        User selectUser = (User) table.getSelectionModel().getSelectedItem();
        userModal = selectUser;
    }
}
