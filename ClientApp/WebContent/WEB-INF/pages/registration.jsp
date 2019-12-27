<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script><%@include file="/js/script.js"%></script>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Registracija</title>
	<!-- Mobile Specific Metas -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- Font-->
	<link rel="stylesheet" type="text/css" href="css/montserrat-font.css">
	<link rel="stylesheet" type="text/css" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
	<!-- Main Style Css -->
    <link rel="stylesheet" href="css/style.css"/>
</head>
<script type="text/javascript" src="script.js"></script>
<body class="form-v10">
	<div class="page-content">
		<div class="form-v10-content">
			<form class="form-detail" action="#" method="post" id="myform">
				<div class="form-left">
					<h2>Osnovne informacije</h2>
					 
					<div class="form-group">
						<div class="form-row form-row-1">
							<input type="text" name="firstName" id="firstName" class="input-text" placeholder="Ime" oninvalid="this.setCustomValidity('Unesite pravilno ime.')" oninput="this.setCustomValidity('')" required>
						</div>
						<div class="form-row form-row-2">
							<input type="text" name="lastName" id="lastName" class="input-text" placeholder="Prezime" oninvalid="this.setCustomValidity('Unesite pravilno prezime.')" oninput="this.setCustomValidity('')" required>
						</div>
					</div>
					 
					<div class="form-row form-row-3">
						<input type="text" name="username" class="input-text id="username" placeholder="Korisničko ime" oninvalid="this.setCustomValidity('Unesite pravilno korisničko ime.')" oninput="this.setCustomValidity('')" required>
					</div>
					
					<div class="form-row form-row-4">
							<input type="password" name="password" class="password" id="password" placeholder="Lozinka" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required>
				   	</div>
				   	
				   	<div class="form-row form-row-5">
							<input type="password" name="password" class="password2" id="password2" placeholder="Potvrda lozinke" oninvalid="this.setCustomValidity('Unesite pravilno lozinku.')" oninput="this.setCustomValidity('')" required>
				   	</div>
				   	
				   	<div class="form-row form-row-6">
							<input type="mail" name="mail" class="input-text" id="mail" placeholder="Mail" required oninvalid="this.setCustomValidity('Unesite pravilno mail.')" oninput="this.setCustomValidity('')" pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}"  title="Username should only contain lowercase letters. e.g. john">
				   	</div>
				</div>
				<div class="form-right">
					<h2>Dodatne informacije</h2>
					<div class="form-group">
						<div class="form-row form-row-2">
							<span class="select-btn">
							  	<i class="zmdi zmdi-chevron-down"></i>
							</span>
						</div>
					</div>
					<div class="form-row">
						<select id="country" name="country">
						    <script>fillCountries();</script>
						</select>
						<span class="select-btn">
						  	<i class="zmdi zmdi-chevron-down"></i>
						</span>
					</div>
					<div class="form-row">
						<select id="region" name="region">
						    <script>fillRegions();</script>
						</select>
						<span class="select-btn">
						  	<i class="zmdi zmdi-chevron-down"></i>
						</span>
					</div>
					<div class="form-row">
						<select id="city" name="cities">
						  <script>fillCities();</script>
						</select>
						<span class="select-btn">
						  	<i class="zmdi zmdi-chevron-down"></i>
						</span>
					</div>
					<input type="file" id="files" name="files[]" multiple />
					<div class="form-checkbox">
						<label class="container"><p>Primanje upozorenja unutar apllikacije</p>
						  	<input type="checkbox" name="checkboxApp">
						  	<span class="checkmark"></span>
						</label>
						
					</div>
					<div class="form-checkbox">
					<label class="container"><p>Primanje upozorenja preko mejla</p>
						  	<input type="checkbox" name="checkboxMail">
						  	<span class="checkmark"></span>
						</label>
						</div>
					<div class="form-row-last">
						<input type="submit" name="register" class="register" value="Registruj se">
					</div>
					<div class="form-row">
					<p>Već imate kreiran nalog?<a href="?action=login" style="text-decoration:none;color:#ffffff" > Prijavite se</a></p>
					</div>
				</div>
			</form>
		</div>
	</div>
	
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>