package Utility;

import Entities.BeautyMastersEntity;
import Entities.BonusEntity;
import Entities.ServiceEntity;
import Entities.UsersEntity;
import Enums.Roles;
import Models.*;
import Services.*;

import java.util.ArrayList;

public class ServerMethods {
    public static UserData findAllUsers() {
        UserData userData = new UserData();
        for (UsersEntity us : UsersService.findAllUsers()) {
            User user = new User();
            user.setUserName(us.getUserName());
            user.setUserSurname(us.getUserSurname());
            user.setLogin(us.getLogin());
            user.setPassword(us.getPassword());
            for (int i = 0; i < RoleHasUsersService.findAllRoles().size(); i++) {
                if (RoleHasUsersService.findAllRoles().get(i).getUsersIdUser() == us.getIdUser()) {
                    user.setIdRole(RoleHasUsersService.findAllRoles().get(i).getRoleIdRole());
                    switch (user.getIdRole()) {
                        case 1: {
                            user.setRole(Roles.VISITOR.getValue());
                            break;
                        }
                        case 2: {
                            user.setRole(Roles.SALON_ADMIN.getValue());
                            break;
                        }
                        case 3: {
                            user.setRole(Roles.ADMIN.getValue());
                            break;
                        }

                    }
                    break;
                } else {
                    user.setIdRole(1);
                    user.setRole("-");
                }
            }
            userData.setData(user);
        }
        return userData;
    }

    public static MasterData findAllMasters() {
        MasterData masterData = new MasterData();
        for (BeautyMastersEntity masters : BeautyMastersService.findAllMasters()) {
            Master master = new Master();
            master.setMasterId(masters.getMasterId());
            master.setMasterName(masters.getMasterName());
            master.setMasterSurname(masters.getMasterSurname());
            master.setActivity(masters.getActivity());
            master.setWorkExperience(masters.getWorkExperience());
            for (int i = 0; i < ServiceService.findAllServices().size(); i++) {
                if (ServiceService.findAllServices().get(i).getMasterId() == masters.getMasterId()) {
                    master.addServices(ServiceService.findAllServices().get(i).getServiceName());
                }
            }
            masterData.setData(master);
        }
        return masterData;
    }

    public static ServiceData findListServices() {
        ServiceData serviceData = new ServiceData();
        for (ServiceEntity services : ServiceService.findAllServices()) {
            Service service = new Service();
            service.setServiceName(services.getServiceName());
            service.setServicePrice(services.getServicePrice());
            serviceData.setData(service);
        }
        return serviceData;
    }

    public static MasterServiceData findListMasters() {
        MasterServiceData listData = new MasterServiceData();
        for (BeautyMastersEntity masters : BeautyMastersService.findAllMasters()) {
            MasterService masterService = new MasterService();
            masterService.setId(masters.getMasterId());
            masterService.setMasterInfo(masters.getMasterSurname() + " " + masters.getMasterName());
            listData.setData(masterService);
        }
        return listData;
    }

    public static ServiceData findAllService() {
        ServiceData serviceData = new ServiceData();
        String master;
        for (ServiceEntity services : ServiceService.findAllServices()) {
            Service service = new Service();
            service.setServiceId(services.getserviceId());
            service.setServiceName(services.getServiceName());
            service.setServicePrice(services.getServicePrice());
            for (BeautyMastersEntity masters : BeautyMastersService.findAllMasters()) {
                if (services.getMasterId() == masters.getMasterId()) {
                    service.setMasterId(masters.getMasterId());
                    master = masters.getMasterName() + " " + masters.getMasterSurname();
                    service.setMaster(master);
                    break;
                } else {
                    master = " - ";
                    service.setMaster(master);
                }
            }
            serviceData.setData(service);
        }
        return serviceData;
    }


    public static FinanceData findAllFinance(){
        FinanceData financeData = new FinanceData();
        for (UsersEntity users : UsersService.findAllUsers()){
            Finance finance = new Finance();
            finance.setUserId(users.getIdUser());
            finance.setUserName(users.getUserName() + " " + users.getUserSurname());
            finance.setBalance(users.getBalance());
            for (BonusEntity bonus: BonusService.findAllBonus()) {
                if (bonus.getIdUser() == users.getIdUser()){
                    finance.setBonusId(bonus.getBonusId());
                    finance.setDiscount(bonus.getDiscount());
                    finance.setCertificate(bonus.getCertificate());
                }
            }
            financeData.setData(finance);
        }
        return financeData;
    }
}
