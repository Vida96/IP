<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script><%@include file="/js/login.js"%></script>
	<style><%@include file="/css/login.css"%></style>
	<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Forma za prijavljivanje</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<style type="text/css">
	body{
		color: #fff;
		background: #63738a;
		font-family: 'Roboto', sans-serif;
		margin-top:70px;
	}
</style>
</head>
<body>
<div class="signup-form">
     <form id="registrationForm">
		<h1 align="center">Prijava</h1>
        <div class="form-group">
                                <input type="text" class="form-control" name="username" id="username" placeholder="Unesite vaše korisničko ime" oninvalid="this.setCustomValidity('Unesite pravilno korisničko ime.')" oninput="this.setCustomValidity('')" required >
      <label id="usernameLabel"></label>
        </div>
    	<div class="form-group">
                          <input type="password" class="form-control" name="password" id="password" placeholder="Unesite lozinku" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required>
      <label id="passwordLabel"></label>
        </div>
            <button type="submit" class="btn btn-success btn-lg btn-block" onclick="event.preventDefault(); return validateFields()">Prijavite se</button>
      <label id="errorLabel"></label>      
    </form>
    </div>
	<div class="text-center"><h4>Nemate nalog? <a href="Registration?action=registration" style="color:black">Registrujte se</a></h4></div>
</body>
</html>         