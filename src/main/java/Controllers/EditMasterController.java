package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Master;
import Models.MasterData;
import TCP.*;
import Utility.ClientSocket;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Controllers.AdminController.masterData;
import static Controllers.MastersController.masterModal;

public class EditMasterController {
    Response response;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField activity;

    @FXML
    private Button cancel;

    @FXML
    private TextField experience;

    @FXML
    private TextField name;

    @FXML
    private Button ok;

    @FXML
    private TextField surname;

    @FXML
    void Cancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    Master master = new Master();
    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        master.setMasterId(masterModal.getMasterId());
        master.setMasterSurname(surname.getText());
        master.setMasterName(name.getText());
        master.setActivity(activity.getText());
        master.setWorkExperience(Integer.parseInt(experience.getText()));
        Request request = new Request(RequestType.UPDATE_MASTER, master);
        System.out.println(masterData.getData().toString());
        ClientSocket.send(request);
        response = ClientSocket.listen();
        masterData = new Gson().fromJson(response.getResponseMessage(), MasterData.class);
        Cancel(event);
    }

    @FXML
    void initialize() {
        name.setText(masterModal.getMasterName());
        surname.setText(masterModal.getMasterSurname());
        experience.setText(String.valueOf(masterModal.getWorkExperience()));
        activity.setText(masterModal.getActivity());
    }

}
