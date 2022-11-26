package Utility;

import Entities.RoleEntity;
import Entities.UsersEntity;
import Entities.UsersHasRoleEntity;
import Services.RoleHasUsersService;
import Services.RoleService;
import Services.UsersService;
import TCP.Request;
import TCP.Response;
import TCP.ResponseType;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private UsersService usersService;
    private RoleService roleService;
    private Response response;
    private Request request;
    private Gson gson;
    private ObjectInputStream inputStream;
    private PrintWriter writer;
    private ObjectOutputStream outputStream;

    public ClientThread(Socket clientSocket) throws IOException {
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new Gson();
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        writer = new PrintWriter(clientSocket.getOutputStream());

    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                System.out.println("hi");
                String message = String.valueOf(inputStream.readObject());
                request = gson.fromJson(message, Request.class);
                String requestMessage = request.getRequestMessage();
                System.out.println(message);

                switch (request.getRequestType()) {
                    case REGISTER: {
                        UsersEntity user = gson.fromJson(requestMessage, UsersEntity.class);
                        System.out.println(requestMessage);
//                        RoleEntity role = gson.fromJson(requestMessage, RoleEntity.class);

                        if (UsersService.findAllUsers().stream().noneMatch((x -> x.getLogin().equals(user.getLogin())))) {
//                        UsersHasRoleEntity entity = new UsersHasRoleEntity(role.getIdRole());
//                        System.out.println(entity.toString());
//                        RoleHasUsersService.saveRole(entity);
                            int id = UsersService.saveUser(user);
//                        entity.setUsersIdUser(id);
//                        RoleHasUsersService.saveRole(entity);
//                        System.out.println(user.toString());
//                        System.out.println(role.getIdRole());
//                            System.out.println(entity.toString());
//                            RoleHasUsersService.saveRole(entity);
                            response = new Response(ResponseType.Ok, "Пользователь зарегистрирован");
                            System.out.println(new Gson().toJson(response));
                            outputStream.writeObject(new Gson().toJson(response));
                        } else {
                            response = new Response(ResponseType.ERROR, "Пользватель уже существует");
                            outputStream.writeObject(new Gson().toJson(response));
                        }
                        break;
                    }
                    case LOGIN: {
                        UsersEntity user = gson.fromJson(requestMessage, UsersEntity.class);
                        UsersEntity respUser = UsersService.findUserLog(user.getLogin());
                        boolean isPasswordValid = respUser != null ? user.getPassword().equals(respUser.getPassword()) : false;

                        if (!isPasswordValid)
                            response = new Response(ResponseType.ERROR, "Неверный логин или пароль");
                        else
                            response = new Response(ResponseType.Ok, respUser);

                        outputStream.writeObject(new Gson().toJson(response));
                    }
                }
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            }
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println("Error connecting!");
            throw new RuntimeException(ex);
        }
    }
}

