package com.mycompany.capp.test;

import com.mycompany.capp.config.SpringRootConfig;
import com.mycompany.capp.dao.UserDAO;
import com.mycompany.capp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOUpdate {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u= new User();
        u.setUserId(1);
        u.setName("Janak Dangi");
        u.setPhone("9810063862");
        u.setEmail("janak123@gmail.com");
        u.setAddress("Tulsipur,Dang");
        u.setRole(1);
        u.setLoginStatus(1);
        userDAO.update(u);
        System.out.println("Data updated");
    }
    
}
