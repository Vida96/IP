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

function focusCommentBox(){
	 document.getElementById("commentBox").focus();
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
function readImageForComment() {

	var num = 0;
	 if (window.File && window.FileList && window.FileReader) {
		 var files = event.target.files; //FileList object
	        var output = document.getElementsByClassName("preview-images-zone")[1];
        
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
             focusCommentBox();
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

function shareDanger(){
	
	var description = document.getElementById('dangerDetails').value;
	var location = document.getElementById('searchInput').value;	 
	var checkboxes = document.getElementsByName('cbCategory');
	var checkboxesChecked = [];
	for (var i=0; i< checkboxes.length; i++) {
	   if (checkboxes[i].checked) {
	    	 checkboxesChecked.push(checkboxes[i].id);
	   }
	}
	
    var url = description;
    var regExp = /(?:youtube\.com\/(?:[^\/]+\/.+\/|(?:v|e(?:mbed)?)\/|.*[?&]v=)|youtu\.be\/)([^"&?\/ ]{11})/i, match = String(url).match(regExp);
    
    if (match) {
    	  video = match;
    }

    var emergencyCb = document.getElementById('emergencyCb');
    
	let object = {
		description: description,
		location: location,
		checkboxesChecked: checkboxesChecked,
		images: images,
	    video: video,
	    isEmergency: emergencyCb.checked
	}
 
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		
		if((request.readyState ==4) && (request.status==200))
		{
			//izbrisati sve podatke 
			images = [];
			video = null;
			document.getElementById('dangerDetails').value = "";
			document.getElementById('searchInput').value = "";	 
			checkboxes = document.getElementsByName('cbCategory');
			for (var i=0; i< checkboxes.length; i++) {
				checkboxes[i].checked = false;	
			}
		    var previewImage = document.getElementsByClassName("preview-images-zone")[0];
	        previewImage.innerHTML = "";
	        previewImage.style.display = "none";
		}
	}
	
	request.open("POST","Post");
	request.setRequestHeader("Content-Type","application/json;charset=UTF-8");
	request.send(JSON.stringify(object));

}

var weatherNum = 0;
function searchWeatherForCities(){
	
	/*var citiesForWeather = [];
	while(citiesForWeather.length < 3){
	    var r = Math.floor(Math.random() * 100) + 1;
	    var city = cities[r];
	    if(citiesForWeather.indexOf(city) === -1) 
	    	citiesForWeather.push(r);
	}
	
	citiesForWeather.forEach(function(city) {
		searchWeather(city)
		});
		*/
	searchWeather("Banja Luka");
	searchWeather("Doboj");
	searchWeather("Mrkonjic Grad");
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
	    });
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
	
	function onEnterPress() {
	    var key = window.event.keyCode;
 
	    if (key === 13) {
	    	var text = document.getElementById("commentBox").value;
	    	addComment(text, imageComment);

	        var previewImage = document.getElementsByClassName("preview-images-zone")[1];
	        previewImage.innerHTML = "";
	        previewImage.style.display = "none";
	    	document.getElementById("commentBox").value = "";
	    	document.getElementById("commentBox").blur();
	    	addComment(text, imageComment);
	    	return false;
	    }
	    else {
	        return true;
	    }
	}
	
	function addComment(text, imageComment){
	 
		
	}	
	
	
	function getFeed()
	{
 
		var feedURL = "https://europa.eu/newsroom/calendar.xml_en?field_nr_events_by_topic_tid=151";
		$.ajax({
			  type: 'GET',
			  url: "https://api.rss2json.com/v1/api.json?rss_url=" + feedURL,
			  dataType: 'jsonp',
			  success: function(result) {
			    console.log(result.items);
			  }
			});
	}
	
	 