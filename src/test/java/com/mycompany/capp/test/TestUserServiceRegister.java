package com.mycompany.capp.test;

import com.mycompany.capp.config.SpringRootConfig;
import com.mycompany.capp.domain.User;
import com.mycompany.capp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserServiceRegister {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserService userService = ctx.getBean(UserService.class);
        User u = new User();
        u.setName("Bhupendra");
        u.setPhone("9810063862");
        u.setEmail("bhupendra@gmail.com");
        u.setAddress("Babai");
        u.setLoginName("bhupendra");
        u.setPassword("bhupendra123");
        u.setRole(UserService.ROLE_ADMIN);
        u.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
        userService.register(u);
        System.out.println("User Registered");
    }

}
