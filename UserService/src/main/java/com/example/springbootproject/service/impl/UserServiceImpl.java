package com.example.springbootproject.service.impl;

import com.example.springbootproject.dao.UserDao;
import com.example.springbootproject.entity.User;
import com.example.springbootproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.selectUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean register(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public boolean updatePassword(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUserById(id);
    }
}
