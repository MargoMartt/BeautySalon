package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Entities.RoleEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RoleDAO {
    public RoleEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(RoleEntity.class, id);
    }

    public void save(RoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
        transaction.commit();
        session.close();
    }

    public void update(RoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(role);
        transaction.commit();
        session.close();
    }

    public void delete(RoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }

    public ArrayList<RoleEntity> findAll() {
        ArrayList<RoleEntity> accountingEntities = new ArrayList<RoleEntity>();
        return (ArrayList<RoleEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from RoleEntity ").list();

    }

}
