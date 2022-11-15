package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RecordDAO {
    public RecordEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(RecordEntity.class, id);
    }

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
        ArrayList<RecordEntity> accountingEntities = new ArrayList<RecordEntity>();
        return (ArrayList<RecordEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from RecordEntity ").list();

    }

}
