package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Enums.RecordTime;
import Models.Record;
import Models.RecordData;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import static Controllers.ClientRecordController.serviceList;
import static Controllers.LoginController.idClient;
import static Controllers.LoginController.recordData;

public class AddClientRecordController {

    Response resp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel;

    @FXML
    private DatePicker date;


    @FXML
    private Button ok;

    @FXML
    private Label response;

    @FXML
    private ComboBox<String> service;

    @FXML
    private ComboBox<String> time;

    @FXML
    void Cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Record record = new Record();
        record.setDate(String.valueOf(date.getValue()));
        record.setTime(time.getValue());

        for (int i = 0; i < serviceList.getData().size(); i++) {
            System.out.println(serviceList.getData().get(i).getServiceId());
            if (service.getValue().equals(serviceList.getData().get(i).getServiceName())) {
                record.setService(serviceList.getData().get(i).getServiceName());
                record.setServiceId(serviceList.getData().get(i).getServiceId());
                record.setMasterId(serviceList.getData().get(i).getMasterId());
            }
        }
        record.setClientId(idClient);

        Request request = new Request(RequestType.CLIENT_RECORD_ADD, record);
        ClientSocket.send(request);
        resp = ClientSocket.listen();
        System.out.println(resp.getResponseType());
        if (resp.getResponseType().equals(ResponseType.ERROR)) {
            response.setText(new Gson().fromJson(resp.getResponseMessage(), String.class));
        } else if (resp.getResponseType().equals(ResponseType.Ok)){
            response.setText("Запись успешно добавлена");
            recordData = new Gson().fromJson(resp.getResponseMessage(), RecordData.class);
            Cancel(event);
        }
    }

    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> timeList = FXCollections.observableArrayList(RecordTime.NINE.getTime(), RecordTime.ELEVEN.getTime(), RecordTime.THIRTEEN.getTime(), RecordTime.FIFTEEN.getTime(), RecordTime.SEVENTEEN.getTime());

    @FXML
    void initialize() {
        for (int i = 0; i < serviceList.getData().size(); i++) {
            list.add(serviceList.getData().get(i).getServiceName());
        }
        service.setItems(list);
        time.setItems(timeList);

    }

}
