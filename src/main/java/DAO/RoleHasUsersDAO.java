package DAO;

import Entities.UsersHasRoleEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RoleHasUsersDAO {
    public UsersHasRoleEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(UsersHasRoleEntity.class, id);
    }

    public void save(UsersHasRoleEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(role);
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
        ArrayList<UsersHasRoleEntity> rolesEntities = new ArrayList<UsersHasRoleEntity>();
        return (ArrayList<UsersHasRoleEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from UsersHasRoleEntity ").list();

    }

}
