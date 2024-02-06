package hmy.webapp.service;

import hmy.webapp.dto.UserDTO;
import hmy.webapp.entity.User;
import hmy.webapp.utils.Response;

public interface IUserService {
    Response login(UserDTO userDTO);
    User checkExist(String username);
    Response register(UserDTO userDTO);
    boolean updatePassword(User user);
    boolean deleteUser(int id);
}
