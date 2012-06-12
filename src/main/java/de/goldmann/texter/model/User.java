package de.goldmann.texter.model;

import javax.persistence.Entity;

/**
 * Model for User.
 * 
 * @author goldmannm
 * 
 */
@Entity
public class User extends AbstractEntitiy {

	private static final long serialVersionUID = 3500054331508398861L;

	private String userName, password;

	/**
	 * @return username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            - userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            - password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
