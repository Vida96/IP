function validateFields(){
	
	let username = document.getElementById("username").value;
	 
	if(/\s/.test(username)) {
 		document.getElementById("usernameLabel").innerHTML = "* Korisničko ime neispravno";
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

	 
	let object = {
			username: username,
			password: password,
			action: "login"
	}
 
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		
		if((request.readyState ==4) && (request.status==200))
		{
			if(this.responseText.trim() == "USERNAME_ERROR") {
				document.getElementById("usernameLabel").innerHTML = "Neispravno korisnicko ime ili lozinka";
				return false;
			}
			else
				window.location.replace("Home");
		}
	}
	
	request.open("POST","Login");
	request.setRequestHeader("Content-Type","application/json;charset=UTF-8");
	request.send(JSON.stringify(object));
}
