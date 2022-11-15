package Services;

import DAO.RoleHasUsersDAO;
import Entities.UsersHasRoleEntity;

import java.util.ArrayList;

public class RoleHasUsersService {
    private static RoleHasUsersDAO roleDAO = new RoleHasUsersDAO();

    public RoleHasUsersService() {
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
