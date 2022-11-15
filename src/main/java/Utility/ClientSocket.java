package Utility;

import TCP.Request;
import TCP.RequestType;
import TCP.Response;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
   private static Gson gson;
    private static Socket instance;

    public static void setInstance() throws IOException {
        Socket client = new Socket("127.0.0.1", 2525);
        instance = client;
    }

    public static Socket getInstance() {
        return instance;
    }

    public static <T> void send(T data) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(instance.getOutputStream());
        objectOutputStream.writeObject(new Gson().toJson(data));
    }

    public static void listen() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(instance.getInputStream());
        String message = String.valueOf(objectInputStream.readObject());
        Response response = gson.fromJson(message, Response.class);
        System.out.println(objectInputStream.readObject());
    }

    public ClientSocket() throws IOException {
    }
}
