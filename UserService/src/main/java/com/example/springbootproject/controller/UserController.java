package com.example.springbootproject.controller;

import com.example.springbootproject.entity.User;
import com.example.springbootproject.service.impl.UserServiceImpl;
import com.example.springbootproject.utils.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //http://localhost:10012/users/login/test5/test6
    //gateway情况下：http://localhost:10011/users/login/?username=test3&password=test4&authorization=pass
    //@GetMapping("/login/{username}/{password}")
    @GetMapping("/login/")
    String login(@Param("username") String username, @Param("password") String password){
        User user = userService.login(username, password);
        if (user != null){
            System.out.println(user);
            return new R(true, user).toString();
        }
        return new R(false).toString();
    }

    //http://localhost:6002/users/register?username=testUser&password=testPassword
    @PostMapping("/register/")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            readOnly = false, timeout = 99999, rollbackFor = Exception.class)
    String register(@Param("username") String username, @Param("password")String password){
        User user = new User(username, password);
        boolean isRegistered = userService.register(user);
        System.out.println(isRegistered);
        return new R(isRegistered, user).toString();
    }
}
