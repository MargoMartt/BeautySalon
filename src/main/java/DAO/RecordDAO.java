package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RecordDAO {
    public RecordEntity findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        RecordEntity record = session.get(RecordEntity.class, id);
        session.close();
        return record;    }

    public void save(RecordEntity record) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(record);
        transaction.commit();
        session.close();
    }

    public void update(RecordEntity record) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(record);
        transaction.commit();
        session.close();
    }

    public void delete(RecordEntity record) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(record);
        transaction.commit();
        session.close();
    }

    public ArrayList<RecordEntity> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ArrayList<RecordEntity> record = (ArrayList<RecordEntity>) session.createQuery("from RecordEntity ").list();
        session.close();
        return record;
    }

}
