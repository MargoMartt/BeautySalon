package Services;

import DAO.RoleHasUsersDAO;
import Entities.RoleEntity;
import Entities.UsersEntity;
import Entities.UsersHasRoleEntity;
import org.hibernate.Session;

import java.util.ArrayList;

public class RoleHasUsersService {
    private static RoleHasUsersDAO roleDAO = new RoleHasUsersDAO();

    public RoleHasUsersService() {
    }

    public static void x(UsersHasRoleEntity role, Session session) {
        roleDAO.x(role, session);
    }

    public UsersHasRoleEntity findRole(int id) {
        return roleDAO.findById(id);
    }

    public static void saveRole(UsersHasRoleEntity role) {
        roleDAO.save(role);
    }

    public static void deleteRole(UsersHasRoleEntity role) {
        roleDAO.delete(role);
    }

    public static void updateRole(UsersHasRoleEntity role) {
        roleDAO.update(role);
    }

    public static ArrayList<UsersHasRoleEntity> findAllRoles() {
        return roleDAO.findAll();
    }

}
