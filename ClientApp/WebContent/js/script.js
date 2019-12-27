function fillCountries(){
	  var dynamicSelect = document.getElementById("country");
 
      ["1", "2", "3",].forEach(function(item){ 
      {
              var newOption = document.createElement("option");
              newOption.text = item.toString();//item.whateverProperty

              dynamicSelect.add(newOption);
 
      }});
};

function fillRegions(){
	  var dynamicSelect = document.getElementById("region");
 
      ["1", "2", "3",].forEach(function(item){ 
      {
              var newOption = document.createElement("option");
              newOption.text = item.toString();//item.whateverProperty

              dynamicSelect.add(newOption);
 
      }});
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
