<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script><%@include file="/js/registration.js"%></script>
	<style><%@ include file="/css/registration.css" %></style>
	<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<title>Registracija</title>
<style type="text/css">
	body{
		color: #fff;
		background: #63738a;
		margin-top:70px;
	}
</style>
</head>
<body>
<div class="signup-form">
     <form id="registrationForm">
		<h1 class="h1" align="center">Registracija</h1>
		<p class="hint-text">Napravite nalog. Besplatno je i brzo.</p>
        <div class="form-group">
		 <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Unesite vaše ime" required autofocus oninvalid="this.setCustomValidity('Unesite ime')" oninput="this.setCustomValidity('')"required autofocus oninvalid="this.setCustomValidity('Unesite ime')" oninput="this.setCustomValidity('')">
				  <label id="firstNameLabel"></label>
			</div>    
		  
        <div class="form-group">
        	 <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Unesite vaše prezime" required autofocus oninput="this.setCustomValidity('')"required autofocus >
        		<label id="lastNameLabel"></label>
				</div> 
        <div class="form-group">
                                <input type="text" class="form-control" name="username" id="username" placeholder="Unesite vaše korisničko ime" oninvalid="this.setCustomValidity('Unesite pravilno korisničko ime.')" oninput="this.setCustomValidity('')" required >
                                <label id="usernameLabel"></label>
        </div>
        
    	<div class="form-group">
                          <input type="password" class="form-control" name="password" id="password" placeholder="Unesite lozinku" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required>
        				  <label id="passwordLabel"></label>
        </div>
		<div class="form-group">
                         <input type="password" class="form-control" name="confirmedPassword" id="confirmedPassword" placeholder="Potvrdite lozinku" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required>
       		  <label id="confirmedPasswordLabel"></label>
        </div>
            <div class="form-group">
              <input type="email" class="form-control" name="mail" id="mail" placeholder="Unesite vaš gmail">
        	  <label id="mailLabel"></label>
        </div>
		<div class="form-group">
            <button name="submit" class="btn btn-success btn-lg btn-block" onclick="event.preventDefault(); return validateFields()">Registrujte se</button>
        </div>
    </form>
	<div class="text-center"><h4>Već imate nalog? <a style="color:black" href="Login">Prijavite se</a></h4></div>
</div>
</body>
</html>                            