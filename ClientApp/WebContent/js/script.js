//API_KEY = bed3202df489f8703eed5235290df215
var countries = [];
var regions = [];

function loadCountries() {
	var elem = document.getElementById("country");
	countries.forEach(country =>{
		var option = document.createElement("option");
		option.text = country.name;
		option.value = country.alpha2Code;
		elem.add(option); 
	});
}

function loadRegions(){
	var elem = document.getElementById("region");

	regions.forEach(region =>{
		var option = document.createElement("option");
		option.text = region.region;
		option.value = region.region;
		elem.add(option); 
	});
}

function fillRegions(){
	var sel = document.getElementById("country");
	var alpha2Code = sel.options[sel.selectedIndex].value;
	
	var req = new XMLHttpRequest();
	req.onreadystatechange = function() {
		//alert(request.responseText)
		//var result = JSON.parse(request.responseText);
	//	alert(req.responseText)
		if ((req.readyState == 4) && (req.status == 200)) {	
			var result = JSON.parse(req.responseText);
			countries = result;
		}
	};
	req.open("GET", "http://battuta.medunes.net/api/country/all/?key=bed3202df489f8703eed5235290df215");
	req.send(null);
	
//	request.open("GET", "http://battuta.medunes.net/api/region/BY/all/?key=bed3202df489f8703eed5235290df215", true);
};

function fillCountries(){
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status == 200)) {	
			var result = JSON.parse(request.responseText);
			countries = result;
			loadCountries();
			fillRegions();
		}
	};
 //  request.open("GET", "http://battuta.medunes.net/api/country/all/?key=bed3202df489f8703eed5235290df215");
request.open("GET", "https://restcountries.eu/rest/v2/region/europe", true);
	request.send(null);
};

function fillCities(){
	  var dynamicSelect = document.getElementById("city");
 
      ["1", "2", "3",].forEach(function(item){ 
      {
              var newOption = document.createElement("option");
              newOption.text = item.toString();//item.whateverProperty

              dynamicSelect.add(newOption);
 
      }});
};

function InvalidMsg(textbox) {

    if(textbox.validity.patternMismatch){
       textbox.setCustomValidity('please enter 10 numeric value.');
   }    
   else {
       textbox.setCustomValidity('');
   }
   return true;
}