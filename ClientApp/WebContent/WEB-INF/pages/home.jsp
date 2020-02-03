<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.etfbl.dto.User"%>
<%@page import="net.etfbl.dto.Post"%>
<%@page import="net.etfbl.dto.Comment"%>
<%@page import="net.etfbl.dto.PostCategory"%>
<%@page import="net.etfbl.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<jsp:useBean id="userBean" type="net.etfbl.beans.UserBean" scope="session"/>
<jsp:useBean id="postBean" type="net.etfbl.beans.PostBean" scope="session"/>
<jsp:useBean id="postCategoryBean" type="net.etfbl.beans.PostCategoryBean" scope="session"/>
<script><%@include file="/js/home.js"%></script>
<style><%@ include file="/css/home.css"%></style>
<!DOCTYPE html>
<html>
<head>
<script> window.onload = initializeComponents;</script>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places&key=AIzaSyASALqo3BcfC125acwVTv1AspJGx1K-F_E"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Pocetna stranica</title>
</head>
<body>

<jsp:include page="../pages/profileUpdate.jsp"/>
<br>
    <div class="container-fluid gedf-wrapper">
        <div class="row">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h3>
                       
                        <%
                        out.println(userBean.getUser().getFirstName() + " " + userBean.getUser().getLastName());
                        %>
                        </h3>
                        <img id="profileImage" style="width:100%" src=<%=userBean.getUser().getPhoto()%> />
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <div class="h6 text-muted">Broj prijava</div>
                            <div class="h5">
                             <%
                             out.println(userBean.getUser().getNumberOfLogins());
                             %>
                            </div>
                        </li>
                     </ul>
						</div>
						  <%if(userBean.getUser().getNotificationInApp() == 1){ %>
						<!-- Post /////-->
		<div class="emergencyPostsZone">

   						   <%
				               List<Post> posts = postBean.getAllActiveEmergencyPosts();
   						   	   int[] i = {0};
				           	   for(Post p : posts){
				           	%>
				           	
				           	                <div style=" margin-top: 15px;" class="card gedf-card">
                    <div class="card-header">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="mr-2">
                                    <img class="rounded-circle" width="70" src=<%=p.getCreator().getPhoto()%> alt="">
                                </div>
                                <div class="ml-2">
                                    <div class="h5 m-0">@<%out.println(p.getCreator().getUsername());%></div>
                                    <div class="h7 text-muted"><%out.println(p.getCreator().getFirstName() + " " + p.getCreator().getLastName());%></div>
                                </div>
                            </div>
                        </div>

                    </div>
                             <%
                        Date postTime = p.getCreationTime();
                        Long result = ((new Date().getTime()/60000) - (postTime.getTime()/60000));
                       	String differnce;
                       	if(result > 60)
                       	{
                       		result /= 60;
                       		if(result > 730){
                           		result /= 730;
                           		differnce = result.toString() + " mjeseci";
                           	}else
                       			differnce = result.toString() + " h";
                       	}else
                       	{
                       		differnce = result.toString() + " min";
                       	}
                        %>
                    
                    <div class="card-body">
                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"> Prije <%=differnce%> </i>
                     
                        <%
                        String location = p.getLocation();
                        if(location != null && !location.isEmpty()){%>
                    	<i style="float:right" class="fa fa-map-marker" aria-hidden="true"> <%=location%></i>
                        <%}%>
                        </div>
                        <h3 class="card-title">
                             <%
                            List<String> categories = p.getCategories();	
                            if(categories != null){
                            	for(String category : categories)
                            		if(categories.indexOf(category) == 0)
                            			out.print("Kategorije opasnosti: " + category);
                            		else
                            			out.print(", " + category);
                            }
                            %>
                            </h3>
                        <%
   						List<String> images = p.getImages();
                        for(String image : images){%>
                        <img width="200" height="200"  class="center-block img-responsive" src='<%=image%>' />
                        <%}%>
                        
                        <p class="card-text">
                             <% out.println(p.getText()); %>
                        </p>
                       	<%
                       	String link;
                       	if((link = p.getLink()) != null){%>
                        <a href=<%=link%> class="card-link">Pročitajte više</a>
                        <%}%>
                    </div>
                </div>
				      <br>     	
				           	 <%
				           	 i[0]++;
				           	 } %>



 <!--- \\\\\\\Post-->
                <!-- Post /////-->

      <br>
                 
        
        </div> <!-- ne diraj ovaj tag kada budes brisao -->
                        <%} %>

            </div>
            <div class="col-md-6 gedf-main">
                <!--- \\\\\\\Post-->
                <div class="card gedf-card">
                    <div class="card-header">
                                <h3 id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Detalji nove objave</h3>
                    </div>
                    
                    <div class="card-body">
                     <label>Odabir kategorije:</label>
                    <div class="multiselect" style="float:right">
    <div class="selectBox" >
      <select id="selectedCategories">
       </select>
      <div class="overSelect" id="txtArea"></div>
    </div>
    <div id="checkboxes">
       						   <%
				               List<PostCategory> postCategories = postCategoryBean.getAllActivePostCategories();
				           	   for(PostCategory pc : postCategories){
				           	%>
				           	<label style="font-weight:bold"><input id="<%=pc.getId() + " " + pc.getName()%>" class="checkboxes" type="checkbox" name="cbCategory" onClick="fillArea();"/> <%out.println(pc.getName());%></label>
				           	<hr>
                        <%} %>
    </div>
  </div>
  <!-- Autocomplete location search input --> 

