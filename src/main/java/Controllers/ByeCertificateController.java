package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Enums.Certificate;
import Enums.Discount;
import Models.Finance;
import Models.FinanceData;
import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import TCP.ResponseType;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.persistence.criteria.CriteriaBuilder;

import static Controllers.ClientController.financeData;

public class ByeCertificateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label balance;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<Integer> certificate;

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

    ObservableList<Integer> certificateList = FXCollections.observableArrayList(Certificate.TWENTY.getCost(), Certificate.FIFTY.getCost(), Certificate.ONE_HUNDRED.getCost(), Certificate.TWO_HUNDRED.getCost());
    Response resp;

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Finance finance = new Finance();
        finance.setBalance(financeData.getBalance());
        finance.setBonusId(financeData.getBonusId());
        finance.setUserId(financeData.getUserId());

        int cost = 0;
        if (certificate.getValue() != null) {
            cost = certificate.getValue();
            for (Certificate c : Certificate.values()) {
                if (c.getCost() == cost) {
                    finance.setCertificate(c.getCost());
                    break;
                }
            }
        } else finance.setCertificate(cost);


        Request request = new Request(RequestType.BYE_CERTIFICATE, finance);
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
        balance.setText(String.valueOf(financeData.getBalance()));
        certificate.setItems(certificateList);
    }

}
