function fillArea(){
  
  var checkboxes = document.getElementsByName('cbCategory');
  var checkboxesChecked = [];
  let counter = 0;
  for (var i=0; i<checkboxes.length; i++) {
     if (checkboxes[i].checked) {
    	 counter++;
     }
  }
  if(counter == 1)
	  document.getElementById("txtArea").innerHTML = counter + " kategorija";
  else if(counter == 0)
	  document.getElementById("txtArea").innerHTML = "";
  else
	  document.getElementById("txtArea").innerHTML = counter + " kategorije";
}

function initializeComponents()
{
	var searchInput = 'searchInput';
	var autocomplete;
	autocomplete = new google.maps.places.Autocomplete((document.getElementById(searchInput)), {
	    types: ['geocode'],
	});
  document.addEventListener("click", toggleDropdown);
  getFeed();
}

function focusCommentBox(id){
	 document.getElementById("commentBox"+id).focus();
}

function toggleDropdown(event) {
	var checkboxes = document.getElementById("checkboxes");
     
    if (event.target.classList.contains('overSelect'))
    {
    	showCheckboxes();
    }else if(!event.target.classList.contains('checkboxes')){
  	 	if(checkboxes.style.display == "block")
  	 		showCheckboxes();
    }
}

var expanded = false;

function showCheckboxes() {
  var checkboxes = document.getElementById("checkboxes");
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
}

var num = 0;
var images = [];
var video;
function readImage() {
       
	 if (window.File && window.FileList && window.FileReader) {
		 var files = event.target.files; //FileList object
	        var output = document.getElementsByClassName("preview-images-zone")[0];

        for (let i = 0; i < files.length; i++) {
            var file = files[i];
            if (!file.type.match('image') && !file.type.match('video')) continue;
            var picReader = new FileReader();

            picReader.readAsDataURL(file);            
            picReader.addEventListener('load', function (event) {
                var picFile = event.target;
                var div = document.createElement("div");
    var html;           
if(file.type.match('image')){
	images.push(picFile.result);
html = '<div style="float: left;" class="preview-image preview-show-' + num + '">' +
    '<div class="image-cancel" data-no="' + num + '">x</div>' +
    '<div class="image-zone"><img style="width:100px;height:100px"  id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
    '</div>';
}
else
{
	 var fileURL = URL.createObjectURL(file);
	 video = fileURL;
	html = '<div style="float: left;" class="preview-image preview-show-' + num + '">' +
    '<div class="image-cancel" data-no="' + num + '">x</div>' +
    '<div class="image-zone"><video style="width:100px;height:100px" controls autoplay src="'+ fileURL + '"></video>' +
    '</div>';

	}
             div.innerHTML = html;
             num = num + 1;
             
             output.append(div);
            });

        }
        document.getElementById("pro-image").innerHTML= '';
	 }
}

function getImagePath(){
     var output =  document.getElementById('profileImage');
     var file = files[0];
   // var picReader = new FileReader();
     picReader.readAsDataURL(file);            
      picReader.addEventListener('load', function (event) {
          var picFile = event.target; 
           var picFile = event.target;
           output.src = picFile.result;
     });
}

var imageComment;
function readImageForComment(id) {
	
	var num = 0;
	 if (window.File && window.FileList && window.FileReader) {
		 var files = event.target.files; //FileList object
		    var output = document.getElementsByClassName("preview-images-zone"+id)[0];
        
        for (let i = 0; i < files.length; i++) {
         
        	var file = files[i];
            if (!file.type.match('image')) continue;
            output.style.display = "block";
            var picReader = new FileReader();
            picReader.readAsDataURL(file);            
            picReader.addEventListener('load', function (event) {
            if(num != 1){
            var picFile = event.target;
            var div = document.createElement("div");
            imageComment = picFile.result;
            var html =  '<div class="preview-image preview-show-' + num + '">' +
                            '<div class="image-cancel" data-no="' + num + '">x</div>' +
                            '<div class="image-zone"><img  width="150" height="100" id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
                            '</div>';
             div.innerHTML = html;
             num = num + 1;
             
             output.append(div);
             focusCommentBox(id);
            }});

        }
        document.getElementById("pro-image").innerHTML= '';
	 }
}

function focusShareOnFacebook(width, height){
	var desc=encodeURIComponent("Hey, I scored "+ 2 +" in a quiz"); 
	window.open("https://www.facebook.com/sharer.php?u=http://192.168.1.2&caption='Top 3 reasons why you should care about your finance&description=What happens when you don't take care of your finances?&message:sdadsaasdas"); 
}

