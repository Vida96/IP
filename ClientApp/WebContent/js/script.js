//API_KEY = bed3202df489f8703eed5235290df215
var countries = [];
var regions = [];
var cities = [];

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
	elem.innerHTML = "";
	regions.forEach(region =>{		
		
		var option = document.createElement("option");		
		option.text = region.region;
		option.value = region.region;
		elem.add(option); 
	});
	if(elem.options.length == 0)
		document.getElementById("city").innerHTML = "";
	else
		fillCities();
}

function loadCities(){
	var elem = document.getElementById("city");
	elem.innerHTML = "";
	cities.forEach(city =>{		
		var option = document.createElement("option");		
		option.text = city.city;
		option.value = city.city;
		elem.add(option); 
	});
}

function JsonpHttpRequest(url, callback) {
    var e = document.createElement('script');
    e.src = url;
    document.body.appendChild(e);
    window[callback] = (data) => {
    regions = data;   
    loadRegions();
    }
}

function JsonpHttpsRequest(url, callback) {
    var e = document.createElement('script');
    e.src = url;
    document.body.appendChild(e);
    window[callback] = (data) => {
    cities = data;   
    loadCities();
    }
}

function fillRegions(){
	var selectedCountry = document.getElementById("country");
	var alpha2Code = selectedCountry.options[selectedCountry.selectedIndex].value; 
	JsonpHttpRequest('http://battuta.medunes.net/api/region/' + alpha2Code + '/all/?key=6826321a61ebdc72f20551df7f7d374a&callback=cb', "cb");	
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
	  var selectedCountry = document.getElementById("country");
	 var alpha2Code = selectedCountry.options[selectedCountry.selectedIndex].value; 

	  var selectedRegion = document.getElementById("region");
	 var region = selectedRegion.options[selectedRegion.selectedIndex].value; 
	  JsonpHttpsRequest('http://battuta.medunes.net/api/city/' + alpha2Code + '/search/?region='+ region + '&key=6826321a61ebdc72f20551df7f7d374a&callback=cb', "cb");

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

function toggle_visibility() 
{
	var e = document.getElementById("radioButtons");
	
    if ( e.style.display == 'block' )
        e.style.display = 'none';
    else
        e.style.display = 'block';
}

function handleImageChange()
{
	alert(2)
    var filesToUpload = document.getElementById('selectedImage').files;
    var file = filesToUpload[0];

    // Create an image
    var img = document.createElement("img");
    // Create a file reader
    var reader = new FileReader();
    // Set the image once loaded into file reader
    reader.onload = function(e)
    {
        img.src = e.target.result;

        var canvas = document.createElement("canvas");
        //var canvas = $("<canvas>", {"id":"testing"})[0];
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0);

        var MAX_WIDTH = 100;
        var MAX_HEIGHT = 100;
        var width = img.width;
        var height = img.height;

        if (width > height) {
          if (width > MAX_WIDTH) {
            height *= MAX_WIDTH / width;
            width = MAX_WIDTH;
          }
        } else {
          if (height > MAX_HEIGHT) {
            width *= MAX_HEIGHT / height;
            height = MAX_HEIGHT;
          }
        }
        canvas.width = width;
        canvas.height = height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, width, height);

        var dataurl = canvas.toDataURL("image/jpg");
        document.getElementById('profileImage').src = dataurl;     
    }
    // Load files into file reader
    reader.readAsDataURL(file);


    // Post the data
    /*
    var fd = new FormData();
    fd.append("name", "some_filename.jpg");
    fd.append("image", dataurl);
    fd.append("info", "lah_de_dah");
    */
}