package Services;

import DAO.RoleDAO;
import Entities.RoleEntity;

import java.util.ArrayList;

public class RoleService {
    private static RoleDAO roleDAO = new RoleDAO();

    public RoleService() {
    }

    public static RoleEntity findRole(int id) {
        return roleDAO.findById(id);
    }

    public static void saveRole(RoleEntity role) {
        roleDAO.save(role);
    }

    public static void deleteRole(RoleEntity role) {
        roleDAO.delete(role);
    }

    public static void updateRole(RoleEntity role) {
        roleDAO.update(role);
    }

    public static ArrayList<RoleEntity> findAllRoles() {
        return roleDAO.findAll();
    }

}