<br>
<br>
<br>
<div class="form-group">
    <label>Lokacija opasnosti:</label>
    <input style="width:250px" type="text" class="form-control" id="searchInput" placeholder="Unesite lokaciju" />
      <div style="float:right">
<label>
<span>Ukoliko se radi o hitnom upozorenju, molimo Vas da oznacite</span>
<input type="checkbox" id="emergencyCb" class="checkbox style-2 right">
</label> </div>
</div>
                            <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                                <div class="form-group">
                                    <label>Opis potencijalne opasnosti:</label>
                                    <textarea class="form-control" id="dangerDetails" rows="3" placeholder="Unesite tekst ili link"></textarea>
                                </div>
                            </div>
                        
                         <fieldset class="form-group">
        <a href="javascript:void(0)" onclick="$('#pro-image').click()">Dodaj sliku ili video</a>
        <input accept="image/*,video/*" type="file" id="pro-image" name="pro-image" style="display: none;" class="form-control" onChange="readImage()" multiple>
    </fieldset>
                                   <div class="preview-images-zone">
    </div>
  
<br>

<br>
   <% 
   List<Post> posts = postBean.getAllActiveUnemergencyPosts();
  	  %>  
                        <div style="float:right" class="btn-toolbar justify-content-between">
                            <div class="btn-group">
                                <button type="submit" class="btn btn-primary" onClick="shareDanger('<%=posts.size()%>', '<%=userBean.getUser().getUsername()%>', '<%=userBean.getUser().getFirstName() + " " + userBean.getUser().getLastName()%>','<%=userBean.getUser().getPhoto()%>')">Podjeli</button>
                            </div>
                            
                        </div>
                    </div>
                </div>
 
                
                <!-- Post /////-->
