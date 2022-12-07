package Services;

import DAO.BonusDAO;
import Entities.BonusEntity;
import Entities.ServiceEntity;

import java.util.ArrayList;

public class BonusService {
    private static BonusDAO bonusDAO = new BonusDAO();

    public BonusService() {
    }

    public static BonusEntity findBonusId(int id) {
        return bonusDAO.findById(id);
    }

    public static void saveBonus(BonusEntity bonus) {
        bonusDAO.save(bonus);
    }

    public static void deleteBonus(BonusEntity bonus) {
        bonusDAO.delete(bonus);
    }

    public static void updateBonus(BonusEntity bonus) {
        bonusDAO.update(bonus);
    }

    public static ArrayList<BonusEntity> findAllBonus() {
        return bonusDAO.findAll();
    }

    public static BonusEntity findBonusUser(int userId) {
        BonusEntity bonusEntity = new BonusEntity();
        for (BonusEntity bonus : BonusService.findAllBonus()) {
            if (bonus.getIdUser() == userId) {
                bonusEntity = bonus;
                break;
            }
        }
        if (bonusEntity == null)
            return null;
        else return bonusEntity;
    }
}
