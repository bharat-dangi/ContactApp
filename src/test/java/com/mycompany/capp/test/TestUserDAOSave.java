package com.mycompany.capp.test;

import com.mycompany.capp.config.SpringRootConfig;
import com.mycompany.capp.dao.UserDAO;
import com.mycompany.capp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOSave {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u = new User();
        u.setName("Janak");
        u.setPhone("9866916743");
        u.setEmail("janak@gmail.com");
        u.setAddress("Tulsipur");
        u.setLoginName("janak");
        u.setPassword("janak123");
        u.setRole(1);
        u.setLoginStatus(1);
        userDAO.save(u);
        System.out.println("Data saved");
    }
    
}
