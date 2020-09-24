package com.mycompany.capp.service;

import com.mycompany.capp.dao.BaseDAO;
import com.mycompany.capp.dao.UserDAO;
import com.mycompany.capp.domain.User;
import com.mycompany.capp.exception.UserBLockedException;
import com.mycompany.capp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseDAO implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User u) {
        userDAO.save(u);
    }

    @Override
    public User login(String loginName, String password) throws UserBLockedException {
        String sql = "SELECT userId, name, phone, email,address, role, loginStatus, loginName"
                + " FROM user WHERE loginName=:ln AND password=:pw";
        Map m = new HashMap();
        m.put("ln", loginName);
        m.put("pw", password);
        try {
            User u = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            if (u.getLoginStatus().equals(UserService.LOGIN_STATUS_BLOCKED)) {
                throw new UserBLockedException("Your account has been blocked, contact to admin.");
            } else {
                return u;
            }
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<User> getUserList() {
        return userDAO.findByProperty("role", UserService.ROLE_USER);
    }

    @Override
    public void changeLoginStatus(Integer userId, Integer loginStatus) {
    String sql="UPDATE user SET loginStatus=:lst WHERE userId=:uid";
    Map m=new HashMap();
    m.put("uid", userId);
    m.put("lst", loginStatus);
    getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public boolean isUsernameExist(String username) {
    String sql="SELECT count(loginName) FROM user WHERE loginName=?";
Integer count=getJdbcTemplate().queryForObject(sql, new String[]{username}, Integer.class);
if(count.equals(1)){
    return true;
}else{
    return false;
}
    }

}
