package hmy.webapp.controller;

import hmy.webapp.entity.User;
import hmy.webapp.service.impl.UserServiceImpl;
import hmy.webapp.utils.Response;
import hmy.webapp.utils.SHA256Encrypt;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to handle requests related to users.
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
    @GetMapping("/login/")
    @Transactional(readOnly = true, timeout = 60, rollbackFor = Exception.class)
    public String login(@Param("username") String username, @Param("password") String password){
        User user = userService.login(username, SHA256Encrypt.encrypt(password));
        if (user != null){
            System.out.println(user);
            return new Response(true, user).toString();
        }
        return new Response(false, "Login failed due to incorrect username or password").toString();
    }

    //http://localhost:6002/users/register?username=testUser&password=testPassword
    @PostMapping("/register/")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 99999, rollbackFor = Exception.class)
    public String register(@Param("username") String username, @Param("password") String password){
        // check if the user already exists
        if (userService.checkExist(username) != null){
            //throw new BaseException("User already exists");
            return new Response(false, "User already exists").toString();
        }

        // Encapsulate the user information then register
        User user = new User(username, SHA256Encrypt.encrypt(password));
        boolean isRegistered = userService.register(user);

        // Return the result
        return new Response(isRegistered).toString();
    }
}
