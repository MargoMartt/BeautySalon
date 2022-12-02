package Utility;

import Entities.BeautyMastersEntity;
import Entities.UsersEntity;
import Entities.UsersHasRoleEntity;
import Models.*;
import Services.*;
import TCP.*;
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

                        if (UsersService.findAllUsers().stream().noneMatch((x -> x.getLogin().equals(user.getLogin())))) {
                            int id = UsersService.saveUser(user);
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
                        else {
                            int id = 0;
                            for (int i = 0; i < RoleHasUsersService.findAllRoles().size(); i++) {
                                if (RoleHasUsersService.findAllRoles().get(i).getUsersIdUser() == respUser.getIdUser()) {
                                    id = RoleHasUsersService.findAllRoles().get(i).getRoleIdRole();
                                    break;
                                }
                            }
                            User userData = new User(id, user.getUserName(), user.getUserSurname(), user.getLogin(), user.getPassword());
                            response = new Response(ResponseType.Ok, userData);
                        }

                        outputStream.writeObject(new Gson().toJson(response));
                        break;
                    }
                    case VIEW_USERS: {
                        UserData userData = ServerMethods.findAllUsers();
                        response = new Response<>(ResponseType.Ok, userData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case UPDATE_USER: {
                        User user = gson.fromJson(requestMessage, User.class);
                        UsersEntity respUser = UsersService.findUserLog(user.getLogin());
                        respUser.setUserName(user.getUserName());
                        respUser.setUserSurname(user.getUserSurname());
                        respUser.setPassword(user.getPassword());

                        UsersService.updateUser(respUser);
                        UsersHasRoleEntity entity = new UsersHasRoleEntity(respUser.getIdUser(), user.getIdRole());
                        RoleHasUsersService.saveRole(entity);
                        UserData userData = ServerMethods.findAllUsers();
                        response = new Response<>(ResponseType.Ok, userData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case DELETE_USER: {
                        User user = gson.fromJson(requestMessage, User.class);
                        UsersEntity respUser = UsersService.findUserLog(user.getLogin());
                        UsersService.deleteUser(respUser);
                        UsersHasRoleEntity entity = new UsersHasRoleEntity(respUser.getIdUser());
                        RoleHasUsersService.deleteRole(entity);
                        UserData userData = ServerMethods.findAllUsers();
                        response = new Response<>(ResponseType.Ok, userData);
                        outputStream.writeObject(new Gson().toJson(response));
                        break;
                    }
                    case VIEW_MASTERS: {

                        MasterData masterData = ServerMethods.findAllMasters();
                        response = new Response<>(ResponseType.Ok, masterData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case ADD_MASTER: {
                        ServiceData serviceData = ServerMethods.findListServices();
                        response = new Response<>(ResponseType.Ok, serviceData);
                        outputStream.writeObject(new Gson().toJson(response));
                        break;
                    }
                    case CREATE_MASTER:{
                        BeautyMastersEntity mastersEntity = new BeautyMastersEntity();
                        Master master = gson.fromJson(requestMessage, Master.class);
                        if (BeautyMastersService.isMaster(master.getMasterName(), master.getMasterSurname()) != null){
                            System.out.println(BeautyMastersService.isMaster(master.getMasterName(), master.getMasterSurname()).toString());
                            String answer = "Такой мастер уже существует";
                            response = new Response<>(ResponseType.ERROR, answer);
                            outputStream.writeObject(new Gson().toJson(response));
                        }
                        else{
                            mastersEntity.setMasterName( master.getMasterName());
                            mastersEntity.setMasterSurname(master.getMasterSurname());
                            mastersEntity.setActivity(master.getActivity());
                            mastersEntity.setWorkExperience(master.getWorkExperience());
                            BeautyMastersService.saveMaster(mastersEntity);
                            MasterData masterData = ServerMethods.findAllMasters();
                            response = new Response<>(ResponseType.Ok, masterData);
                            outputStream.writeObject(new Gson().toJson(response));
                        }
                        break;
                    }
                    case UPDATE_MASTER: {
                       MasterData masterD = gson.fromJson(requestMessage, MasterData.class);
                       Master master = masterD.getData().get(0);
                       Master updateMaster = masterD.getData().get(1);
                       BeautyMastersEntity respMaser = BeautyMastersService.isMaster(master.getMasterName(), master.getMasterSurname());

                       respMaser.setMasterName(updateMaster.getMasterName());
                       respMaser.setWorkExperience(updateMaster.getWorkExperience());
                       respMaser.setMasterSurname(updateMaster.getMasterSurname());
                       respMaser.setActivity(updateMaster.getActivity());

                       BeautyMastersService.updateMaster(respMaser);

                       MasterData masterData = ServerMethods.findAllMasters();
                        response = new Response<>(ResponseType.Ok, masterData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case DELETE_MASTER: {
                        Master master = gson.fromJson(requestMessage, Master.class);
                        BeautyMastersEntity respMaster = BeautyMastersService.isMaster(master.getMasterName(), master.getMasterSurname());
                        System.out.println(respMaster.toString());
                        BeautyMastersService.deleteMaster(respMaster);

                        MasterData masterData = ServerMethods.findAllMasters();
                        response = new Response<>(ResponseType.Ok, masterData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case VIEW_SERVICES: {
                        ServiceData serviceData = ServerMethods.findAllService();
                        response = new Response<>(ResponseType.Ok, serviceData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
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

