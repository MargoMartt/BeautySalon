package Start;

import Entities.UsersEntity;
import Entities.UsersHasRoleEntity;
import Services.RoleHasUsersService;
import Services.UsersService;
import Utility.ClientSocket;
import Utility.ClientThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        UsersEntity admin = new UsersEntity();
        admin.setUserName("Маргарита");
        admin.setUserSurname("Мартинкевич");
        admin.setLogin("ritamart");
        admin.setPassword("ritamart");

        System.out.println("Start");
        ClientSocket.setInstance();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Beauty Salon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
