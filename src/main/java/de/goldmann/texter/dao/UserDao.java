package de.goldmann.texter.dao;

import de.goldmann.texter.common.exception.NoUserFoundException;
import de.goldmann.texter.model.User;

/**
 * Data access object for {@link User} operations
 * 
 * @author goldmannm
 *
 */
public interface UserDao extends GenericDao<User, Long> {
	
	/**
	 * find user by username
	 * 
	 * @param userName
	 * 
	 * @return {@link User}
	 */
	public User findUserByUsername(String userName) throws NoUserFoundException;
	
}
