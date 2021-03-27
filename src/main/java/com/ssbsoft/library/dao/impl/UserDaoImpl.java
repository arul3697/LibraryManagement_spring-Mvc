package com.ssbsoft.library.dao.impl;

import com.ssbsoft.library.dao.UserDao;
import com.ssbsoft.library.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssbsoft.library.util.HibernateUtil.createSession;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private CommonDaoImpl commonDao;
	// save users
	public User createUser(User user) {
		commonDao.saveOrUpdate(user);
		return user;
	}

	/**
	 * retrieve user
	 * 
	 * @return user
	 * @throws Exception
	 */
	public List<User> viewUser() {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("account_enabled", true));
		    List<User> user = criteria.list();
			return user;
	}

	/**
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public void disableAccount(User user) {
		commonDao.saveOrUpdate(user);
	}

	public User userLogin(User user) {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", user.getName()))
					.add(Restrictions.eq("account_enabled", true));
			user = (User) criteria.uniqueResult();
			return user;
	}
	
	public User getName(String name) {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", name));
			User user = (User) criteria.uniqueResult();
			return user;
	}

	public boolean name(User user) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", user.getName()));
		user = (User) criteria.uniqueResult();
		if (user != null) {
			return false;
		}
		return true;
	}

	public boolean contactNumberVerification(User user) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("contact_number", user.getContact_number()));
		user = (User) criteria.uniqueResult();
		if (user != null) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * checking for EmailId already exists or not
	 * </p>
	 */
	public boolean emailId(User user) {
		Session session = createSession();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email_id", user.getEmail_id()));
			user = (User) criteria.uniqueResult();
			if (user != null) {
				return false;
			}
			return true;
	}

	/**
	 * <p>
	 * Checks the mobile already exists or not
	 * </p>
	 */
	public User getContactNumber(String contact_number) {
		Session session = createSession();
		User user = new User();
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("contact_number",contact_number));
			user = (User) criteria.uniqueResult();
		return user;
	}

	public void passwordReset(User user) {
		Session session = createSession();
		commonDao.saveOrUpdate(user);
	}

	/**
	 * <p>
	 * Admin gets the User details by Id
	 * </p>
	 */
	public User getUserId(int id) {
		Session session = createSession();
		User user = new User();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("id", id));
			user = (User) criteria.uniqueResult();
		return user;
	}
	public User getUserEmail(String email_id) {
		Session session = createSession();
		User user = new User();
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email_id", email_id));
			user = (User) criteria.uniqueResult();
		return user;
	}

	/**
	 * <p>
	 * Admin updates the user
	 * </p>
	 */
	public void updateUser(User user) {
		Session session = createSession();
		commonDao.saveOrUpdate(user);
	}
	/**
	 * getting user details from user table by using username and password
	 *
	 * @param name
	 * @return user
	 */
	public User getUserByUsername(String name) {
		Session session = createSession();
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.eq("name", name))
		        .add(Restrictions.eq("account_enabled", true));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}
}
