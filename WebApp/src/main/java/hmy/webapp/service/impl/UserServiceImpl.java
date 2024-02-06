package hmy.webapp.service.impl;

import hmy.webapp.dao.UserDao;
import hmy.webapp.dto.UserDTO;
import hmy.webapp.entity.User;
import hmy.webapp.exception.BaseException;
import hmy.webapp.service.IUserService;
import hmy.webapp.utils.Response;
import hmy.webapp.utils.SHA256Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Response login(UserDTO userDTO) {
        try {
            User user = userDao.selectUserByUsernameAndPassword(userDTO.getUsername(), SHA256Encrypt.encrypt(userDTO.getPassword()));
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
    public Response register(UserDTO userDTO) {
//        //check if the user already exists
//        if (userService.checkExist(userDTO.getUsername()) != null){
//            //throw new BaseException("User already exists");
//            return ResponseEntity.ok(new Response(false, "User already exists"));
//        }
        try {
            // Encapsulate the user information then register
            User user = new User(userDTO.getUsername(), SHA256Encrypt.encrypt(userDTO.getPassword()));
            boolean isSuccess = userDao.insertUser(user);
            return new Response(isSuccess);
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
            //return ResponseEntity.badRequest().body(new Response(false, "Register failed"));
        }
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
