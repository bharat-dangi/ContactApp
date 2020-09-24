package com.mycompany.capp.test;

import com.mycompany.capp.config.SpringRootConfig;
import com.mycompany.capp.dao.UserDAO;
import com.mycompany.capp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAODelete {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        userDAO.delete(2);
        System.out.println("Data Delted");
    }
    
}
