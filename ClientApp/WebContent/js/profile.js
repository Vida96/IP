function validateFields(){
	let firstName = document.getElementById("firstName").value;
	
	if(firstName == null || firstName == "")
	{
		document.getElementById("firstNameLabel").innerHTML = "* Polje ne moze biti prazno";
		return false; 
	}	
	document.getElementById("firstNameLabel").innerHTML = "";
	
	let lastName = document.getElementById("lastName").value;	
	if(lastName == null || lastName == "")
	{
		document.getElementById("lastNameLabel").innerHTML = "* Polje ne smije biti prazno";
		return false; 
	}	
	document.getElementById("lastNameLabel").innerHTML = "";
		
	let username = document.getElementById("username").value;
	 
	if(/\s/.test(username)) {
 		document.getElementById("usernameLabel").innerHTML = "* Korisničko ime ne smije imati razmak";
		return false; 
	}
	else if(username == null || username == "")
	{
		document.getElementById("usernameLabel").innerHTML = "* Polje ne smije biti prazno";
		return false; 
	}	
	document.getElementById("usernameLabel").innerHTML = "";
 
	let password = document.getElementById("password").value; 
	if(password == null || password == "")
	{
		document.getElementById("passwordLabel").innerHTML = "* Polje ne smije biti prazno";
		return false; 
	}	
	document.getElementById("passwordLabel").innerHTML = "";

	let confirmedPassword = document.getElementById("confirmedPassword").value;
	if(password !== confirmedPassword) {
		console.log(12);
		document.getElementById("confirmedPasswordLabel").innerHTML = "Lozinke se ne poklapaju";
		return false;
		
	}
	
	let mail = document.getElementById("email").value;
	let regex = /[^@]+@[^@]+.[a-zA-Z]{2,6}/;
	if(!regex.test(String(mail).toLowerCase())) {
		document.getElementById("mailLabel").innerHTML = "Mail nije validan";
		return false;
	}
	else if(password == null || password == "")
	{
		document.getElementById("mailLabel").innerHTML = "* Polje ne smije biti prazno";
		return false; 
	}	
	else
		document.getElementById("mailLabel").innerHTML = ""; 
 
	var countryOption = document.getElementById("country");
	var country  = countryOption.options[countryOption.selectedIndex].text;
	 
	var regionOption = document.getElementById("region");
	if(regionOption.options.length != 0)
		var region  = regionOption.options[regionOption.selectedIndex].value;
	
	var cityOption = document.getElementById("city");
	if(cityOption.options.length != 0)
	var city  = cityOption.options[cityOption.selectedIndex].value;
	var notificationOnMail;
	var notificationInApp;
	if(document.getElementById("notifications-blog").checked){
	var radioButtons = document.getElementsByName("notifications");
	notificationOnMail = radioButtons[0].checked;
	notificationInApp = radioButtons[1].checked;
	}else{
		notificationOnMail = 0;
		notificationInApp = 0;
	}
	let object = {
			firstName: firstName,
			lastName: lastName,
			username: username,
			password: password,
			mail: mail,
			action: "updateProfile",
			country: country,
			region: region,
			city: city,
			photo: null,
			notificationOnMail: notificationOnMail,
			notificationInApp: notificationInApp,
	}
 
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		
		if((request.readyState ==4) && (request.status==200))
		{
			console.log(this.responseText.trim());
			if(this.responseText.trim() == "USERNAME_ERROR") {
				document.getElementById("usernameLabel").innerHTML = "Lozinka mora imati vise od 7 karaktera";
				return false;
			}
			else
				window.location.replace("Login?action=");
	 
		}
	}
	
	request.open("POST","Profile");
	request.setRequestHeader("Content-Type","application/json;charset=UTF-8");
	request.send(JSON.stringify(object));
}
