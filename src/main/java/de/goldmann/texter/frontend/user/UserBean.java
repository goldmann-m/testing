package de.goldmann.texter.frontend.user;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import de.goldmann.texter.exception.IncorrectPasswordException;
import de.goldmann.texter.model.User;
import de.goldmann.texter.services.UserService;

/**
 * Bean for user handling.
 * 
 * @author goldmannm
 * 
 */
@SessionScoped
@Named
public class UserBean implements Serializable {

	private static final long serialVersionUID = 8012155032937956454L;

	private String userName;
	private String password;
	private String email;
	private boolean loggedIn = false;
	private User user;

	@Inject
	private UserService userService;

	/**
	 * login the user.
	 * 
	 * throws facesmessage if user doesn't exists or password is incorrect.
	 */
	public void login() {

		User user;

		try {
			user = userService.login(userName, password);
		} catch (IncorrectPasswordException | NoResultException e) {
			password = "";
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Username/Password incorrect.",
							"Username/Password incorrect."));
			return;
		}

		loggedIn = true;
		this.user = user;

	}
	
	/**
	 * save a new user
	 */
	public void saveUser(){
		userService.saveUser(userName, password, email);
	}

	/**
	 * @return username
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            - username to set
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

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            - email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return true if user is logged in
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @return logged in user
	 */
	public User getUser() {
		return user;
	}

}