function focusShareOnTwitter(){
	var url = "http://192.168.1.2/ClientApp/Home";
	var text = "Replace this with your text";
	window.open('http://twitter.com/share?url='+encodeURIComponent(url)+'&text='+encodeURIComponent(text), '', 'left=0,top=0,width=550,height=450,personalbar=0,toolbar=0,scrollbars=0,resizable=0');
}

function shareDanger(id, username, fullName, userPhoto){
	
	var description = document.getElementById('dangerDetails').value;
	var location = document.getElementById('searchInput').value;	 
	var checkboxes = document.getElementsByName('cbCategory');
	var checkboxesChecked = [];
	var cateoriesNames = [];
	for (var i=0; i< checkboxes.length; i++) {
	   if (checkboxes[i].checked) {
	    	 checkboxesChecked.push(checkboxes[i].id);
	    	 cateoriesNames.push(checkboxes[i].name);
	   }
	}
	
	var url = description;
    var regExp = /(?:youtube\.com\/(?:[^\/]+\/.+\/|(?:v|e(?:mbed)?)\/|.*[?&]v=)|youtu\.be\/)([^"&?\/ ]{11})/i, match = String(url).match(regExp);
    var link;
    
    if (match) {
    	  video = match;
    	  description = description.replace(video,'');
    }else{
    	var str = description;
    	var urlRE= new RegExp("([a-zA-Z0-9]+://)?([a-zA-Z0-9_]+:[a-zA-Z0-9_]+@)?([a-zA-Z0-9.-]+\\.[A-Za-z]{2,4})(:[0-9]+)?([^ ])+");
    	if(str.match(urlRE) != null)
    	{
    		link = str.match(urlRE)[0];
    		description = description.replace(link,'');
    		}
    }

    var emergencyCb = document.getElementById('emergencyCb');
    
    if((video == null) && (images.length == 0) && (description == "") && (location == ""))
    	return false;
    
	let object = {
		description: description,
		location: location,
		checkboxesChecked: checkboxesChecked,
		images: images,
	    video: video,
	    isEmergency: emergencyCb.checked,
	    link: link
	}
 
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		
		if((request.readyState ==4) && (request.status==200))
		{
			
			//appendDiv
			if(emergencyCb.checked == 0)
				displayDanger(username, fullName, userPhoto, description, location, images, video, link, id);
			
			//izbrisati sve podatke 
			images = [];
			video = null;
			document.getElementById('dangerDetails').value = "";
			document.getElementById('searchInput').value = "";	 
			checkboxes = document.getElementsByName('cbCategory');
			for (var i=0; i< checkboxes.length; i++) {
				checkboxes[i].checked = false;	
			}
			document.getElementById('emergencyCb').checked = false;	
		    var previewImage = document.getElementsByClassName("preview-images-zone")[0];
	        previewImage.innerHTML = "";
	        document.getElementById("txtArea").innerHTML = "";
		}
	}
	
	request.open("POST","Post");
	request.setRequestHeader("Content-Type","application/json;charset=UTF-8");
	request.send(JSON.stringify(object));

}

function displayDanger(username, fullName, userPhoto, description, location, images, video, link, id){
    var div = document.createElement("div");
    var output = document.getElementsByClassName("postsZone")[0];  
    var imagesHtml ="";
    images.forEach(function (image, index) {
    	if(index != 0 && index % 4 == 0)
    		imagesHtml += ' <img width="200" height="200" style="padding-bottom: 5px;" class="center-block img-responsive" src=' + image + '/><br>';
    	else
    		imagesHtml += ' <img width="200" height="200" style="padding-bottom: 5px;" class="center-block img-responsive" src=' + image + '/> ';
    	});
    
    var proimage = "pro-image2" + id;
    var commentBox = "commentBox" + id;
	var previewImagesZone = "preview-images-zone" + id;
    
    var html =  '<br><div class="card gedf-card"><div class="card-header"><div class="d-flex justify-content-between align-items-center"><div class="d-flex justify-content-between align-items-center">' +
    '<div class="mr-2"><img class="rounded-circle" width="45" src=' + userPhoto + ' alt=""></div><div class="ml-2"><div class="h5 m-0">@' + username + '</div><div class="h7 text-muted">' + fullName + '</div>' +
    '</div></div><div><div class="dropdown"><button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
    '<i class="fa fa-ellipsis-h"></i></button><div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1"><div class="h6 dropdown-header">Configuration</div>' +
    '<a class="dropdown-item" href="#">Save</a><a class="dropdown-item" href="#">Hide</a><a class="dropdown-item" href="#">Report</a></div></div></div></div></div>' +
    '<div class="card-body"><div class="well"> <div class="row"><div class="col-md-12"><div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i> Prije 0 minuta</div>' +
    '<h1>TITULO LARGO DE UNA INVESTIGACION cualquiera</h1>' +	imagesHtml
     +
    '<p>' + description + '<br><br></p></div></div></div></div>' +
    '<div class="card-footer"><button type="button" onClick="focusCommentBox(' + id + ')" class="btn btn-link"><i class="fa fa-comment"></i>Komentariši</button>' +
    '<button type="button" onClick="focusShareOnFacebook(500, 300)" class="btn btn-link"><i class="fa fa-facebook-square" aria-hidden="true"></i>Podijeli na fb</button>' +
    '<button type="button" onClick="focusShareOnTwitter()" class="btn btn-link"><i class="fa fa-twitter-square" aria-hidden="true"></i>Podijeli na twitter</button>' +
    '</div></div>   <div class="commentsZone'+id+'"><div class="card gedf-card"><div class="panel panel-info"><div class="panel-body"><textarea id=' + commentBox +' style="width:100%" placeholder="Napišite svoj komentar ovdje" class="pb-cmnt-textarea" onkeypress="onEnterPress(' + id + ', ' + username + ', ' + userPhoto + '\ ); ") ></textarea>' +
    '<div style="float:right; display: none;" class=' + previewImagesZone + '></div></div></div></div></div><fieldset style="float:right" class="form-group"><a  href="javascript:void(0)" style="float:right;"onclick="$(#pro-image2' + id +').click()"><span class="fa fa-picture-o fa-lg"></span>Dodaj sliku</a>' +
    '<input type="file" id='+ proimage +' name=' + proimage + ' style="display: none;" class="form-control" onChange="readImageForComment(' + id + ')" ></fieldset><br><br>';

	div.innerHTML = html + output.innerHTML;
	output.innerHTML = "";
	output.append(div);	
}

var weatherNum = 0;
var countries = [];
var regions = [];
var citiesForWeather = [];
var cities = [];

function JsonpHttpsRequest(url, callback, isLastRegion) {
	  var e = document.createElement('script');
	    e.src = url;
	    document.body.appendChild(e);
	  
	window[callback] = (data) => {
    	data.forEach(function(city) {
    		citiesForWeather.push(city);	
    	}); 
    	
    	if(isLastRegion && citiesForWeather.length > 0 && cities.length < 3){
    		while(cities.length < 3){
    		    var r = Math.floor(Math.random() * citiesForWeather.length);
    		    
    		    var city = citiesForWeather[r].city;
    		    if(cities.indexOf(city) === -1) 
    		    	cities.push(city);
    		}
    		cities.forEach(function(city) {
    			searchWeather(city)
    			});
    		cities = [];
    		citiesForWeather = [];
    	}
    }
}

function JsonpHttpRequest(url, callback, alpha2Code) {
    var e = document.createElement('script');
    e.src = url;
    document.body.appendChild(e);
    window[callback] = (data) => {
    regions = data;  
    var isLastRegion = false;
    regions.forEach(function(region, idx, array){
    	   if (idx === regions.length - 1){ 
    	 		isLastRegion = true;
    	   }
    	   	JsonpHttpsRequest('http://battuta.medunes.net/api/city/' + alpha2Code + '/search/?region='+ region.region + '&key=ceab192fc6bd24fbec5aa769affe791c&callback=cb', "cb", isLastRegion); 
    	    
    	});
    }
}

function fillCountries(country){
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status == 200)) {	
			var result = JSON.parse(request.responseText);
			countries = result;
			var userCountry = countries.filter(function (c) {
	    	    return c.name === country;
	    	})[0];
			JsonpHttpRequest('http://battuta.medunes.net/api/region/' + userCountry.alpha2Code + '/all/?key=ceab192fc6bd24fbec5aa769affe791c&callback=cb', "cb", userCountry.alpha2Code);
	};
	}
		request.open("GET", "https://restcountries.eu/rest/v2/region/europe", true);
		request.send(null);		
}

