package com.example.springbootproject.service;

import com.example.springbootproject.entity.User;

public interface IUserService {
    User login(String username, String password);
    boolean register(User user);
    boolean updatePassword(User user);
    boolean deleteUser(int id);
}
