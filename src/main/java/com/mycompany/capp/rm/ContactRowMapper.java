package com.mycompany.capp.rm;

import com.mycompany.capp.domain.Contact;
import com.mycompany.capp.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int i) throws SQLException {
        Contact c = new Contact();
        c.setContactId(rs.getInt("contactId"));
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));
        c.setPhone(rs.getString("phone"));
        c.setRemark(rs.getString("remark"));
        
        return c;

    }

}
