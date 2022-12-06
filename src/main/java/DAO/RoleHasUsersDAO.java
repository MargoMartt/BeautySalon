package DAO;

import Entities.ServiceEntity;
import Entities.UsersHasRoleEntity;
import Services.RoleHasUsersService;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RoleHasUsersDAO {
    public UsersHasRoleEntity findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        UsersHasRoleEntity roleEntity = session.get(UsersHasRoleEntity.class, id);
        session.close();
        return roleEntity;    }

    public void save(UsersHasRoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(role);
        transaction.commit();
        session.close();
    }

    public void update(UsersHasRoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(role);
        transaction.commit();
        session.close();
    }

    public void delete(UsersHasRoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }

    public ArrayList<UsersHasRoleEntity> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        ArrayList<UsersHasRoleEntity> users = (ArrayList<UsersHasRoleEntity>) session.createQuery("from UsersHasRoleEntity ").list();
        session.close();
        return users;
    }

}
