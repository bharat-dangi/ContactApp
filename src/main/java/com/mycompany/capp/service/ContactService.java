
package com.mycompany.capp.service;

import com.mycompany.capp.domain.Contact;
import java.util.List;

public interface ContactService {
public void save(Contact c);
public void update(Contact c);
public void delete(Integer contactId);
public void delete(Integer[] contactIds);
public Contact findById(Integer contactId);
public List<Contact> findUserContact(Integer userId);
public List<Contact> findUserContact(Integer userId,String txt);
    
}
