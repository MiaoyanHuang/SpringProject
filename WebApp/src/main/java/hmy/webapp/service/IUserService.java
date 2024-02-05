package hmy.webapp.service;

import hmy.webapp.entity.User;

public interface IUserService {
    User login(String username, String password);
    User checkExist(String username);
    boolean register(User user);
    boolean updatePassword(User user);
    boolean deleteUser(int id);
}
