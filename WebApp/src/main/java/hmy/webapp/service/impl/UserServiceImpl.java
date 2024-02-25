package hmy.webapp.service.impl;

import hmy.webapp.dao.UserDao;
import hmy.webapp.dto.UserDTO;
import hmy.webapp.entity.User;
import hmy.webapp.exception.BaseException;
import hmy.webapp.service.IUserService;
import hmy.webapp.utils.Response;
import hmy.webapp.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Response login(UserDTO userDTO) {
        try {
            User user = userDao.selectUserByUsernameAndPassword(
                    userDTO.getUsername(), EncryptUtil.encrypt(userDTO.getPassword(), EncryptUtil.SHA_256));
            if (user != null) {
                return new Response(true, userDTO);
            }
            return new Response(false, "Login failed due to incorrect username or password");
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
            //return ResponseEntity.badRequest().body(new Response(false, "Login failed"));
        }
    }

    @Override
    public User checkExist(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 60, rollbackFor = Exception.class)
    public Response register(UserDTO userDTO) {
        //check if the user already exists
        if (checkExist(userDTO.getUsername()) != null){
            throw new BaseException("User already exists");
            //return ResponseEntity.ok(new Response(false, "User already exists"));
        }
        try {
            // Encapsulate the user information then register
            User user = new User(userDTO.getUsername(), EncryptUtil.encrypt(userDTO.getPassword(), EncryptUtil.SHA_256));
            boolean isSuccess = userDao.insertUser(user);
            return new Response(isSuccess);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
            //return ResponseEntity.badRequest().body(new Response(false, "Register failed"));
        }
    }

    @Override
    public boolean updatePassword(User user) {
        try {
            return userDao.updateUser(user);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            return userDao.deleteUserById(id);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }
}
