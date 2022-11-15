package Services;

import DAO.BeautyMastersDAO;
import Entities.BeautyMastersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BeautyMastersService {
    private BeautyMastersDAO mastersDAO = new BeautyMastersDAO();

    public BeautyMastersService() {
    }

    public BeautyMastersEntity findMaster(int id) {
        return mastersDAO.findById(id);
    }

    public void saveMaster(BeautyMastersEntity master) {
        mastersDAO.save(master);
    }

    public void deleteMaster(BeautyMastersEntity master) {
        mastersDAO.delete(master);
    }

    public void updateMaster(BeautyMastersEntity master) {
        mastersDAO.update(master);
    }

    public ArrayList<BeautyMastersEntity> findAllMasters() {
        return mastersDAO.findAll();
    }

}
