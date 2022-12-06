package Services;

import DAO.RecordDAO;
import Entities.RecordEntity;

import java.util.ArrayList;

public class RecordService {
    private static RecordDAO recordDAO = new RecordDAO();

    public RecordService() {
    }

    public static RecordEntity findRecord(int id) {
        return recordDAO.findById(id);
    }

    public static void saveRecord(RecordEntity record) {
        recordDAO.save(record);
    }

    public static void deleteRecord(RecordEntity record) {
        recordDAO.delete(record);
    }

    public static void updateRecord(RecordEntity record) {
        recordDAO.update(record);
    }

    public static ArrayList<RecordEntity> findAllRecords() {
        return recordDAO.findAll();
    }
}
