package DAO;

import Entities.BeautyMastersEntity;
import Entities.BonusEntity;
import Entities.UsersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BonusDAO {
    public static BonusEntity findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        BonusEntity bonus = session.get(BonusEntity.class, id);
        session.close();
        return bonus;
    }

    public static void save(BonusEntity bonus) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bonus);
        transaction.commit();
        session.close();
    }

    public static void update(BonusEntity bonus) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(bonus);
        transaction.commit();
        session.close();
    }

    public static void delete(BonusEntity bonus) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(bonus);
        transaction.commit();
        session.close();
    }

    public static ArrayList<BonusEntity> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ArrayList<BonusEntity> bonus = (ArrayList<BonusEntity>) session.createQuery("from BonusEntity ").list();
        session.close();
        return bonus;
    }
}