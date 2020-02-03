<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style><%@ include file="/css/profileUpdate.css" %></style>
<!DOCTYPE html>
<html>
<head>
<title>NavBar</title>
</head>
<body>
 <ul>
  <li>
    <a href="Home">Početna strana<span class="sr-only">(current)</span></a>
  </li>
  <li>
    <a href="Profile">Izmjena profila</a>
  </li>
  <li style="float:right">
    <a href="Login?action=logout">Odjava</a>
  </li>
</ul>
<br>
</body>
</html>