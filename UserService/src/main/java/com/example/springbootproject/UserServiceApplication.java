package com.example.springbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced //这个是用来做负载均衡的
    public RestTemplate getRestTemplate() {
        //RestTemplate是Spring提供的用于访问Rest服务的客户端，RestTemplate提供了多种便捷访问远程Http服务的方法
        return new RestTemplate();
    }

    /*
    假设这里有一个服务A，它需要调用另一个服务B，那么服务A就是服务B的客户端，服务B就是服务A的服务端。
    通过RestTemplate来实现服务A调用服务B的功能，这里的服务B可以是多个，RestTemplate会根据负载均衡策略来选择一个服务B来调用。
    UserService 服务层示例：
    ...

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(){
        String url = "http://userservice/user/1"; // 这里的userservice是服务B的服务名，user/1是服务B的接口地址 通过服务名来调用服务B
        User user = restTemplate.getForObject(url, User.class); //这里的url是服务B的地址，User.class是服务B返回的数据类型
        return userDao.queryUserById(user.getId());
    }

    ...
     */

}
