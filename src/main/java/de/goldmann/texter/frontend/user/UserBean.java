package de.goldmann.texter.frontend.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

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
	private boolean loggedIn = false;
	private User user;

	@Inject
	private UserService userService;

	@PostConstruct
	public void test() {
		User user = new User();
		user.setUserName("peter");
		user.setPassword("pan");
		userService.saveUser(user);
	}

	/**
	 * login the user
	 * 
	 * throws facesmessage if password/username is incorrect.
	 */
	public void login() {

		User user;

		try {
			user = userService.findUserByUsername(userName);
		} catch (NoResultException e) {
			password = "";
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Benutzername/Passwort Kombination ungültig!",
							"Benutzername/Passwort Kombination ungültig!"));
			return;
		}

		if (!user.getPassword().equals(this.password)) {
			password = "";
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Benutzername/Passwort Kombination ungültig!",
							"Benutzername/Passwort Kombination ungültig!"));
			return;
		}

		this.user = user;

		this.loggedIn = true;

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
