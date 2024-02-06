package hmy.webapp.controller;

import hmy.webapp.dto.UserDTO;
import hmy.webapp.service.impl.UserServiceImpl;
import hmy.webapp.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * This class is used to handle requests related to users.
 *
 * @author Huang Miaoyan
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //http://localhost:10012/users/login/test5/test6
    //gateway情况下：http://localhost:10011/users/login/?username=test3&password=test4&authorization=pass
    //@GetMapping("/login/{username}/{password}")
    @PostMapping("/login/")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 60, rollbackFor = Exception.class)
    public ResponseEntity<Response> login(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        Response response = userService.login(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //http://localhost:6002/users/register?username=testUser&password=testPassword
    @PostMapping("/register/")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 99999, rollbackFor = Exception.class)
    public ResponseEntity<Response> register(@RequestBody UserDTO userDTO) {
        Response response = userService.register(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
