package net.etfbl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import net.etfbl.beans.UserBean;
import net.etfbl.dao.UserDAO;
import net.etfbl.dto.User;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	 
			private static final long serialVersionUID = 1L;
		    
		    public Profile() {
		        super();
		    }

		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    	request.setCharacterEncoding("UTF-8");		
				String address = "/WEB-INF/pages/profile.jsp";
				String action = request.getParameter("action");
				if (("updateProfile").equals(action)) {
					address = "/WEB-INF/pages/profile.jsp";
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(address);
				dispatcher.forward(request, response);
			}
		    
		    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("UTF-8");
				String jsonText = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				JSONObject jsonObject = new JSONObject(jsonText);
				String action = jsonObject.getString("action");

				if ("updateProfile".equals(action)) {
					validateFields(jsonObject, response, request, 0); //prvi put se vrsi izmjena profila, odmah nakon registracije pa je active na 0 jer administrator jos nije odobrio profil
				}
		    }

			private void validateFields(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request, Integer active) {
				HttpSession session = request.getSession();
				String firstName = jsonObject.getString("firstName");
				String lastName = jsonObject.getString("lastName");
				String username = jsonObject.getString("username");
				String password = jsonObject.getString("password");
				String mail = jsonObject.getString("mail");
				byte[] photoData = null; //jsonObject.getString("photo").getBytes();
				Integer notificationOnMail = 0;
				Integer notificationInApp = 0;
				String country = null, region = null, city = null;
				
				if (!jsonObject.isNull("country")){
					 country = jsonObject.getString("country");
					}
				 
				if (!jsonObject.isNull("region")){
					region = jsonObject.getString("region");
					}
				
				if (!jsonObject.isNull("city")){
					 city = jsonObject.getString("city");
					}
				
				UserBean userBean = new UserBean();
				String attribute;
				User user = new User(firstName, lastName, username, password, mail, country, region, city, photoData, false, false);//notificationOnMail, notificationInApp);
				try {
					PrintWriter pw = new PrintWriter(response.getWriter());
					if (username != null) {
			 			User u = UserDAO.getUserByUsernameAndActive(username, active);
			 			user.setId(u.getId());
			 			user.setNumberOfLogging(u.getNumberOfLogging());
			 			UserDAO.update(user);
			 	    	session.invalidate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
}
