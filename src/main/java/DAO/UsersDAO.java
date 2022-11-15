package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Entities.ServiceEntity;
import Entities.UsersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UsersDAO {
    public UsersEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(UsersEntity.class, id);
    }

    public int save(UsersEntity users) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(users);
        transaction.commit();
        session.close();
        return id;
    }

    public void update(UsersEntity users) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(users);
        transaction.commit();
        session.close();
    }

    public void delete(UsersEntity users) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(users);
        transaction.commit();
        session.close();
    }

    public ArrayList<UsersEntity> findAll() {
        ArrayList<UsersEntity> usersEntities = new ArrayList<UsersEntity>();

        return (ArrayList<UsersEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from UsersEntity ").list();
    }

}
