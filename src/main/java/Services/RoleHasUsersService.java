package Services;

import DAO.RoleDAO;
import DAO.RoleHasUsersDAO;
import Entities.RoleEntity;
import Entities.RoleHasUsersEntity;
import Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RoleHasUsersService {
    private static RoleHasUsersDAO roleDAO = new RoleHasUsersDAO();

    public RoleHasUsersService() {
    }

    public RoleHasUsersEntity findRole(int id) {
        return roleDAO.findById(id);
    }

    public static void saveRole(RoleHasUsersEntity role) {
        roleDAO.save(role);
    }

    public static void deleteRole(RoleHasUsersEntity role) {
        roleDAO.delete(role);
    }

    public static void updateRole(RoleHasUsersEntity role) {
        roleDAO.update(role);
    }

    public static ArrayList<RoleHasUsersEntity> findAllRoles() {
        return roleDAO.findAll();
    }

}
