package com.ssbsoft.library.service.impl;

import com.ssbsoft.library.dao.UserDao;
import com.ssbsoft.library.model.User;
import com.ssbsoft.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User getName(String name) {
        return userDao.getName(name);
    }

    @Override
    public boolean name(User user) {
        return userDao.name(user);
    }

    @Override
    public boolean emailId(User user) {
        return userDao.emailId(user);
    }

    @Override
    public boolean contactNumberVerification(User user) {
        return userDao.contactNumberVerification(user);
    }

    @Override
    public boolean passwordVerify(User user){
        if(user.getPassword().equals(user.getConfirm_password())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> viewUser() {
        return userDao.viewUser();

    }

    @Override
    public void passwordReset(User user) {
        userDao.passwordReset(user);

    }

    @Override
    public User getContactNumber(String contact_number) {
        return userDao.getContactNumber(contact_number);
    }

    @Override
    public void disableAccount(User user) {
        userDao.createUser(user);

    }

    @Override
    public User getUserId(int id) {
        return userDao.getUserId(id);
    }

    @Override
    public User getUserEmail(String email_id) {
        return userDao.getUserEmail(email_id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User getUserByUsername(User user) {
        return userDao.getUserByUsername(user.getName());
    }
}
