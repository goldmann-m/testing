package de.goldmann.texter.frontend.user;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.goldmann.texter.common.exception.IncorrectPasswordException;
import de.goldmann.texter.common.exception.NoUserFoundException;
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
		} catch (IncorrectPasswordException | NoUserFoundException e) {
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
	 * logout the user
	 * 
	 * @return mapping to home
	 */
	public String logout() {
		user = null;
		loggedIn = false;
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

		return "pretty:home";
	}

	/**
	 * save a new user
	 */
	public void registerUser() {
		User user = createUserObject();
		userService.registerUser(user);
	}

	private User createUserObject() {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		return user;
	}

	/**
	 * edit the user
	 */
	public void editUser() {
		userService.editUser(user);
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
