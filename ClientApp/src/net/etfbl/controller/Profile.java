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

import org.json.JSONObject;

import net.etfbl.beans.UserBean;
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
					validateFields(jsonObject, response);
				}
		    }

			private void validateFields(JSONObject jsonObject, HttpServletResponse response) {
				String firstName = jsonObject.getString("firstName");
				String lastName = jsonObject.getString("lastName");
				String username = jsonObject.getString("username");
				String password = jsonObject.getString("password");
				String mail = jsonObject.getString("mail");
				String country = jsonObject.getString("country");
				String region = jsonObject.getString("region");
				String city = jsonObject.getString("city");

				UserBean userBean = new UserBean();
				String attribute;
				//updateProfila
				/*try {
					PrintWriter pw = new PrintWriter(response.getWriter());
					if (username != null) {
						if (userBean.areUsernameAndMailAllowed(username, mail, pw)) {
			 			User user = new User(firstName, lastName, username, password, mail);
							if (userBean.add(user)) {
								//dodati bean-ove
							}
							}
				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				
			}
}
