package Start;

import Utility.ClientThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainServer {
    private static ServerSocket serverSocket;
    private static ClientThread clientThread;
    private static Thread thread;
    private static List<Socket> sockets = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(2526);
        while (true) {
            System.out.println("Hi");
            for (Socket socket : sockets) {
                if ((socket.isClosed())) {
                    sockets.remove(socket);
                    continue;
                }
                String socketInf = "Client" + socket.getInetAddress() + " is " + socket.getPort();
                System.out.println(socketInf);

            }
            Socket socket = serverSocket.accept();

            sockets.add(socket);
            clientThread = new ClientThread(socket);
            thread = new Thread(clientThread);
            thread.start();
            System.out.flush();
        }
    }
}
