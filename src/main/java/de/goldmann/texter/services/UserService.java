package de.goldmann.texter.services;

import de.goldmann.texter.exception.IncorrectPasswordException;
import de.goldmann.texter.model.User;

/**
 * Service for user handling.
 * 
 * @author goldmannm
 * 
 */
public interface UserService {

	/**
	 * login the user.
	 * 
	 * @param userName
	 * @param password
	 * @return true if userName and password is correct
	 * @throws IncorrectPasswordException
	 */
	public User login(String userName, String password)
			throws IncorrectPasswordException;
	
	/**
	 * save the user.
	 * 
	 * @param userName
	 * @param password
	 * @param email
	 */
	public void registerUser(String userName, String password, String email);
	
	/**
	 * edit the user data.
	 * 
	 * @param user
	 */
	public void editUser(User user);
}
