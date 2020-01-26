package net.etfbl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import net.etfbl.dao.PostCategoryDAO;
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
		String description = null, location = null, video = null, link = null;
		List<String> images = null;
		List<Integer> categoriesId = null;
		
		if (!jsonObject.isNull("description")){
			description = jsonObject.getString("description");
		}
		if (!jsonObject.isNull("location")){
			location = jsonObject.getString("location");
		}
		if (!jsonObject.isNull("checkboxesChecked")){
			JSONArray jsonCategoriesId = jsonObject.getJSONArray("checkboxesChecked");
			categoriesId = new ArrayList<Integer>();
			for(int i = 0; i < jsonCategoriesId.length(); i++)
				categoriesId.add(jsonCategoriesId.getInt(i)); //lista ID-eva kategorija kojoj objava pripada
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
		if (!jsonObject.isNull("link")){
			link = jsonObject.getString("link");
		}
		
		Integer isEmergency = jsonObject.getBoolean("isEmergency")? 1 : 0;
		User postCreator = userBean.getUser();
		
		java.util.Date postTime = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(postTime);
		
		Post post = new Post(description, postCreator, postTime, location, video, isEmergency, link);
		post.setImages(images);
		post.setUserId(userBean.getUser().getId());
		Integer postId = PostDAO.insert(post);
		ImageDAO.insert(images, postId);
		PostCategoryDAO.insert(categoriesId, postId); 
		if(isEmergency == 1 )
		{
			sendMailToUsers(post, userBean.getUser().getMail());
		}
		
	}

	private void sendMailToUsers(Post post, String senderMail) {
		List<String> usersMails = UserDAO.getUsersMailsForEmergencyMail(senderMail);
sendMail("", "", post);
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
       /* 	Message msg = new MimeMessage(session);
        	 
          
        	// Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");
*/
            // Now set the actual message
         //   message.setText("This is actual message");
        //    String msg2 = getMessageContent(post); // "<i>Greetings!</i><br>";
          //  msg2 += "<b>Wish you a nice day!</b><br>";
           // msg2 += "<font color=red>Duke</font><br>";
          //  msg2 +="<img src='https://www.pngitem.com/pimgs/m/247-2473457_current-location-icon-png-location-vector-icon-transparent.png' alt='Smiley face' height='100' width='100'>";
           // message.setContent(msg2, "text/html");
            
            
        	Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(
               "C:/Users/Nikola/Desktop/download.jfif");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            fds = new FileDataSource("C:/Users/Nikola/Desktop/images.jfif");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            
         
            // put everything together
      
            message.setContent(multipart);
                 System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }    
	}
	
	public String getMessageContent(Post post) {
			String imagesHtml[] = {""};
		    String categoriesHtml = "";
		    String locationHtml = "";
		    String descriptionHtml = "";
		    Integer i = 0; 
		    String location;
		    String description;
		    String link;
		    
		    /* categoriesNames.forEach(function (category, index) {
		    	
				if(categoriesNames.indexOf(category) == 0)
					categoriesHtml+= "<h3 class='card-title'>Kategorije opasnosti: " + category;
				else
					categoriesHtml+= ", " + category;
				
				if (categoriesNames.indexOf(category) == (categoriesNames.length - 1))
					categoriesHtml+=" </h3>";  
		    }); */
		   
		    List<String> images = post.getImages(); 
		    images.forEach(image -> {
		//    	if(images.indexOf(image) == 0  && images.indexOf(image) == 4)
			 imagesHtml[0] +="<img src=" + image +" alt='Smiley face' height='100' width='100'>";
				
		    	imagesHtml[0] +="<img src=" + image + "height='100' width='100'>";
		 //       else
		   // 		 imagesHtml[0] +="<img src='https://www.pngitem.com/pimgs/m/247-2473457_current-location-icon-png-location-vector-icon-transparent.png' alt='Smiley face' height='100' width='100'>";
		     System.out.println(image);      
		    	}); 
		    if(imagesHtml[0] != ""){
		    	imagesHtml[0] += "<br><br><br><br><br><br><br><br><br>";
		    }
		    String linkHtml = "";

		    if((location = post.getLocation()) != null && location == ""){
		    	locationHtml+= "<i style='float:right' class='fa fa-map-marker' aria-hidden='true'>&nbsp;" + location + "</i>";
		    }
		    if((description = post.getText()) != null){
		    	descriptionHtml+="<p>" + description + "<br>";
		    }
		    if((link = post.getLink()) != null){
		    	linkHtml+="<a href=" + link + "class='card-link'>Pro&#269;itajte vi&#353;e</a>";
		    }
		    
		    return imagesHtml[0] + locationHtml + descriptionHtml + linkHtml;
	}	
}