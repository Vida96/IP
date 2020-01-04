package net.etfbl.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import net.etfbl.dto.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
 
	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
System.out.println("ACTION" + action);
		session.setAttribute("notificationUsername", "");
		session.setAttribute("notificationMail", "");

		if (action == null || action.equals("")) {
  			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("login")) {
			UserBean userBean = new UserBean();
			String username = request.getParameter("username");
			String password = request.getParameter("password");	
			System.out.println(username);
			System.out.println(password);
			if (userBean.login(username, password)) {
				session.setAttribute("userBean", userBean);
			//	MessageBean messageBean = new MessageBean();
		//		session.setAttribute("messageBean", messageBean);
				address = "/WEB-INF/pages/messages.jsp";
			} else {
				session.setAttribute("notification", "Pogresni parametri za pristup");
			}
		} else if (action.equals("registration")) {
			String jsonText = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		//	System.out.println(jsonText);
			JSONObject jsonObject = new JSONObject(jsonText);
			System.out.println("OBJECT" + jsonObject.getString("firstName"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String mail = request.getParameter("mail");

			UserBean userBean = new UserBean();
			String attribute;
			try {
				System.out.println("USERNAME" + username);

				if (username != null) {
					attribute =userBean.areUsernameAndMailAllowed(username, mail);
					if (attribute.equals(" # ")) {
		 			User user = new User(firstName, lastName, username, password, mail);
						if (userBean.add(user)) {
				//			MessageBean messageBean = new MessageBean();
					//		session.setAttribute("messageBean", messageBean);
							System.out.println("C");
							address = "/WEB-INF/pages/profile.jsp";
						}
					} else {
						session.setAttribute("notificationUsername", attribute.split("#")[0]);
						session.setAttribute("notificationMail", attribute.split("#")[1]);
						address = "/WEB-INF/pages/registration.jsp";
					}
				} else {
					address = "/WEB-INF/pages/registration.jsp";
				}
			} catch (Exception e) {
				session.setAttribute("notification", "ERROR: " + e.getMessage());
			}
		} else {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if (userBean == null || !userBean.isLoggedIn()) {
				address = "/WEB-INF/pages/login.jsp";
			} else {
				if (action.equals("messages")) {
					address = "/WEB-INF/pages/messages.jsp";
				} else if (action.equals("newMessage")) {
					address = "/WEB-INF/pages/new_message.jsp";
			//		MessageBean messageBean = (MessageBean) session.getAttribute("messageBean");
					if (request.getParameter("submit") != null && request.getParameter("text") != null) {
						try {
							String userInfo = userBean.getUser().getLastName() + " " + userBean.getUser().getFirstName()
									+ " (" + userBean.getUser().getUsername() + ")";
							String date = new SimpleDateFormat("dd.MM.yyyy. HH:mm").format(new Date());
					//		Message book = new Message(request.getParameter("text"), userInfo, date,
					//				request.getRemoteAddr(), 0);
						//	if (messageBean.add(book))
						//		address = "/WEB-INF/pages/messages.jsp";
						} catch (Exception e) {
							session.setAttribute("notification", "ERROR: " + e.getMessage());
						}
					}

				} else {
					address = "/WEB-INF/pages/404.jsp";
				}
			}

		}	 
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
