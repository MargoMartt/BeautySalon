package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Controllers.LoginController.UsersNameSurname;
import static Controllers.SalonController.salon;
import static Controllers.ServiceController.serviceModal;

public class ReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label adminData;

    @FXML
    private Button back;

    @FXML
    private Label bonus;

    @FXML
    private Label certificate;

    @FXML
    private Label client;

    @FXML
    private Label discount;

    @FXML
    private Label masters;

    @FXML
    private Label record;

    @FXML
    private Button save;

    @FXML
    private Label service;

    @FXML
    private Label saved;
    @FXML
    void SaveReport(ActionEvent event) throws IOException {
        File file = new File("report.txt");
//        FileOutputStream fileWriter = new FileOutputStream(file);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Общее кол-во клиентов: " + salon.getClientCount() + '\n');
        fileWriter.write("Общее кол-во мастеров: " + salon.getMasterCount() + '\n');
        fileWriter.write("Общее кол-во предоставляемых услуг: " + salon.getServiceCount() + '\n');
        fileWriter.write("Общее кол-во записей: " + salon.getRecordCount() + '\n');
        fileWriter.write("Общее кол-во клиентов, участвующих в бонусной программе: " + salon.getBonusCount() + '\n');
        fileWriter.write("Общее кол-во проданных сертификатов: " + salon.getCertificate() + '\n');
        fileWriter.write("Общее кол-во клиентов, имеющих скидку: " + salon.getDiscount() + '\n');
        fileWriter.close();

        saved.setText("Файл сохранен");

    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        adminData.setText(UsersNameSurname);
        bonus.setText(String.valueOf(salon.getBonusCount()));
        client.setText(String.valueOf(salon.getClientCount()));
        masters.setText(String.valueOf(salon.getMasterCount()));
        record.setText(String.valueOf(salon.getRecordCount()));
        service.setText(String.valueOf(salon.getServiceCount()));
        certificate.setText(String.valueOf(salon.getCertificate()));
        discount.setText(String.valueOf(salon.getDiscount()));
    }

}
