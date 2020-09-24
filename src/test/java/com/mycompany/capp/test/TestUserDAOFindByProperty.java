package com.mycompany.capp.test;

import com.mycompany.capp.config.SpringRootConfig;
import com.mycompany.capp.dao.UserDAO;
import com.mycompany.capp.domain.User;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOFindByProperty {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);

        List<User> users = userDAO.findByProperty("role", 1);
        for (User u : users) {
            System.out.println(u.getUserId() + " " + u.getName() + " " + u.getRole());
        }

    }

}
