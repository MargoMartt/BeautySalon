package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Models.*;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.LoginController.UsersNameSurname;
import static Controllers.LoginController.userData;

public class AdminUsersController {
    Response response;
    static User userModal = new User();
    private ObservableList<User> clientList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private Button edit;


    @FXML
    private TableColumn<User, String> login;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> surname;

    @FXML
    private TableView<User> table;

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        loader.setLocation(getClass().getClassLoader().getResource("adminsalona.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void DeleteUser(ActionEvent event) throws IOException, ClassNotFoundException {
        Request request = new Request(RequestType.DELETE_USER, userModal);
        ClientSocket.send(request);
        response = ClientSocket.listen();
        System.out.println(response.getResponseMessage());
        clientList.clear();
        userData = new Gson().fromJson(response.getResponseMessage(), UserData.class);
        createTable(userData.getData());
    }

    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();

    @FXML
    void EditUser(ActionEvent event) {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/editadminuser.fxml"));
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(500);
            stage.setMinWidth(500);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();
            clientList.clear();
            createTable(userData.getData());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<User> users = userData.getData();

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
        createTable(users);
    }

    void createTable(ArrayList<User> users) {
        initData(users);
        for (int i = 0; i < users.size(); i++) {
            surname.setCellValueFactory(new PropertyValueFactory<User, String>("userSurname"));
            name.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
            login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        }
        table.setItems(clientList);
    }

    private void initData(ArrayList<User> users) {

        for (int i = 0; i < users.size(); i++) {
            clientList.add(users.get(i));
        }
    }

    public void selectUser(javafx.scene.input.MouseEvent mouseEvent) {
        User selectUser = (User) table.getSelectionModel().getSelectedItem();
        userModal = selectUser;
    }


}
