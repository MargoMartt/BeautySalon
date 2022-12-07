package Utility;

import Entities.*;
import Enums.Roles;
import Models.*;
import Models.Record;
import Services.*;

import java.util.ArrayList;
import java.util.Date;

public class ServerMethods {
    public static UserData findAllUsers() {
        UserData userData = new UserData();
        for (UsersEntity us : UsersService.findAllUsers()) {
            User user = new User();
            user.setUserId(us.getIdUser());
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

    public static BonusEntity findBonusUser(int id) {
        BonusEntity bonus = new BonusEntity();
        for (BonusEntity bonuses : BonusService.findAllBonus()) {
            if (bonuses.getIdUser() == id)
                return bonuses;
        }
        return bonus;
    }

    public static RecordEntity findRecordUser(int id) {
        RecordEntity record = new RecordEntity();
        for (RecordEntity records : RecordService.findAllRecords()) {
            if (records.getIdUser() == id)
                return records;
        }
        return record;
    }

    public static void deleteByIDMaster(int id) {
        for (ServiceEntity services : ServiceService.findAllServices()) {
            if (id == services.getMasterId()) {
                for (RecordEntity record : RecordService.findAllRecords()) {
                    if (services.getserviceId() == record.getserviceId()) {
                        RecordService.deleteRecord(record);
                    }
                    ServiceService.deleteService(services);
                }
            }
        }
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
            service.setMasterId(services.getMasterId());
            service.setServiceId(services.getserviceId());
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


    public static FinanceData findAllFinance() {
        FinanceData financeData = new FinanceData();
        for (UsersEntity users : UsersService.findAllUsers()) {
            Finance finance = new Finance();
            finance.setUserId(users.getIdUser());
            finance.setUserName(users.getUserName() + " " + users.getUserSurname());
            finance.setBalance(users.getBalance());
            for (BonusEntity bonus : BonusService.findAllBonus()) {
                if (bonus.getIdUser() == users.getIdUser()) {
                    finance.setBonusId(bonus.getBonusId());
                    finance.setDiscount(bonus.getDiscount());
                    finance.setCertificate(bonus.getCertificate());
                }
            }
            financeData.setData(finance);
        }
        return financeData;
    }

    public static Salon SalonData() {
        Salon salon = new Salon();
        int clientCount = 0;
        int certificate = 0;
        int discount = 0;
        for (int i = 0; i < RoleHasUsersService.findAllRoles().size(); i++) {
            if (RoleHasUsersService.findAllRoles().get(i).getRoleIdRole() == 1)
                clientCount++;
        }
        salon.setClientCount(clientCount);
        salon.setMasterCount(BeautyMastersService.findAllMasters().size());
        salon.setServiceCount(ServiceService.findAllServices().size());
        salon.setRecordCount(RecordService.findAllRecords().size());

        for (BonusEntity b : BonusService.findAllBonus()) {
            if (b.getCertificate() != 0)
                certificate++;
            else if (b.getDiscount() != 0)
                discount++;
        }
        salon.setBonusCount(BonusService.findAllBonus().size());
        salon.setCertificate(certificate);
        salon.setDiscount(discount);
        return salon;
    }

    public static RecordData RecordInfo() {
        RecordData recordData = new RecordData();
        for (RecordEntity recordEntity : RecordService.findAllRecords()) {
            Record record = new Record();
            record.setRecordId(recordEntity.getRecordId());
            record.setDate(String.valueOf(recordEntity.getDate()));
            record.setTime(recordEntity.getTime());
            record.setClientId(recordEntity.getIdUser());
            record.setServiceId(recordEntity.getserviceId());

            UsersEntity user = UsersService.findUser(record.getClientId());
            record.setClient(user.getUserName() + " " + user.getUserSurname());
            record.setClientId(user.getIdUser());
            record.setBalance(user.getBalance());

            for (BonusEntity bonus : BonusService.findAllBonus()
            ) {
                if (bonus.getIdUser() == record.getClientId()) {
                    record.setDiscount(bonus.getDiscount());
                    break;
                } else record.setDiscount(0);
            }

            ServiceEntity service = ServiceService.findServiceId(record.getServiceId());
            record.setService(service.getServiceName());
            record.setCost(service.getServicePrice());
            record.setMasterId(service.getMasterId());

            BeautyMastersEntity master = BeautyMastersService.findMaster(record.getMasterId());
            record.setMaster(master.getMasterName() + " " + master.getMasterSurname());

            record.setFinalCost(record.getCost() - record.getCost() * record.getDiscount() / 100);
            recordData.setData(record);
        }
        return recordData;
    }

    public static Boolean findRecord(int id, String date, String time) {
        ServiceEntity serviceEntity = ServiceService.findServiceId(id);
        for (RecordEntity records : RecordService.findAllRecords()) {
            if (records.getserviceId() == id && records.getDate().toString().equals(date) && records.getTime().equals(time)) {
                return true;
            }

            for (ServiceEntity service : ServiceService.findAllServices()) {
                if (service.getMasterId() == serviceEntity.getMasterId() && records.getDate().toString().equals(date) && records.getTime().equals(time)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ProfitabilityData ProfitabilityData() {
        ProfitabilityData profitabilityData = new ProfitabilityData();
        double sum = 0;
        for (ServiceEntity services : ServiceService.findAllServices()) {
            int count = 0;
            double finalCost = 0;

            Profitability profitability = new Profitability();
            profitability.setServiceId(services.getserviceId());
            profitability.setPrice(services.getServicePrice());
            profitability.setService(services.getServiceName());
            for (RecordEntity records : RecordService.findAllRecords()) {
                if (records.getserviceId() == profitability.getServiceId()) {
                    count++;
                    finalCost += records.getTotalCost();
                }
            }
            profitability.setCount(count);
            profitability.setCost(profitability.getPrice() * count);
            profitability.setFinalCost(finalCost);
            profitabilityData.setData(profitability);
            sum += profitability.getFinalCost();
        }
        profitabilityData.setSum(sum);

        return profitabilityData;
    }
}