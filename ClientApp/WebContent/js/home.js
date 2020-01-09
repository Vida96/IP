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

var num = 4;
function readImage() {
       
	 if (window.File && window.FileList && window.FileReader) {
	     alert(2)   
		 var files = event.target.files; //FileList object
	        var output = document.getElementsByClassName("preview-images-zone")[0];
        
        for (let i = 0; i < files.length; i++) {
            var file = files[i];
            if (!file.type.match('image')) continue;
            alert(i)
            var picReader = new FileReader();

            picReader.readAsDataURL(file);            
            picReader.addEventListener('load', function (event) {
                var picFile = event.target;
              var html =  '<div class="preview-image preview-show-' + num + '">' +
                            '<div class="image-cancel" data-no="' + num + '">x</div>' +
                            '<div class="image-zone"><img id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
                            '<div class="tools-edit-image"><a href="javascript:void(0)" data-no="' + num + '" class="btn btn-light btn-edit-image">edit</a></div>' +
                            '</div>';
//                var html = '<div class="preview-image preview-show-1"><div class="image-cancel" data-no="1">x</div><div class="image-zone"><img id="pro-img-1" src="https://img.purch.com/w/660/aHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzA5Ny85NTkvb3JpZ2luYWwvc2h1dHRlcnN0b2NrXzYzOTcxNjY1LmpwZw=="></div><div class="tools-edit-image"><a href="javascript:void(0)" data-no="1" class="btn btn-light btn-edit-image">edit</a></div></div>';
                output.append(html);
                num = num + 1;
            });

        }
        document.getElementById("pro-image").innerHTML= '';
	 }
}
