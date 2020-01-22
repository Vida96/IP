function validateFields(){
	let firstName = document.getElementById("firstName").value;
	var condition = true;
	if(firstName == null || firstName == "")
	{
		document.getElementById("firstNameLabel").innerHTML = "* Polje ne moze biti prazno";
		condition = false; 
	}
	else
		document.getElementById("firstNameLabel").innerHTML = "";
	
	let lastName = document.getElementById("lastName").value;	
	if(lastName == null || lastName == "")
	{
		document.getElementById("lastNameLabel").innerHTML = "* Polje ne smije biti prazno";
		condition = false;
	}
	else
		document.getElementById("lastNameLabel").innerHTML = "";
		
	let username = document.getElementById("username").value;
	 
	if(/\s/.test(username)) {
 		document.getElementById("usernameLabel").innerHTML = "* Korisničko ime ne smije imati razmak";
 		condition = false;
	}
	else if(username == null || username == "")
	{
		document.getElementById("usernameLabel").innerHTML = "* Polje ne smije biti prazno";
		condition = false; 
	}else
		document.getElementById("usernameLabel").innerHTML = "";
 
	let password = document.getElementById("password").value; 
	if(password == null || password == "")
	{
		document.getElementById("passwordLabel").innerHTML = "* Polje ne smije biti prazno";
		condition = false; 
	}else
		document.getElementById("passwordLabel").innerHTML = "";

	let confirmedPassword = document.getElementById("confirmedPassword").value;
	if(password !== confirmedPassword) {
		document.getElementById("confirmedPasswordLabel").innerHTML = "* Lozinke se ne poklapaju";
		condition = false;
	}else
		document.getElementById("confirmedPasswordLabel").innerHTML = "";
	
	let mail = document.getElementById("mail").value;
	let regex = /([a-zA-Z0-9]+)([\.{1}])?([a-zA-Z0-9]+)\@gmail([\.])com/;
	if(!regex.test(String(mail).toLowerCase())) {
		document.getElementById("mailLabel").innerHTML = "* Mail nije validan";
		condition = false;
	}
	else if(password == null || password == "")
	{
		document.getElementById("mailLabel").innerHTML = "* Polje ne smije biti prazno";
		condition = false;
	}	
	else
		document.getElementById("mailLabel").innerHTML = ""; 
 
	if(condition == false)
		return false;
	
	let object = {
			firstName: firstName,
			lastName: lastName,
			username: username,
			password: password,
			mail: mail,
			action: "registration"
	}
 
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		
		if((request.readyState ==4) && (request.status==200))
		{
			if(this.responseText.includes("USERNAME_ERROR")) {
				document.getElementById("usernameLabel").innerHTML = "* Korisničko ime je već zauzeto";
				condition = false;
			}
			if(this.responseText.includes("MAIL_ERROR")) {
				document.getElementById("mailLabel").innerHTML = "* Mail je već zauzet";
				condition = false;
			}
			if(this.responseText.includes("USERNAME_ON_HOLD")) {
				document.getElementById("usernameLabel").innerHTML = "* Korisnik sa ovim korisnickim imenom ceka na odobrenje";
				condition = false;
			}
			if(this.responseText.includes("MAIL_ON_HOLD")) {
				document.getElementById("mailLabel").innerHTML = "* Korisnik sa ovim mailom ceka na odobrenje";
				condition = false;
			}
			if(condition == false)
				return false;
			else
				window.location.replace("Profile?action=updateProfile");
	 
		}
	}
	
	request.open("POST","Registration");
	request.setRequestHeader("Content-Type","application/json;charset=UTF-8");
	request.send(JSON.stringify(object));
}
