package com.example.springbootproject;

import com.example.springbootproject.dao.UserDao;
import com.example.springbootproject.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTestCase {

    @Autowired
    private UserDao userDao;

    @Test
    void selectUserById(){
        User user = userDao.selectUserById(1);
        System.out.println(user);
    }

    @Test
    void insertUser() {
        User user = new User("test1", "test2");
        boolean result = userDao.insertUser(user);
        System.out.println(result);
    }

    @Test
    void updateUserById(){
        User user = new User(1,"test3", "test4");
        user.setId(1);
        boolean result = userDao.updateUser(user);
        System.out.println(result);
    }

    @Test
    void deleteUserById(){
        boolean result = userDao.deleteUserById(2);
        System.out.println(result);
    }
}
