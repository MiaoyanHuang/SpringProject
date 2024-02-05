package hmy.webapp.service.impl;

import hmy.webapp.dao.UserDao;
import hmy.webapp.entity.User;
import hmy.webapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User checkExist(String username) {
        return userDao.selectUserByUsername(username);
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
