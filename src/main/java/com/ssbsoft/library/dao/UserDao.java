package com.ssbsoft.library.dao;

import com.ssbsoft.library.model.User;

import java.util.List;


public interface UserDao {
	User createUser(User user);

	User userLogin(User user);

	List<User> viewUser();
	
	User getName(String name);

	boolean name(User user);

	boolean emailId(User user);

	boolean contactNumberVerification(User user);

	void passwordReset(User user);

	User getContactNumber(String contact_number);

	void disableAccount(User user);

	User getUserId(int id);
	
	User getUserEmail(String email_id);

	void updateUser(User user);

	User getUserByUsername(String name);
}