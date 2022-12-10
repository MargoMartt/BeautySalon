package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.ClientController.financeData;
import static Controllers.LoginController.UsersNameSurname;

public class ClientFinanceController {
    FXMLLoader loader = new FXMLLoader();
    private Stage stage = new Stage();

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Label balance;

    @FXML
    private Label certificate;

    @FXML
    private Label discont;

    @FXML
    private Button gift;
    @FXML
    private Button byecertificate;
    @FXML
    void AddBalance(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/balance.fxml"));
        stage.setTitle("Редактирование клиента");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();
    }


    @FXML
    void ByeCertificate(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/byecertificate.fxml"));
        stage.setTitle("Покупка сертификата");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();

        balance.setText(String.valueOf(financeData.getBalance()));
        certificate.setText(String.valueOf(financeData.getCertificate()));
    }

    @FXML
    void Gift(ActionEvent event) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gift.fxml"));
        stage.setTitle("Подарить сертификат");
        stage.setMinHeight(500);
        stage.setMinWidth(500);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.showAndWait();

        certificate.setText(String.valueOf(financeData.getCertificate()));
    }

    @FXML
    void onBackButtonClick(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("client.fxml"));

        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
        balance.setText(String.valueOf(financeData.getBalance()));
        certificate.setText(String.valueOf(financeData.getCertificate()));
        discont.setText(String.valueOf(financeData.getDiscount()));
    }

}
