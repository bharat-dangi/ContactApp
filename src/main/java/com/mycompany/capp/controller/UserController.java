package com.mycompany.capp.controller;

import com.mycompany.capp.command.LoginCommand;
import com.mycompany.capp.command.UserCommand;
import com.mycompany.capp.domain.User;
import com.mycompany.capp.exception.UserBLockedException;
import com.mycompany.capp.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.parser.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model m) {
        m.addAttribute("command", new LoginCommand());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m, HttpSession session) {
        try {
            User loggedInUser = userService.login(cmd.getLoginName(), cmd.getPassword());
            if (loggedInUser == null) {
                m.addAttribute("err", "Login Failed! Enter valid detail");
                return "index";
            } else {
                if (loggedInUser.getRole().equals(UserService.ROLE_ADMIN)) {
                    addUserInSession(loggedInUser, session);
                    return "redirect:/admin/dashboard";
                } else if (loggedInUser.getRole().equals(UserService.ROLE_USER)) {
                    addUserInSession(loggedInUser, session);
                    return "redirect:/user/dashboard";
                } else {
                    m.addAttribute("err", "Invalid User Role");
                    return "index";
                }
            }
        } catch (UserBLockedException ex) {
            m.addAttribute("err", ex.getMessage());
            return "index";
        }

    }

    @RequestMapping(value = "/user/dashboard")
    public String userDashboard() {
        return "dashboard_user";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index?act=lo";
    }

    @RequestMapping(value = "/admin/dashboard")
    public String adminDashboard() {
        return "dashboard_admin";
    }

    @RequestMapping(value = "/admin/users")
    public String getUsersList(Model m) {
        m.addAttribute("userList", userService.getUserList());
        return "users";
    }

    @RequestMapping(value = "/reg_form")
    public String registrationForm(Model m) {
        UserCommand cmd = new UserCommand();
        m.addAttribute("command", cmd);
        return "reg_form";
    }

    @RequestMapping(value = "/register")
    public String registerUser(@ModelAttribute("command") UserCommand cmd, Model m) {
        try {
            User user = cmd.getUser();
            user.setRole(UserService.ROLE_USER);
            user.setLoginStatus(UserService.LOGIN_STATUS_ACTIVE);
            userService.register(user);
            m.addAttribute("command", cmd);
            return "redirect:/index?act=reg";
        } catch (DuplicateKeyException e) {
            m.addAttribute("err", "Username is already taken!");
            return "reg_form";
        }
    }

    private void addUserInSession(User u, HttpSession session) {
        session.setAttribute("user", u);
        session.setAttribute("userId", u.getUserId());
        session.setAttribute("role", u.getRole());
    }

    @RequestMapping(value = "/admin/change_status")
    @ResponseBody
    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus) {
        try {
            userService.changeLoginStatus(userId, loginStatus);
            return "SUCCESS: Status Changed";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: Unable to change Status";
        }
    }

    @RequestMapping(value = "/check_avail")
    @ResponseBody
    public String checkAvailability(@RequestParam String username) {
     if(userService.isUsernameExist(username)){
         return "This username is already taken.Please choose another username";
     }else{
         return "This username is available";
     }
    }

}
