package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Master;
import Models.MasterData;
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

import static Controllers.ServiceController.listMasters;
import static Controllers.ServiceController.serviceModal;
import static Controllers.AdminController.serviceData;

public class AddServiceController {
    Response resp;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<String> master;

    @FXML
    private TextField name;

    @FXML
    private Button ok;

    @FXML
    private TextField price;

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
        Service service = new Service();
        service.setServiceId(serviceModal.getServiceId());
        service.setServiceName(name.getText());
        service.setServicePrice(Double.parseDouble(price.getText()));

        for (int i = 0; i < listMasters.getData().size(); i++) {
            if (master.getValue() == listMasters.getData().get(i).getMasterInfo())
                service.setMasterId(listMasters.getData().get(i).getId());
        }

        Request request = new Request(RequestType.ADD_SERVICE, service);
        ClientSocket.send(request);
        resp = ClientSocket.listen();
        if (resp.getResponseType().equals(ResponseType.Ok)) {
            serviceData = new Gson().fromJson(resp.getResponseMessage(), ServiceData.class);
            Cancel(event);
        } else
            response.setText(new Gson().fromJson(resp.getResponseMessage(), String.class));
    }

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        for (int i = 0; i < listMasters.getData().size(); i++) {
            list.add(listMasters.getData().get(i).getMasterInfo());
        }
        master.setItems(list);
        name.setText(serviceModal.getServiceName());
        price.setText(String.valueOf(serviceModal.getServicePrice()));

    }


}
