<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.etfbl.dto.User"%>
<jsp:useBean id="userBean" type="net.etfbl.beans.UserBean" scope="session"/>

<script><%@include file="/js/profile.js"%></script>
<script><%@include file="/js/script.js"%></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<%
	//	int id = Integer.parseInt(request.getParameter("id"));
		User user = userBean.getUser();
	%>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<jsp:include page="../pages/profileUpdate.jsp"/>
<hr>	
<div class="container bootstrap snippet">
    <div class="row">
  		<div class="col-sm-10"><h1>Izmjena profila</h1></div>
    </div>
    <div class="row">
  		<div class="col-sm-3"><!--left col-->
               
      <div class="text-center">
     <%
     String userPhoto = user.getPhoto();
     if(userPhoto == null){ %>
        <img id="profileImage" src="https://www.serenbooks.com/sites/default/files/default_images/default-user-image.png" class="avatar img-circle img-thumbnail" alt="avatar">
     <%}else{ %>  
        <img id="profileImage" src=<%=userPhoto%> class="avatar img-circle img-thumbnail" alt="avatar">
    
        <%} %>
        <input type='file' id='selectedImage' class='hidden' onChange='handleChange()' />  
<label for="selectedImage">Odaberite profilnu sliku</label>
      </div></hr><br>

               
          
        </div><!--/col-3-->
    	<div class="col-sm-9">   
          <div class="tab-content">
            <div class="tab-pane active" id="home">
                <hr>
                  <form method="POST" action="?action=login" id="profileForm">
                      <div class="form-group">
                          
                          <div class="col-xs-6">
                              <label for="firstName"><h4>Ime</h4></label>
                              <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Unesite vaše ime" oninvalid="this.setCustomValidity('Unesite pravilno ime.')" oninput="this.setCustomValidity('')" required value=<%= user.getFirstName() %>>
                           <label id="firstNameLabel"></label>
                          </div>
                      </div>
          
                      <div class="form-group">
                          
                          <div class="col-xs-6">
                              <label for="lastName"><h4>Prezime</h4></label>
                              <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Unesite vaše prezime" oninvalid="this.setCustomValidity('Unesite pravilno prezime.')" oninput="this.setCustomValidity('')" required value=<%= user.getLastName() %>>
                         <label id="lastNameLabel"></label>
                          </div>
                      </div>
          
                      <div class="form-group">
                          <div class="col-xs-6">
                             <label for="username"><h4>Korisničko ime</h4></label>
                              <input type="text" class="form-control" name="username" id="username" placeholder="Unesite vaše korisničko ime" oninvalid="this.setCustomValidity('Unesite pravilno korisničko ime.')" oninput="this.setCustomValidity('')" required value=<%= user.getUsername() %>>
                           <label id="usernameLabel"></label>
                          </div>
                      </div>
                      <div class="form-group">
             
                          <div class="col-xs-6">
                              <label for="email"><h4>Email</h4></label>
                              <input type="email" class="form-control" name="email" id="email" placeholder="vaš@email.com" title="enter your email." required pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}"  oninvalid="this.setCustomValidity('Unesite pravilno mail. ')" onchange="try{setCustomValidity('')}catch(e){}" oninput="setCustomValidity(' ')"value=<%= user.getMail() %>>
                          <label id="mailLabel"></label>
                          </div>
                      </div>
                      <div class="form-group">
                          
                          <div class="col-xs-6">
                              <label for="password"><h4>Lozinka</h4></label>
                              <input type="password" class="form-control" name="password" id="password" placeholder="Unesite lozinku" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required value=<%= user.getPassword() %>>
                        <label id="passwordLabel"></label>
                          </div>
                      </div>
                      <div class="form-group">
                          
                          <div class="col-xs-6">
                              <label for="password"><h4>Potvrda lozinke</h4></label>
                              <input type="password" class="form-control" name="confirmedPassword" id="confirmedPassword" placeholder="Potvrdite lozinku" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required value=<%= user.getPassword() %>>
                         	  <label id="confirmedPasswordLabel"></label>
                          </div>
                      </div>
                      <div class="form-group">
                          <div class="col-xs-7">
                             <label for="country"><h4>Država</h4></label>
                             
                               <select style="height:30px;" class="form-control" id="country"  onChange="fillRegions()">
                <script>fillCountries();</script> 
                <%String country = userBean.getUser().getCountry();
                if(country != null){ %>
                <option selected>  
  				<%=country%>
  				</option> 
  				<%}%>
                </select>
                         </div>
                         </div>
                     
                       <div class="form-group">
                    <div class="col-xs-7">
                             <label for="region"><h4>Region</h4></label>
                               <select style="height:30px;" class="form-control" id="region"  onChange="fillCities()">
                               <%String region = userBean.getUser().getRegion();
                				if(region != null){ %>
                				<option selected>  
  								<%=region%>
  								</option> 
  								<%}%>
                </select>
                         </div>
                         </div> 
                    
                       <div class="form-group">
                    <div class="col-xs-7">
                             <label for="city"><h4>Grad</h4></label>
                               <select style="height:30px;" id="city" class="form-control">
                                <%String city = userBean.getUser().getCity();
                				if(city != null){ %>
                				<option selected>  
  								<%=city%>
  								</option> 
  								<%}%>
               		 </select>
                         </div>
                         </div> 
                      
                       <div class="form-group">
                    <div class="col-xs-7">
                    <br>
                    <%Integer notificationOnMail = userBean.getUser().getNotificationOnMail();
                   	  Integer notificationInApp = userBean.getUser().getNotificationInApp();	
                      String display;
                      String nOM = "";
                      String nIA = ""; 
                      String cbChecked ="";
                   	  if(notificationOnMail == 1){
                   		 display = "block";
       					 nOM = "checked";
       				   	 cbChecked = "checked";
                      }else if(notificationInApp == 1){
                    		 display = "block";
           					 nIA = "checked";
           				   	 cbChecked = "checked";
                      }
                   	   else
                    	 display = "none";
                    %>
                            <input type="checkbox" class="custom-control-input" id="notifications-blog" checked="<%=cbChecked%>"onChange="toggle_visibility()">
                                <label style="padding-left: 20px;" class="custom-control-label" for="notifications-blog">Notifikacije o hitnim upozorenjima</label>
                                    <div class="col-sm-6" id="radioButtons" style="display:<%=display%>">
                        <div class="row">
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="notifications" value="notificationsOnMail" checked="<%=nOM%>">Na mail
                                </label>
                            </div>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="notifications" value="notificationsInApp" checked="<%=nIA%>" style="padding-right: 20px;">U aplikaciji
                                </label>
                            </div>
                        </div>
                    </div>
                
                         </div>
                         </div>         
                              
                      <div class="form-group">
                           <div class="col-xs-12">
                                <br>
                              	<button class="btn btn-lg btn-success" type="submit" onclick="event.preventDefault(); return validateFields(<%=userBean.getUser().getNumberOfLogins()%>)"><i class="glyphicon glyphicon-ok-sign"></i> Sačuvaj</button>
                               	&nbsp&nbsp<label style="color: green" id="successLabel"></label>
                            </div>
                      </div>
              	</form>
              
              <hr>
              
             </div><!--/tab-pane-->
              </div><!--/tab-content-->

        </div><!--/col-9-->
    </div><!--/row-->
                                                      