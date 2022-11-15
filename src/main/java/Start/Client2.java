package Start;

import Utility.ClientThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Client2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("Start");
        Socket client = new Socket("127.0.0.1", 2525);
        ClientThread clientThread = new ClientThread(client);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Beauty Salon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
