package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Entities.ServiceEntity;
import Entities.UsersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ServiceDAO {
    public ServiceEntity findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ServiceEntity service = session.get(ServiceEntity.class, id);
        session.close();
        return service;
    }

    public void save(ServiceEntity service) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(service);
        transaction.commit();
        session.close();
    }

    public void update(ServiceEntity service) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(service);
        transaction.commit();
        session.close();
    }

    public void delete(ServiceEntity service) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(service);
        transaction.commit();
        session.close();
    }

    public ArrayList<ServiceEntity> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ArrayList<ServiceEntity> users = (ArrayList<ServiceEntity>) session.createQuery("from ServiceEntity ").list();
        session.close();
        return users;
    }

}
