package net.etfbl.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    public RegistrationController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (("registration").equals(action)) {
			address = "/WEB-INF/pages/registration.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String jsonText = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject jsonObject = new JSONObject(jsonText);
		String action = jsonObject.getString("action");
		HttpSession session = request.getSession();
		
		if ("registration".equals(action)) {
			System.out.println(jsonObject.getString("firstName"));
		//	Student student = new Student(0,jsonObject.getString("firstName"),jsonObject.getString("lastName"),jsonObject.getString("username"),jsonObject.getString("password"),jsonObject.getString("mail"),null,null,null,null,0,1 );
			/*	if(studentBean.add(student))
				{
					studentBean.login(session, jsonObject.getString("username"), jsonObject.getString("password"));
					setBeans(request, studentBean);
				}
			}*/
		}
	}
}