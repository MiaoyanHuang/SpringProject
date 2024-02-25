package hmy.webapp.controller;

import hmy.webapp.dto.UserDTO;
import hmy.webapp.service.impl.UserServiceImpl;
import hmy.webapp.utils.Response;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Response> login(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {
        Response response = userService.login(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //http://localhost:6002/users/register?username=testUser&password=testPassword
    @PostMapping("/register/")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userDTO) {
        Response response = userService.register(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method is used to test the auto wiring of UserController.
     * 当这个类被实例化的时候，这个方法会被自动执行 执行顺序高于@PostConstruct
     * @param port port
     */
    @Autowired
    public void autoWiredTest(@Value(value = "${server.port}") String port) {
        System.out.println("UserController has been auto wired. Port: " + port);
    }

    /**
     * This method is used to test the construction of UserController.
     * 这个方法会在构造函数执行后自动执行 执行顺序低于@Autowired
     */
    @PostConstruct
    public void afterConstructTest() {
        System.out.println("UserController has been constructed.");
    }

    /**
     * This method is used to test the destruction of UserController.
     * 当这个类被销毁的时候，这个方法会被自动执行
     */
    @PreDestroy
    public void destroy() {
        System.out.println("UserController has been destroyed.");
    }

}
