package DAO;

import Entities.BeautyMastersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BeautyMastersDAO {
    public BeautyMastersEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(BeautyMastersEntity.class, id);
    }

    public void save(BeautyMastersEntity master) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(master);
        transaction.commit();
        session.close();
    }

    public void update(BeautyMastersEntity master) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(master);
        transaction.commit();
        session.close();
    }

    public void delete(BeautyMastersEntity master) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(master);
        transaction.commit();
        session.close();
    }

    public ArrayList<BeautyMastersEntity> findAll() {
        ArrayList<BeautyMastersEntity> accountingEntities = new ArrayList<BeautyMastersEntity>();
        return (ArrayList<BeautyMastersEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from BeautyMastersEntity ").list();

    }

}
