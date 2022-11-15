package Services;

import DAO.ServiceDAO;
import Entities.ServiceEntity;

import java.util.ArrayList;

public class ServiceService {
    private ServiceDAO serviceDAO = new ServiceDAO();

    public ServiceService() {
    }

    public ServiceEntity findService(int id) {
        return serviceDAO.findById(id);
    }

    public void saveService(ServiceEntity service) {
        serviceDAO.save(service);
    }

    public void deleteService(ServiceEntity service) {
        serviceDAO.delete(service);
    }

    public void updateService(ServiceEntity service) {
        serviceDAO.update(service);
    }

    public ArrayList<ServiceEntity> findAllServices() {
        return serviceDAO.findAll();
    }

}
