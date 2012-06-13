package de.goldmann.texter.services;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import de.goldmann.texter.dao.UserDao;
import de.goldmann.texter.exception.IncorrectPasswordException;
import de.goldmann.texter.model.User;

/**
 * Service for user handling.
 * 
 * @author goldmannm
 * 
 */
@RequestScoped
public class UserService implements Serializable {

	private static final long serialVersionUID = 540434479928296322L;

	@Inject
	private UserDao userDao;

	/**
	 * login the user.
	 * 
	 * @param userName
	 * @param password
	 * @return true if userName and password is correct
	 * @throws IncorrectPasswordException
	 */
	public User login(String userName, String password)
			throws IncorrectPasswordException {

		User user;
		try {
			user = userDao.findUserByUsername(userName);
		} catch (NoResultException e) {

			throw new NoResultException();
		}

		if (!user.getPassword().equals(password)) {

			throw new IncorrectPasswordException();
		}

		return user;
	}

	/**
	 * save the user.
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 */
	public void registerUser(String userName, String password, String email) {

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		userDao.save(user);
	}

	/**
	 * edit the user data.
	 * 
	 * @param user
	 */
	public void editUser(User user) {
		userDao.save(user);
	}

}
