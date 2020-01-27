<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script><%@include file="/js/registration.js"%></script>
	<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<title>Forma za registraciju</title>
<style type="text/css">
	body{
		color: #fff;
		background: #63738a;
		margin-top:70px;
	}
    .form-control{
		height: 40px;
	}
	.form-control:focus{
		border-color: #5cb85c;
	}
    .form-control, .btn{        
        border-radius: 3px;
    }
	.signup-form{
		width: 400px;
		margin: 0 auto;
		padding: 30px 0;
	}
	.signup-form h2{
		color: #636363;
        margin: 0 0 15px;
		position: relative;
		text-align: center;
    }
	.signup-form h2:before, .signup-form h2:after{
		content: "";
		height: 2px;
		width: 30%;
		background: #d4d4d4;
		position: absolute;
		top: 50%;
		z-index: 2;
	}	
	.signup-form h2:before{
		left: 0;
	}
	.signup-form h2:after{
		right: 0;
	}
    .signup-form .hint-text{
		color: #999;
		margin-bottom: 30px;
		text-align: center;
	}
    .signup-form form{
		color: #999;
		border-radius: 3px;
    	margin-bottom: 15px;
        background: #f2f3f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.signup-form .form-group{
		margin-bottom: 20px;
	}
	.signup-form input[type="checkbox"]{
		margin-top: 3px;
	}
	.signup-form .btn{        
        font-size: 16px;
        font-weight: bold;		
		min-width: 140px;
        outline: none !important;
    }
	.signup-form .row div:first-child{
		padding-right: 10px;
	}
	.signup-form .row div:last-child{
		padding-left: 10px;
	}    	
    .signup-form a{
		color: #fff;
		text-decoration: underline;
	}
    .signup-form a:hover{
		text-decoration: none;
	}
	.signup-form form a{
		color: #5cb85c;
		text-decoration: none;
	}	
	.signup-form form a:hover{
		text-decoration: underline;
	}  
	@media screen and (max-device-width: 500px) {
      .signup-form {
        width: 300px;
      }
    }
    @media screen and (max-device-width: 400px) {
      .signup-form {
        width: 280px;
      }
      .signup-form .hint-text{
      	font-size:12px;
      }
      .h1{
      	font-size:30px;
      }
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
              <input type="email" class="form-control" name="mail" id="mail" placeholder="vaš@email.com">
        	  <label id="mailLabel"></label>
        </div>
		<div class="form-group">
            <button name="submit" class="btn btn-success btn-lg btn-block" onclick="event.preventDefault(); return validateFields()">Registrujte se</button>
        </div>
    </form>
	<div class="text-center"><h3>Već imate nalog? <a style="color:black" href="Login">Prijavite se</a></h3></div>
</div>
</body>
</html>                            