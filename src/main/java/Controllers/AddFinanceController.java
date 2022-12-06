package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Enums.Certificate;
import Enums.Discount;
import Enums.Roles;
import Models.Finance;
import Models.FinanceData;
import Models.Service;
import Models.ServiceData;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Controllers.AdminController.financeData;
import static Controllers.AdminController.serviceData;
import static Controllers.FinanceController.financeModal;
import static Controllers.ServiceController.listMasters;
import static Controllers.ServiceController.serviceModal;

public class AddFinanceController {
    Response resp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField amount;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<Integer> certificate;

    @FXML
    private ComboBox<Integer> discount;

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

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Finance finance = new Finance();
        finance.setBalance(amount.getText());
        finance.setBonusId(financeModal.getBonusId());
        finance.setUserId(financeModal.getUserId());

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

        if (discount.getValue() != null) {
            cost = discount.getValue();
            for (Discount d : Discount.values()) {
                if (d.getCost() == cost) {
                    finance.setDiscount(d.getCost());
                    break;
                }
            }
        } else finance.setDiscount(0);

        Request request = new Request(RequestType.UPDATE_FIANCE, finance);
        ClientSocket.send(request);
        resp = ClientSocket.listen();
        financeData = new Gson().fromJson(resp.getResponseMessage(), FinanceData.class);
        Cancel(event);
    }

    ObservableList<Integer> certificateList = FXCollections.observableArrayList(Certificate.FIFTY.getCost(), Certificate.TWENTY.getCost(), Certificate.ONE_HUNDRED.getCost(), Certificate.TWO_HUNDRED.getCost());
    ObservableList<Integer> discountList = FXCollections.observableArrayList(Discount.TWO.getCost(), Discount.FIVE.getCost(), Discount.TEN.getCost(), Discount.TWENTY.getCost(), Discount.FIFTY.getCost());

    @FXML
    void initialize() {
        certificate.setItems(certificateList);
        discount.setItems(discountList);
        amount.setText(financeModal.getBalance());
    }

}
