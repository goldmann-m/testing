package de.goldmann.texter.services.impl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import de.goldmann.texter.dao.UserDao;
import de.goldmann.texter.exception.IncorrectPasswordException;
import de.goldmann.texter.model.User;
import de.goldmann.texter.services.UserService;

/**
 * Implementation of {@link UserService}
 * 
 * @author goldmannm
 * 
 */
@RequestScoped
public class UserServiceImpl implements Serializable, UserService {

	private static final long serialVersionUID = 540434479928296322L;

	@Inject
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.services.UserService#login(java.lang.String, java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.services.UserService#registerUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void registerUser(String userName, String password, String email) {

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * @see de.goldmann.texter.services.UserService#editUser(de.goldmann.texter.model.User)
	 */
	public void editUser(User user) {
		userDao.save(user);
	}

}
