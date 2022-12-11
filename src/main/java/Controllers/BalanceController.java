package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Finance;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import TCP.ResponseType;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Controllers.ClientController.financeData;

public class BalanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel;

    @FXML
    private TextField balance;

    @FXML
    private Button ok;

    @FXML
    private Label response;

    @FXML
    void Cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    Response resp;
    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        financeData.setBalance(Double.valueOf(balance.getText()));

        Request request = new Request(RequestType.BALANCE, financeData);
        ClientSocket.send(request);
        resp = ClientSocket.listen();

        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            response.setText(new Gson().fromJson(resp.getResponseMessage(), String.class));
        } else if (resp.getResponseType().equals(ResponseType.Ok)){
            financeData = new Gson().fromJson(resp.getResponseMessage(), Finance.class);
            Cancel(event);
        }
    }

    @FXML
    void initialize() {

    }

}
