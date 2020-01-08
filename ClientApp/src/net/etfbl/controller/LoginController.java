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

@WebServlet("/Login")
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
  
		if (action == null || action.equals("")) {
  			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			//pozvati UserBean, a zatim odraditi i odjavljivanje korisnika
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("login")) {
			UserBean userBean = new UserBean();
			String username = request.getParameter("username");
			String password = request.getParameter("password");	
			if (userBean.login(username, password)) {
				session.setAttribute("userBean", userBean);
			//	MessageBean messageBean = new MessageBean();
		//		session.setAttribute("messageBean", messageBean);
				address = "/WEB-INF/pages/messages.jsp";
			}  
		}  
		else
			address = "/WEB-INF/pages/login.jsp";
		 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
