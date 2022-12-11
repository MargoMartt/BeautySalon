package Entities;

import Services.UsersService;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


public class AppTest
        extends TestCase {
    @Test
    void testCreate() {
        try {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUserName("Мария");
            usersEntity.setUserSurname("Белоснежная");
            usersEntity.setLogin("mari");
            usersEntity.setPassword("123");
            usersEntity.setBalance(100.0);
            UsersService.saveUser(usersEntity);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to create new user");
        }
    }

    @Test
    void testDelete() {
        try {
            UsersService.deleteUser(UsersService.findUser(82));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to delete user");
        }
    }

    @Test
    void testRead() {
        try {
            UsersEntity user = UsersService.findUser(82);
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to read information from database.");
        }
    }


    @Test
    void testUpdate() {
        try {
            UsersEntity user = UsersService.findUser(82);
            user.setBalance(150.0);
            UsersService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to update database.");
        }
    }
}
