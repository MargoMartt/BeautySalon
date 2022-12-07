package Utility;

import Entities.*;
import Models.*;
import Models.Record;
import Services.*;
import TCP.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

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
                        System.out.println(masterData.getData().toString());
                        break;
                    }
                    case ADD_MASTER: {
                        ServiceData serviceData = ServerMethods.findListServices();
                        response = new Response<>(ResponseType.Ok, serviceData);
                        outputStream.writeObject(new Gson().toJson(response));
                        break;
                    }
                    case CREATE_MASTER: {
                        BeautyMastersEntity mastersEntity = new BeautyMastersEntity();
                        Master master = gson.fromJson(requestMessage, Master.class);
                        if (BeautyMastersService.findMaster(master.getMasterId()) != null) {
                            String answer = "Такой мастер уже существует";
                            response = new Response<>(ResponseType.ERROR, answer);
                            outputStream.writeObject(new Gson().toJson(response));
                        } else {
                            mastersEntity.setMasterName(master.getMasterName());
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
                        Master master = gson.fromJson(requestMessage, Master.class);
                        BeautyMastersEntity respMaser = BeautyMastersService.findMaster(master.getMasterId());

                        respMaser.setMasterName(master.getMasterName());
                        respMaser.setWorkExperience(master.getWorkExperience());
                        respMaser.setMasterSurname(master.getMasterSurname());
                        respMaser.setActivity(master.getActivity());

                        BeautyMastersService.updateMaster(respMaser);

                        MasterData masterData = ServerMethods.findAllMasters();
                        response = new Response<>(ResponseType.Ok, masterData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case DELETE_MASTER: {
                        Master master = gson.fromJson(requestMessage, Master.class);
                        BeautyMastersEntity respMaster = BeautyMastersService.findMaster(master.getMasterId());
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
                    case ADD_SERVICE: {
                        ServiceEntity serviceEntity = new ServiceEntity();
                        Service service = gson.fromJson(requestMessage, Service.class);
                        if (ServiceService.findServiceId(service.getMasterId()) != null) {
                            String answer = "Такой мастер уже существует";
                            response = new Response<>(ResponseType.ERROR, answer);
                            outputStream.writeObject(new Gson().toJson(response));
                        } else {
                            serviceEntity.setMasterId(service.getMasterId());
                            serviceEntity.setServiceName(service.getServiceName());
                            serviceEntity.setServicePrice(service.getServicePrice());
                            ServiceService.saveService(serviceEntity);

                            ServiceData serviceData = ServerMethods.findAllService();
                            response = new Response<>(ResponseType.Ok, serviceData);
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                        }
                        break;
                    }
                    case UPDATE_SERVICE: {
                        Service service = gson.fromJson(requestMessage, Service.class);
                        ServiceEntity respService = ServiceService.findServiceId(service.getServiceId());

                        respService.setServiceName(service.getServiceName());
                        respService.setServicePrice(service.getServicePrice());
                        respService.setMasterId(service.getMasterId());

                        ServiceService.updateService(respService);

                        ServiceData serviceData = ServerMethods.findAllService();
                        response = new Response<>(ResponseType.Ok, serviceData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case DELETE_SERVICE: {
                        Service service = gson.fromJson(requestMessage, Service.class);
                        ServiceEntity respService = ServiceService.findServiceId(service.getServiceId());
                        ServiceService.deleteService(respService);

                        ServiceData serviceData = ServerMethods.findAllService();
                        response = new Response<>(ResponseType.Ok, serviceData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case MASTER_LIST: {
                        MasterServiceData listData = ServerMethods.findListMasters();
                        response = new Response<>(ResponseType.Ok, listData);
                        outputStream.writeObject(new Gson().toJson(response));
                        break;
                    }
                    case FINANCE: {
                        FinanceData financeData = ServerMethods.findAllFinance();
                        response = new Response<>(ResponseType.Ok, financeData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case DELETE_FINANCE: {
                        Finance finance = gson.fromJson(requestMessage, Finance.class);
                        BonusEntity bonusEntity = BonusService.findBonusId(finance.getBonusId());
                        BonusService.deleteBonus(bonusEntity);

                        FinanceData financeData = ServerMethods.findAllFinance();
                        response = new Response<>(ResponseType.Ok, financeData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case UPDATE_FIANCE: {
                        Finance finance = gson.fromJson(requestMessage, Finance.class);
                        BonusEntity bonusEntity = new BonusEntity();
                        UsersEntity user = UsersService.findUser(finance.getUserId());

                        if (BonusService.findBonusId(finance.getBonusId()) != null) {

                            user.setBalance(finance.getBalance());
                            bonusEntity = BonusService.findBonusId(finance.getBonusId());
                            bonusEntity.setCertificate(finance.getCertificate());
                            bonusEntity.setDiscount(finance.getDiscount());

                            if (bonusEntity.getDiscount() != 0 || bonusEntity.getCertificate() != 0) {
                                BonusService.updateBonus(bonusEntity);
                            } else BonusService.deleteBonus(bonusEntity);
                            UsersService.updateUser(user);
                        } else {
                            user.setBalance(finance.getBalance());
                            bonusEntity.setCertificate(finance.getCertificate());
                            bonusEntity.setDiscount(finance.getDiscount());
                            bonusEntity.setUsersByIdUser(user);
                            if (bonusEntity.getDiscount() != 0 || bonusEntity.getCertificate() != 0) {
                                BonusService.saveBonus(bonusEntity);
                            }
                            UsersService.updateUser(user);
                        }

                        FinanceData financeData = ServerMethods.findAllFinance();
                        response = new Response<>(ResponseType.Ok, financeData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case SALON_DATA: {
                        Salon salon = ServerMethods.SalonData();
                        response = new Response<>(ResponseType.Ok, salon);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                    }

                    case RECORD: {
                        RecordData recordData = ServerMethods.RecordInfo();
                        response = new Response<>(ResponseType.Ok, recordData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                        break;
                    }
                    case ADD_RECORD: {
                        Record record = gson.fromJson(requestMessage, Record.class);
                        RecordEntity recordEntity = new RecordEntity();
                        ServiceEntity service = ServiceService.findServiceId(record.getServiceId());

                        Boolean isExist = ServerMethods.findRecord(record.getServiceId(), record.getDate(), record.getTime());

                        if (isExist) {
                            System.out.println("1");
                            response = new Response<>(ResponseType.ERROR, "Выбранное время занято, пожалуйста, выберите другое.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        UsersEntity user = UsersService.findUserLog(record.getLogin());
                        if (user == null) {
                            System.out.println("2");
                            response = new Response<>(ResponseType.ERROR, "Пользователь не найден, проверьте введенный логин.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        BonusEntity bonus = BonusService.findBonusUser(user.getIdUser());
                        if (bonus == null) break;
                        if (user.getBalance() == null) {
                            System.out.println("3");
                            response = new Response<>(ResponseType.ERROR, "Недостаточно денег на счете.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        if (user.getBalance() < (service.getServicePrice() - service.getServicePrice() * bonus.getDiscount() / 100)) {
                            response = new Response<>(ResponseType.ERROR, "Недостаточно денег на счете.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        recordEntity.setDate(Date.valueOf(record.getDate()));
                        recordEntity.setTime(record.getTime());
                        recordEntity.setUsersByIdUser(user);
                        recordEntity.setServiceByserviceId(ServiceService.findServiceId(record.getServiceId()));
                        recordEntity.setTotalCost(service.getServicePrice() - service.getServicePrice() * bonus.getDiscount() / 100);
                        recordEntity.setserviceId(record.getServiceId());
                        RecordService.saveRecord(recordEntity);

                        Double balance = user.getBalance() - recordEntity.getTotalCost();
                        user.setBalance(balance);
                        UsersService.updateUser(user);

                        RecordData recordData = ServerMethods.RecordInfo();
                        response = new Response<>(ResponseType.Ok, recordData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                    }
                    case UPDATE_RECORD: {
                        Record record = gson.fromJson(requestMessage, Record.class);
                        RecordEntity recordEntity = new RecordEntity();
                        ServiceEntity service = ServiceService.findServiceId(record.getServiceId());
                        BonusEntity bonus;

                        Boolean isExist = ServerMethods.findRecord(record.getServiceId(), record.getDate(), record.getTime());

                        if (isExist) {
                            response = new Response<>(ResponseType.ERROR, "Выбранное время занято, пожалуйста, выберите другое.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        UsersEntity user = UsersService.findUser(record.getUserId());
                        bonus = BonusService.findBonusUser(record.getUserId());
                        if (!service.getServiceName().equals(record.getService())) {
                            user.setBalance(user.getBalance() + service.getServicePrice());
                        } else {
                            user.setBalance(user.getBalance() + record.getCost());
                        }

                        if (bonus == null) break;
                        if (user.getBalance() == null) {
                            response = new Response<>(ResponseType.ERROR, "Недостаточно денег на счете.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        if (user.getBalance() < (service.getServicePrice() - service.getServicePrice() * bonus.getDiscount() / 100)) {
                            response = new Response<>(ResponseType.ERROR, "Недостаточно денег на счете.");
                            outputStream.writeObject(new Gson().toJson(response));
                            System.out.println(response.getResponseMessage());
                            break;
                        }
                        recordEntity.setDate(Date.valueOf(record.getDate()));
                        recordEntity.setTime(record.getTime());
                        recordEntity.setUsersByIdUser(user);
                        recordEntity.setServiceByserviceId(ServiceService.findServiceId(record.getServiceId()));
                        recordEntity.setTotalCost(service.getServicePrice() - service.getServicePrice() * bonus.getDiscount() / 100);
                        recordEntity.setserviceId(record.getServiceId());
                        RecordService.updateRecord(recordEntity);

                        Double balance = user.getBalance() - recordEntity.getTotalCost();
                        user.setBalance(balance);
                        UsersService.updateUser(user);

                        RecordData recordData = ServerMethods.RecordInfo();
                        response = new Response<>(ResponseType.Ok, recordData);
                        outputStream.writeObject(new Gson().toJson(response));
                        System.out.println(response.getResponseMessage());
                    }

                }
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            }
        } catch (ClassNotFoundException |
                 IOException ex) {
            System.out.println("Error connecting!");
            throw new RuntimeException(ex);
        }
    }
}

