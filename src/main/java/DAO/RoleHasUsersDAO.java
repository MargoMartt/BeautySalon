package DAO;

import Entities.BeautyMastersEntity;
import Entities.RecordEntity;
import Entities.RoleEntity;
import Entities.RoleHasUsersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RoleHasUsersDAO {
    public RoleHasUsersEntity findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(RoleHasUsersEntity.class, id);
    }

    public void save(RoleHasUsersEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
//        session.save(role);
        session.save(new RoleHasUsersEntity(1, 12));
        transaction.commit();
        session.close();
    }

    public void update(RoleHasUsersEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(role);
        transaction.commit();
        session.close();
    }

    public void delete(RoleHasUsersEntity role) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }

    public ArrayList<RoleHasUsersEntity> findAll() {
        ArrayList<RoleHasUsersEntity> accountingEntities = new ArrayList<RoleHasUsersEntity>();
        return (ArrayList<RoleHasUsersEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("from RoleHasUsersEntity ").list();

    }

}
