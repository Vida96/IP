package net.etfbl.beans;

import java.io.Serializable;

import net.etfbl.dao.UserDAO;
import net.etfbl.dto.User;
 
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	
	private boolean isLoggedIn = false;

	public boolean login(String username, String password) {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		user = new User();
		isLoggedIn = false;
	}

	public User getUser() {
		return user;
	}

	public String areUsernameAndMailAllowed(String username, String mail) {
		String message = "";
		if(!UserDAO.isUsernameAllowed(username))
			message = "* Korisničko ime je zauzeto";
		if(!UserDAO.isMailAllowed(mail))
			message += " # * Mail je zauzet";
		else
			message += " # ";
		return message;
	}
	
	public boolean add(User user) {
		return UserDAO.insert(user);
	}

}
