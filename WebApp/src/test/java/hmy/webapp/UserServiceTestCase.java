package hmy.webapp;

import hmy.webapp.entity.User;
import hmy.webapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestCase {
    @Autowired
    UserServiceImpl userService;

    @Test
    void login() {
        User user = userService.login("test3", "test4");
        if (user != null){
            System.out.println("Login successfully!");
        } else {
            System.out.println("Login failed!");
        }
    }

    @Test
    void register() {
        User user = new User("test5", "test6");
        boolean result = userService.register(user);
        if (result){
            System.out.println("Register successfully!");
        } else {
            System.out.println("Register failed!");
        }
    }

    @Test
    void updateUser(){
        User user = new User(3, "test3", "test4");
        boolean result = userService.updatePassword(user);
        if (result){
            System.out.println("Update successfully!");
        } else {
            System.out.println("Update failed!");
        }
    }

    @Test
    void deleteUser(){
        boolean result = userService.deleteUser(1);
        if (result){
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Delete failed!");
        }
    }


}