<div class="postsZone">

   						  <%
   						 	   int[] i = {0}; 
				           	   for(Post p : posts){
				           	%>
				           	
				           	                <div style=" margin-top: 15px;" class="card gedf-card">
                    <div class="card-header">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="mr-2">
                                    <img class="rounded-circle" width="70" src=<%=p.getCreator().getPhoto()%> alt="">
                                </div>
                                <div class="ml-2">
                                    <div class="h5 m-0">@<%out.println(p.getCreator().getUsername());%></div>
                                    <div class="h7 text-muted"><%out.println(p.getCreator().getFirstName() + " " + p.getCreator().getLastName());%></div>
                                </div>
                            </div>
                        </div>

                    </div>
                             <%
                        Date postTime = p.getCreationTime();
                        Long result = ((new Date().getTime()/60000) - (postTime.getTime()/60000));
                       	String differnce;
                        if(result > 60)
                       	{
                       		result /= 60;
                        	if(result > 730){
                           		result /= 730;
                           		differnce = result.toString() + " mjeseci";
                           	}else{
                       		differnce = result.toString() + " h";
                       	}}else
                       	{
                       		differnce = result.toString() + " min";
                       	}
                        %>
                    
                    <div class="card-body">
                        <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"> Prije <%=differnce%> </i>
                     
                        <%
                        String location = p.getLocation();
                        if(location != null && !location.isEmpty()){%>
                    	<i style="float:right" class="fa fa-map-marker" aria-hidden="true"> <%=location%></i>
                        <%}%>
                        </div>
                        <h3 class="card-title">
                             <%
                            List<String> categories = p.getCategories();	
                            if(categories != null){
                            	for(String category : categories)
                            		if(categories.indexOf(category) == 0)
                            			out.print("Kategorije opasnosti: " + category);
                            		else
                            			out.print(", " + category);
                            }
                            %>
                            </h3>
                        <%
   						List<String> images = p.getImages();
                        String firstImage ="";
                        String video = p.getVideo();
                        
                        if(images != null){
                        if(images.size() > 0)
                        	firstImage = images.get(0);
                      
                        for(String image : images){
                        %>
                        <img width="200" height="200"  class="center-block img-responsive" src='<%=image%>' />
                        <%}}%>
                        <%if(video != null){%>
                  	     <video style="width:200px;height:200px" controls autoplay src='<%=video%>'></video>
                        <%} %>
                        <p class="card-text">
                             <% out.println(p.getText()); %>
                        </p>
                       	<%
                       	String link;
                       	if((link = p.getLink()) != null){
                       	if(link.contains("youtube")){%>
                       	<a href=<%=link%> class="card-link">Pogledajte više</a>	
                       	<%}else{%>
                        <a href=<%=link%> class="card-link">Pročitajte više</a>
                        <%}}%>
                    </div>
                    <div class="card-footer">
                       <%if(!p.getIsFeed()){%>
                       <button type="button" onClick="focusCommentBox(<%=i[0]%>)" class="btn btn-link"><i class="fa fa-comment"></i>Komentariši</button>
                       <%}%>
                       <button type="button" onClick="focusShareOnFacebook('<%=p.getText()%>', '<%=firstImage%>', '<%=location%>','<%=p.getLink()%>' )" class="btn btn-link"><i class="fa fa-facebook-square"></i>Podijeli na fb</button>
                       <button type="button" onClick="focusShareOnTwitter('<%=p.getText()%>','<%=p.getLink()%>', '<%=userBean.getUser().getUsername()%>', '<%=location%>')" class="btn btn-link"><i class="fa fa-twitter-square"></i>Podijeli na twitter</button>
                    </div>
                    <%if(!p.getIsFeed()){ %>
                    <div class="commentsZone<%=i[0]%>">  
                    <% List<Comment> postComments = p.getCommments();   
                    for(Comment comment : postComments){
                    	User user = UserDAO.getById(comment.getUserId()); 
                        Date commentTime = comment.getTime();
                        Long resultTime = ((new Date().getTime()/60000) - (commentTime.getTime()/60000));
                       	String differnceTime;
                        if(resultTime > 60)
                       	{
                        	resultTime /= 60;
                       		
                        	if(result > 730){
                           		result /= 730;
                           		differnceTime = result.toString() + " mjeseci";
                           	}else
                       			differnceTime = resultTime.toString() + " h";
                       	}else
                       	{
                       		differnceTime = resultTime.toString() + " min";
                       	}
                        if(resultTime < 0 )
                       		System.out.println((new Date())  + " " + (commentTime));
                        
                        %>
                    
                    <div class="row" style="margin:5px" >
    <div class="col-sm-2 text-center">
                <img class="rounded-circle" width="60" src="<%=user.getPhoto()%>" alt="">
    </div>
    <div class="col-sm-10">
      <h4><%=user.getFirstName()  + " " + user.getLastName()%></h4>
      <div class="text-muted h7 mb-2"><i class="fa fa-clock-o"></i> Prije <%=differnceTime%></div>
      <p><%=comment.getText()%></p>
      <%String image = comment.getImage();
      if(image!= null){%>
      <img height="170" width="170" src='<%=image%>'>
      <%}%>
      <br>
      </div>
    </div>
    <%  if(postComments.indexOf(comment) != postComments.size()-1){ %>
    	<hr>	
    <%}}%>
    <div class="card gedf-card">
            <div class="panel panel-info">
                <div class="panel-body">
                    <textarea id="commentBox<%=i[0]%>" style="width:100%" placeholder="Napišite svoj komentar ovdje" class="pb-cmnt-textarea" onkeypress="onEnterPress('<%=i[0]%>', '<%=userBean.getUser().getUsername()%>', '<%=userBean.getUser().getPhoto()%>', '<%=p.getId()%>', '<%=userBean.getUser().getId()%>');"></textarea>
      <div style="float:right; display: none;" class="preview-images-zone<%=i[0]%>"></div>      
            </div>
        </div>
    </div>  
                </div>
                    <fieldset style="float:right" class="form-group">
        <a  href="javascript:void(0)" style="float:right;" onclick="$('#pro-image2<%=i[0]%>').click()"><span class="fa fa-picture-o fa-lg"></span>Dodaj sliku</a>
      
        <input type="file" id="pro-image2<%=i[0]%>" name="pro-image2<%=i[0]%>" style="display: none;" class="form-control" onChange="readImageForComment('<%=i[0] %>')"></input>
    </fieldset>
    <%}%>
                </div>
				      <br>     	
				           	 <%
				           	 i[0]++;
				           	 } %>


                <!--- \\\\\\\Post-->
      <br>
	  </div> <!-- ne diraj ovaj tag kada budes brisao -->
      <br><br><br>
      </div>
            <div class="col-md-3">
                <div class="card gedf-card">
                    <div class="weatherContainer" id="weatherContainer0">
            <div class="weatherDescription" id="weatherDescription0">
                <h1 id="cityHeader0"></h1>
                <div class="weatherMain" id="weatherMain0">
                    <div class="temperature" id="temperature0"></div>
                    <div class="weatherDescriptionHeader" id="weatherDescriptionHeader0"></div>
                    <div><img id="documentIconImg0"></div>
                </div>
                <hr>
                <div id="windSpeed0" class="bottom-details"></div>
                <div id="humidity0" class="bottom-details"></div>
            </div>
        </div>
         <br>
            <div class="weatherContainer" id="weatherContainer1">
            <div class="weatherDescription" id="weatherDescription1">
                <h1 id="cityHeader1"></h1>
                <div class="weatherMain" id="weatherMain1">
                    <div class="temperature" id="temperature1"></div>
                    <div class="weatherDescriptionHeader" id="weatherDescriptionHeader1"></div>
                    <div><img id="documentIconImg1"></div>
                </div>
                <hr>
                <div id="windSpeed1" class="bottom-details"></div>
                <div id="humidity1" class="bottom-details"></div>
            </div>
        </div>
        <br>
           <div class="weatherContainer" id="weatherContainer2">
            <div class="weatherDescription" id="weatherDescription2">
                <h1 id="cityHeader2"></h1>
                <div class="weatherMain" id="weatherMain2">
                    <div class="temperature" id="temperature2"></div>
                    <div class="weatherDescriptionHeader" id="weatherDescriptionHeader2"></div>
                    <div><img id="documentIconImg2"></div>
                </div>
                <hr>
                <div id="windSpeed2" class="bottom-details"></div>
                <div id="humidity2" class="bottom-details"></div>
            </div>
        </div>
                </div>
                
                
                
               <script>
               var country = "<%= userBean.getUser().getCountry() %>";
           //    searchWeatherForCities(country);
           </script>
  
            </div>
        </div>
    </div>
</body>
</html>