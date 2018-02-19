/*
	AJAX JS File
	-- For all methods using ajax
*/
window.onload = function() {
	getEmp();
	getEventTypes();
	getLearnCenters();
}

function getEmp() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../EmployeeInfoServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			document.getElementById("firstname").value = jsonObj.fname;
			document.getElementById("lastname").value = jsonObj.lname;
			document.getElementById("empid").value = jsonObj.empid;
			document.getElementById("addr").value = jsonObj.addr;
			document.getElementById("city").value = jsonObj.city;
			document.getElementById("state").value = jsonObj.state;
			document.getElementById("zip").value = jsonObj.zip;
		}
	}
	
	xhr.send();
}

function getEventTypes() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../EventTypesServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			
			var selector = document.getElementById("eventtype");
			
			for(var i = 0; i < jsonObj.length; i++) {
				var option = document.createElement("option");
				option.value = jsonObj[i].eventid;
				option.innerHTML = jsonObj[i].eventname;
				
				selector.appendChild(option);
			}
		}
	}
	
	xhr.send();
}

function getLearnCenters() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../LearningCentersServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			
			var selector = document.getElementById("learncenter");
			
			for(var i = 0; i < jsonObj.length; i++) {
				var option = document.createElement("option");
				option.value = jsonObj[i].centerid;
				option.innerHTML = jsonObj[i].centername;
				
				selector.appendChild(option);
			}
		}
	}
	
	xhr.send();
}