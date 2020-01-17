package net.etfbl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import net.etfbl.beans.UserBean;
import net.etfbl.dao.ImageDAO;
import net.etfbl.dao.PostDAO;
import net.etfbl.dao.UserDAO;
import net.etfbl.dto.Post;
import net.etfbl.dto.User;

@WebServlet("/Post")
public class PostContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Properties properties = System.getProperties();  
 
	private Session session = Session.getDefaultInstance(properties);  
	
    public PostContoller() {
        super();
    	properties.setProperty("mail.smtp.host", "localhost");  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		if((userBean != null) && (userBean.isLoggedIn())){ 
		String jsonText = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject jsonObject = new JSONObject(jsonText);
		createDanger(jsonObject, request, userBean);
		}
	}

	private void createDanger(JSONObject jsonObject, HttpServletRequest request, UserBean userBean) {
		String description = null, location = null, video = null;
		List<String> images = null;
		Integer[] categoriesId = null;
		
		if (!jsonObject.isNull("description")){
			description = jsonObject.getString("description");
		}
		if (!jsonObject.isNull("location")){
			location = jsonObject.getString("location");
		}
		if (!jsonObject.isNull("checkboxesChecked")){
			JSONArray jsonCategoriesId = jsonObject.getJSONArray("checkboxesChecked");
			categoriesId = new Integer[jsonCategoriesId.length()];
			for(int i = 0; i < jsonCategoriesId.length(); i++)
				categoriesId[i] = jsonCategoriesId.getInt(i); //lista ID-eva kategorija kojoj objava pripada
		}
		if (!jsonObject.isNull("images")){
			JSONArray jsonImages = jsonObject.getJSONArray("images");
			images = new ArrayList<>();
			for(int i = 0; i < jsonImages.length(); i++)
				images.add(jsonImages.getString(i));
		}
		if (!jsonObject.isNull("video")){
			video = jsonObject.getString("video");
		}

		Integer isEmergency = jsonObject.getBoolean("isEmergency")? 1 : 0;
		User postCreator = userBean.getUser();
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		Post post = new Post(description, postCreator, currentTime, location, video, isEmergency);
		post.setImages(images);
		post.setUserId(userBean.getUser().getId());
		PostDAO.insert(post);
		ImageDAO.insert(images, 1); //postId
		if(isEmergency == 1 )
		{
			sendMailToUsers(post, userBean.getUser().getMail());
		}
		
	}

	private void sendMailToUsers(Post post, String senderMail) {
		List<String> usersMails = UserDAO.getUsersMailsForEmergencyMail(senderMail);

	 for(String recieverMail : usersMails) {
		System.out.println(recieverMail);	
		} 
	}

	private void sendMail(String senderMail, String recieverMail, Post post) {
		  // Recipient's email ID needs to be mentioned.
        String to = "nikolavidovic813@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "nikolavidovic813@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("nikolavidovic813@gmail.com", "srbin1996");

            }

        });

        // Used to debug SMTP issues
//        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
}