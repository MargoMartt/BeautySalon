package DAO;

import Entities.BeautyMastersEntity;
import Entities.UsersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class BeautyMastersDAO {
    public static BeautyMastersEntity findById(int id) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        BeautyMastersEntity masters = session.get(BeautyMastersEntity.class, id);
        session.close();
        return masters;
    }

    public static void save(BeautyMastersEntity master) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(master);
        transaction.commit();
        session.close();
    }

    public static void update(BeautyMastersEntity master) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(master);
        transaction.commit();
        session.close();
    }

    public static void delete(BeautyMastersEntity master) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(master);
        transaction.commit();
        session.close();
    }

    public static ArrayList<BeautyMastersEntity> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ArrayList<BeautyMastersEntity> masters = (ArrayList<BeautyMastersEntity>) session.createQuery("from BeautyMastersEntity ").list();
        session.close();
        return masters;
    }

}
