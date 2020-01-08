package net.etfbl.beans;

import java.io.PrintWriter;
import java.io.Serializable;

import net.etfbl.dao.UserDAO;
import net.etfbl.dto.User;
 
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	
	private boolean isLoggedIn = false;

	public boolean login(String username, String password) {
	/*	if ((user = UserDAO.selectByUsernameAndActive(username, 1)) != null) {
			isLoggedIn = true;
			return true;
		}*/
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

	public Boolean areUsernameAndMailAllowed(String username, String mail, PrintWriter pw) {
		Boolean areAllowed = true;
		if(!UserDAO.isUsernameAllowed(username)) {
			areAllowed = false;
			pw.println("USERNAME_ERROR");
			pw.close();
		}
		if(!UserDAO.isMailAllowed(mail)) {
			areAllowed = false;
			pw.println(" # * Mail je zauzet");
			pw.close();
		}
		return areAllowed;
	}
	
	public boolean add(User user) {
		this.user = user;
		return UserDAO.insert(user);
	}

}
