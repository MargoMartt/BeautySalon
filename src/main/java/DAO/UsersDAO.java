package DAO;

import Entities.*;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public UsersEntity findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UsersEntity users = session.get(UsersEntity.class, id);
        session.close();
        return users;
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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ArrayList<UsersEntity> users = (ArrayList<UsersEntity>) session.createQuery("from UsersEntity ").list();
        session.close();
        return users;
    }

}
