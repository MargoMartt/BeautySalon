package Services;

import DAO.UsersDAO;
import Entities.UsersEntity;

import java.util.ArrayList;

public class UsersService {
    private static UsersDAO usersDAO = new UsersDAO();

    public UsersService() {
    }

    public static UsersEntity findUser(int id) {
        return usersDAO.findById(id);
    }

    public static int saveUser(UsersEntity user) {
        return usersDAO.save(user);
    }

    public static void deleteUser(UsersEntity user) {
        usersDAO.delete(user);
    }

    public static void updateUser(UsersEntity user) {
        usersDAO.update(user);
    }

    public static ArrayList<UsersEntity> findAllUsers() {
        return usersDAO.findAll();
    }
}
