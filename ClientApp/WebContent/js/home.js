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
html = '<div style="float: left;" class="preview-image preview-show-' + num + '">' +
    '<div class="image-cancel" data-no="' + num + '">x</div>' +
    '<div class="image-zone"><img style="width:100px;height:100px"  id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
    '</div>';
}
else
{
	 var fileURL = URL.createObjectURL(file)
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

var num = 0;
function readImage2() {
       
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
            var picFile = event.target;
            var div = document.createElement("div");
               
             var html =  '<div class="preview-image preview-show-' + num + '">' +
                            '<div class="image-cancel" data-no="' + num + '">x</div>' +
                            '<div class="image-zone"><img  width="150" height="100" id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
                            '</div>';
             div.innerHTML = html;
             num = num + 1;
             
             output.append(div);
            });

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
	
	var desription = document.getElementById('dangerDetails');
	var location = document.getElementById('searchInput');	
	 
	var checkboxes = document.getElementsByName('cbCategory');
	var checkboxesChecked = [];
	for (var i=0; i< checkboxes.length; i++) {
	   if (checkboxes[i].checked) {
	    	 checkboxesChecked.push(checkboxes[i].id);
	   }
	}
	
	let object = {
		desription: desription,
		location: location,
		checkboxesChecked: checkboxesChecked,
		images: null,
	    video: null,
	}
 
}