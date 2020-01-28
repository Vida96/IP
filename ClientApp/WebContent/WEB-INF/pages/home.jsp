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
<style>
.weatherContainer {
    background-color: rgba(0, 0, 0, 0.25);
    background-image: url("https://lh3.googleusercontent.com/tFLhMKVH90elgpEiYflqzUv8W6sBlefoxkFH7TpmT-8bRWpIJQaEl6GZjAZ9RIPUziZhtRU9=w640-h400-e365");
    box-shadow: 1px 1px 5px black;
    padding: 30px;
    border-radius: 10px;
    visibility: hidden;
    width:100%;
}
.weatherContainer h1 {
    margin: 5px;
    font-size: 25px;
    font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}
.searchContainer {
    padding: 15px;
    position: absolute;
    top: 0;
    right: 0;
}
.weatherMain {
    display: block;
    margin-bottom: 20px;
}
.weatherMain div {
    display: inline-block;
}
.weatherDescriptionHeader {
    font-size: 22px;
    vertical-align: 50%;
}
.temperature {
    font-size: 38px;
    vertical-align: 25%;
}
.bottom-details {
    display: block;
    font-size: 15px;
    text-align: right;
}
hr {
    margin-bottom: 20px;
}
</style>
<style>
.preview-images-zone {
    border: 1px solid #ddd;
    width: 100%;
    min-height: 150px;
    /* display: flex; */
    padding: 5px 5px 0px 5px;
    position: relative;
    overflow:auto;
}
.preview-images-zone > .preview-image:first-child {
    height: 140px;
    width: 140px;
    position: relative;
    margin-right: 5px;
}
.preview-images-zone > .preview-image {
    height: 120px;
    width: 120px;
    position: relative;
    margin-right: 5px;
    float: left;
    margin-bottom: 5px;
}
.preview-images-zone > .preview-image > .image-zone {
    width: 100%;
    height: 100%;
}
.preview-images-zone > .preview-image > .image-zone > img {
    width: 100%;
    height: 100%;
}
.preview-images-zone > .preview-image > .tools-edit-image {
    position: absolute;
    z-index: 100;
    color: #fff;
    bottom: 0;
    width: 100%;
    text-align: center;
    margin-bottom: 10px;
    display: none;
}
.preview-images-zone > .preview-image > .image-cancel {
    font-size: 18px;
    position: absolute;
    top: 0;
    right: 0;
    font-weight: bold;
    margin-right: 10px;
    cursor: pointer;
    display: none;
    z-index: 100;
}
.preview-image:hover > .image-zone {
    cursor: move;
    opacity: .5;
}
.preview-image:hover > .tools-edit-image,
.preview-image:hover > .image-cancel {
    display: block;
}
.ui-sortable-helper {
    width: 90px !important;
    height: 90px !important;
}

.container {
    padding-top: 50px;
}
    .pb-cmnt-textarea {
        resize: none;
    }
</style>
<style>
.multiselect {
 position: absolute;
   z-index:1;
   height:70px; 
   max-height: 90px;
   overflow-y: scroll;
   overflow:auto;
background-color:  #ffffff;;
   
}

::-webkit-scrollbar {
  width: 1em;
}

::-webkit-scrollbar-track {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}

::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #000000;
  outline: 2px solid slategrey;
}

.multiselect {
  width: 200px;
}

.selectBox {
  position: relative;
}

.selectBox select {
  width: 100%;
  font-weight: bold;
}

.overSelect {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}

#checkboxes {
  display: none;
  border: 1px #dadada solid;
}

#checkboxes label {
  display: block;
}

#checkboxes label:hover {
  background-color: #1e90ff;
}
 </style>
<title>Prikaz obavjestenja o opasnosti</title>
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
                       		differnce = result.toString() + " h";
                       	}else
                       	{
                       		differnce = result.toString() + " min";
                       	}
                       	if(result < 0 )
                       		System.out.println((new Date())  + " " + (postTime));
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
                                <h3 id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Detalji potencijalne opasnosti</h3>
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
                       		differnce = result.toString() + " h";
                       	}else
                       	{
                       		differnce = result.toString() + " min";
                       	}
                       	if(result < 0 )
                       		System.out.println((new Date())  + " " + (postTime));
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
                        if(images != null){
                        if(images.size() > 0)
                        	firstImage = images.get(0);
                      
                        for(String image : images){
                        %>
                        <img width="200" height="200"  class="center-block img-responsive" src='<%=image%>' />
                        <%}}%>
                        
                        <p class="card-text">
                             <% out.println(p.getText()); %>
                        </p>
                       	<%
                       	String link;
                       	if((link = p.getLink()) != null){%>
                        <a href=<%=link%> class="card-link">Pročitajte više</a>
                        <%}%>
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