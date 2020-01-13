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
		String jsonText = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject jsonObject = new JSONObject(jsonText);
		createDanger(jsonObject, request);
	}

	private void createDanger(JSONObject jsonObject, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
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
		long timeNow = Calendar.getInstance().getTimeInMillis();
		java.sql.Timestamp ts = new java.sql.Timestamp(timeNow);
		
		Post post = new Post(description, postCreator, ts, location, video, isEmergency);
		post.setImages(images);
		System.out.println(post.toString());
		System.out.println(isEmergency);
 	/*if(isEmergency == 1 )
		{
			sendMailToUsers(post, userBean.getUser().getMail());
		}*/ 
/*		Date dt = new java.util.Date();
		String currentTime = sdf.format(dt);*/

		
	}

	private void sendMailToUsers(Post post, String senderMail) {
		List<String> usersMails = UserDAO.getUsersMailsForEmergencyMail();
		
	/*	for(String recieverMail : usersMails) {
			sendMail(senderMail, recieverMail, post);
		}*/
		sendMail("S", "S", null);
	}

	private void sendMail(String senderMail, String recieverMail, Post post) {
 
		try{   
		         MimeMessage message = new MimeMessage(session);  
		         message.setFrom(new InternetAddress("nikolavidovic47@yahoo.com"));  
		         message.addRecipient(Message.RecipientType.TO,new InternetAddress("nikolavidovic47@yahoo.com"));  
		         message.setSubject("Ping");  
		         message.setText("Hello, this is example of sending email  ");  
		  
		         // Send message  
		         Transport.send(message);  
		         System.out.println("message sent successfully....");  
		  
		      }catch (MessagingException mex) {mex.printStackTrace();}  
	}
}


/*
 https://mkyong.com/java/java-how-to-send-email/
Properties prop = System.getProperties();
prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
prop.put("mail.smtp.auth", "true");
prop.put("mail.smtp.port", "25"); // default port 25

Session session = Session.getInstance(prop, null);
Message msg = new MimeMessage(session);

try {

	// from
    msg.setFrom(new InternetAddress(EMAIL_FROM));

	// to 
    msg.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(EMAIL_TO, false));

	// cc
    msg.setRecipients(Message.RecipientType.CC,
            InternetAddress.parse(EMAIL_TO_CC, false));

	// subject
    msg.setSubject(EMAIL_SUBJECT);
	
	// content 
    msg.setText(EMAIL_TEXT);
	
    msg.setSentDate(new Date());

	// Get SMTPTransport
    SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
	
	// connect
    t.connect(SMTP_SERVER, USERNAME, PASSWORD);
	
	// send
    t.sendMessage(msg, msg.getAllRecipients());

    System.out.println("Response: " + t.getLastServerResponse());

    t.close();

} catch (MessagingException e) {
    e.printStackTrace();
}
*/