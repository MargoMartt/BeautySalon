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
import javafx.scene.control.*;
import javafx.stage.Stage;

import static Controllers.AdminController.masterData;
import static Controllers.MastersController.masterModal;

public class AddMasterController {
    Response resp;


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
    private Label response;
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

    @FXML
    void Role(ActionEvent event) {

    }

    @FXML
    void onOkButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        Master master = new Master();
        master.setMasterName(name.getText());
        master.setMasterSurname(surname.getText());
        master.setActivity(activity.getText());
        master.setWorkExperience(Integer.parseInt(experience.getText()));

        Request request = new Request(RequestType.CREATE_MASTER, master);
        ClientSocket.send(request);
        resp = ClientSocket.listen();
        if (resp.getResponseType().equals(ResponseType.Ok)){
            masterData = new Gson().fromJson(resp.getResponseMessage(), MasterData.class);
            Cancel(event);
        }
        else
        response.setText(new Gson().fromJson(resp.getResponseMessage(), String.class));
    }

    @FXML
    void initialize() {
    }

}


