package Services;

import DAO.BeautyMastersDAO;
import Entities.BeautyMastersEntity;
import Entities.ServiceEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BeautyMastersService {
    private static BeautyMastersDAO mastersDAO = new BeautyMastersDAO();

    public BeautyMastersService() {
    }

    public static BeautyMastersEntity findMaster(int id) {
        return mastersDAO.findById(id);
    }

    public static void saveMaster(BeautyMastersEntity master) {
        mastersDAO.save(master);
    }

    public static void deleteMaster(BeautyMastersEntity master) {
        mastersDAO.delete(master);
    }

    public static void updateMaster(BeautyMastersEntity master) {
        mastersDAO.update(master);
    }

    public static ArrayList<BeautyMastersEntity> findAllMasters() {
        return mastersDAO.findAll();
    }

    public static BeautyMastersEntity isMaster(String name, String surname) {
        BeautyMastersEntity masters = new BeautyMastersEntity();
        for (int i = 0; i < findAllMasters().size(); i++) {
            if (findAllMasters().get(i).getMasterName().equals(name) && findAllMasters().get(i).getMasterSurname().equals(surname)) {
                masters = findAllMasters().get(i);
                break;
            }
        }
        if (masters.getMasterName() == null) return null;
        return masters;
    }
}
