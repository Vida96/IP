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
	  document.getElementById("txtArea").innerHTML = "Odabir kategorije";
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
            if (!file.type.match('image')) continue;
            var picReader = new FileReader();

            picReader.readAsDataURL(file);            
            picReader.addEventListener('load', function (event) {
                var picFile = event.target;
                var div = document.createElement("div");
               
             var html =  '<div class="preview-image preview-show-' + num + '">' +
                            '<div class="image-cancel" data-no="' + num + '">x</div>' +
                            '<div class="image-zone"><img  width="150" height="100" id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
                            '<div class="tools-edit-image"><a href="javascript:void(0)" data-no="' + num + '" class="btn btn-light btn-edit-image">edit</a></div>' +
                            '</div>';
             div.innerHTML = html;
             num = num + 1;
             
             output.append(div);
            });

        }
        document.getElementById("pro-image").innerHTML= '';
	 }
}