function searchWeatherForCities(country){
	fillCountries(country);
	/*
	searchWeather("Banja Luka");
	searchWeather("Doboj");
	searchWeather("Mrkonjic Grad");
	*/
}

function searchWeather(cityName) {
	let appId = 'c184fa574eb152bfe0cab958d44d62de';
	let units = 'metric';//'imperial'; 
	let searchMethod = 'q';
	fetch(`http://api.openweathermap.org/data/2.5/weather?q=` + cityName + `&APPID=c184fa574eb152bfe0cab958d44d62de&units=metric`)
	        .then((result) => {
	            return result.json();
	        }).then((res) => {
	            init(res);
	    }).catch(function() {
	        console.log("error");
	    });;
	}

   
	function init(resultFromServer) {
		
	    let weatherDescriptionHeader = document.getElementById('weatherDescriptionHeader'+weatherNum);
	    let temperatureElement = document.getElementById('temperature'+weatherNum);
	    let humidityElement = document.getElementById('humidity'+weatherNum);
	    let windSpeedElement = document.getElementById('windSpeed'+weatherNum);
	    let cityHeader = document.getElementById('cityHeader'+weatherNum);

	    let weatherIcon = document.getElementById('documentIconImg'+weatherNum);
	    weatherIcon.src = 'http://openweathermap.org/img/w/' + resultFromServer.weather[0].icon + '.png';

	    let resultDescription = resultFromServer.weather[0].description;
	    weatherDescriptionHeader.innerText = resultDescription.charAt(0).toUpperCase() + resultDescription.slice(1);
	    temperatureElement.innerHTML = Math.floor(resultFromServer.main.temp) + '&#176;';
	    windSpeedElement.innerHTML = 'Winds at  ' + Math.floor(resultFromServer.wind.speed) + ' m/s';
	    cityHeader.innerHTML = resultFromServer.name;
	    humidityElement.innerHTML = 'Humidity levels at ' + resultFromServer.main.humidity +  '%';

	    setPositionForWeatherInfo();
	}

	function setPositionForWeatherInfo() {
	    let weatherContainer = document.getElementById('weatherContainer'+weatherNum);
	    let weatherContainerHeight = weatherContainer.clientHeight;
	    let weatherContainerWidth = weatherContainer.clientWidth;

	    weatherContainer.style.left = `calc(50% - ${weatherContainerWidth/2}px)`;
	    weatherContainer.style.top = `calc(50% - ${weatherContainerHeight/1.3}px)`;
	    weatherContainer.style.visibility = 'visible';
	    weatherNum = weatherNum + 1;
	}
	
	function onEnterPress(id, fullName, userPhoto) {
	    var key = window.event.keyCode;
 
	    if (key === 13) {
	    	var text = document.getElementById("commentBox"+id).value;
	    	if(text != '' || imageComment != null){
	    	addComment(text, imageComment, id, fullName, userPhoto);

	        var previewImage = document.getElementsByClassName("preview-images-zone"+id)[0];
	        previewImage.innerHTML = "";
	        previewImage.style.display = "none";
	    	document.getElementById("commentBox"+id).value = "";
	    	document.getElementById("commentBox"+id).blur();
	    	imageComment = null;
	    	return false;
	    }}
	    else {
	        return true;
	    }
	}
	
	function addComment(text, imageComment, id, fullName, userPhoto){
		 
        var div = document.createElement("div");
        var output = document.getElementsByClassName("commentsZone"+id)[0];  
        alert("commentsZone"+id)
        var html;
        var datetime = new Date().toLocaleString();
        
        if(imageComment != null){
        	html =  '<div class="row" style="margin:5px"><div class="col-sm-2 text-center"><img class="rounded-circle" width="70" src=' + userPhoto + ' alt="">' +
			'</div><div class="col-sm-10"><h4>' + fullName  + ' </h4><div class="text-muted h7 mb-2"><i class="fa fa-clock-o"></i> Prije 0 minuta</div><p>' + text + '</p>' +
			'<img height="170" width="170" src=' + imageComment + '><br></div></div><hr>';
        }else{
        	html =  '<div class="row" style="margin:5px"><div class="col-sm-2 text-center"><img class="rounded-circle" width="70" src=' + userPhoto + ' alt="">' +
			'</div><div class="col-sm-10"><h4>' + fullName  + '</h4><div class="text-muted h7 mb-2"><i class="fa fa-clock-o"></i> Prije 0 minuta</div><p>' + text + '</p>' +
			'<br></div></div><hr>';
        }
        
		div.innerHTML = html + output.innerHTML;
		output.innerHTML = "";
		output.append(div);
		
		//sendComment //poslati komentar POST zahtjevom
	}	
	
	function getFeed()
	{
 
		var feedURL = "https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151";
		$.ajax({
			  type: 'GET',
			  url: "https://api.rss2json.com/v1/api.json?rss_url=" + feedURL,
			  dataType: 'jsonp',
			  success: function(result) {
			//    console.log(result.items);
			  }
			});
	}
	
	 