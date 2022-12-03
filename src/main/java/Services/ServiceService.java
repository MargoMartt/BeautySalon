package Services;

import DAO.ServiceDAO;
import Entities.ServiceEntity;
import Entities.UsersEntity;

import java.util.ArrayList;

public class ServiceService {
    private static ServiceDAO serviceDAO = new ServiceDAO();

    public ServiceService() {
    }

    public static ServiceEntity findServiceId(int id) {
        return serviceDAO.findById(id);
    }

    public static void saveService(ServiceEntity service) {
        serviceDAO.save(service);
    }

    public static void deleteService(ServiceEntity service) {
        serviceDAO.delete(service);
    }

    public static void updateService(ServiceEntity service) {
        serviceDAO.update(service);
    }

    public static ArrayList<ServiceEntity> findAllServices() {
        return serviceDAO.findAll();
    }

}
