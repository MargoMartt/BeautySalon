package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Entities.ServiceEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ServiceDAO {
    public ServiceEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(ServiceEntity.class, id);
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
        ArrayList<ServiceEntity> accountingEntities = new ArrayList<ServiceEntity>();
        return (ArrayList<ServiceEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from ServiceEntity ").list();

    }

}
