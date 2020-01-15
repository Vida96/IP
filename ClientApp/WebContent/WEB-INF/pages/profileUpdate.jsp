<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
 
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<title>Insert title here</title>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <ul class="navbar-nav mr-auto">
  <li class="nav-item active">
    <a class="nav-link" href="Home">Početna strana<span class="sr-only">(current)</span></a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="Profile">Izmjena profila</a>
  </li>
</ul>
<ul class="navbar-nav ml-auto">
  <li class="nav-item">
    <a class="nav-link" href="Login?action=signOut">Odjava</a>
  </li>
</ul>
</nav>
<br>
<jsp:include page="../pages/profile.jsp"/>
</body>
</html>