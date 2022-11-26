package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminController {

    @FXML // fx:id="adminData"
    private Label adminData; // Value injected by FXMLLoader

    @FXML // fx:id="back"
    private Button back; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private TableColumn<?, ?> login; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TableColumn<?, ?> name; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private TableColumn<?, ?> password; // Value injected by FXMLLoader

    @FXML // fx:id="response"
    private Label response; // Value injected by FXMLLoader

    @FXML // fx:id="role"
    private TableColumn<?, ?> role; // Value injected by FXMLLoader

    @FXML // fx:id="save"
    private TableColumn<?, ?> save; // Value injected by FXMLLoader

    @FXML // fx:id="surname"
    private TableColumn<?, ?> surname; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<?> table; // Value injected by FXMLLoader

    @FXML
    void onBackButtonClick(ActionEvent event) {

    }

}
