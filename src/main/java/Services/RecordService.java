package Services;

import DAO.RecordDAO;
import Entities.RecordEntity;

import java.util.ArrayList;

public class RecordService {
    private RecordDAO recordDAO = new RecordDAO();

    public RecordService() {
    }

    public RecordEntity findRecord(int id) {
        return recordDAO.findById(id);
    }

    public void saveRecord(RecordEntity record) {
        recordDAO.save(record);
    }

    public void deleteRecord(RecordEntity record) {
        recordDAO.delete(record);
    }

    public void updateRecord(RecordEntity record) {
        recordDAO.update(record);
    }

    public ArrayList<RecordEntity> findAllRecords() {
        return recordDAO.findAll();
    }
}
