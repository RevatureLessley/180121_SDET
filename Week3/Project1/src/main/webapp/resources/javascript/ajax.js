/*
	AJAX JS File
	-- For all methods concerning the user submitting a new reimbursement request
 */
window.onload = function() {
	getEmp();
	getEventTypes();
	getLearnCenters();
	getGradeFormats();
	setUpEventListeners();
}

var availReimburse;
var coverages = [];
function getEmp() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../EmployeeInfoServlet");

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			document.getElementById("firstname").value = jsonObj.fname;
			document.getElementById("lastname").value = jsonObj.lname;
			document.getElementById("empid").value = jsonObj.empid;
			document.getElementById("addr").value = jsonObj.addr;
			document.getElementById("city").value = jsonObj.city;
			document.getElementById("state").value = jsonObj.state;
			document.getElementById("zip").value = jsonObj.zip;
			availReimburse = jsonObj.availreim;
		}
	}

	xhr.send();
}

function getEventTypes() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../EventTypesServlet");

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);

			var selector = document.getElementById("eventtype");

			for (var i = 0; i < jsonObj.length; i++) {
				var option = document.createElement("option");
				option.value = jsonObj[i].eventid;
				option.innerHTML = jsonObj[i].eventname;
				coverages.push(jsonObj[i].eventcoverage);

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
		if (xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);

			var selector = document.getElementById("learncenter");

			for (var i = 0; i < jsonObj.length; i++) {
				var option = document.createElement("option");
				option.value = jsonObj[i].centerid;
				option.innerHTML = jsonObj[i].centername;

				selector.appendChild(option);
			}
		}
	}

	xhr.send();
}

function getGradeFormats() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../GradeFormatServlet");

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);

			var div = document.getElementById("formattype");

			for (var i = 0; i < jsonObj.length; i++) {
				var label = document.createElement("label");
				var input = document.createElement("input");
				var span = document.createElement("span");

				span.className = "radio-inline";
				label.innerHTML = jsonObj[i].formatname;
				input.type = "radio";
				input.value = jsonObj[i].formatid;
				input.required = true;
				input.name = "formattype";

				span.appendChild(input);
				span.appendChild(label);
				div.appendChild(span);
			}
		}
	}

	xhr.send();
}

function calculateProjectedReimburse() {
	var eventtype = document.getElementById("eventtype").value;
	var cost = document.getElementById("cost");
	var cost_val = cost.value;
	console.log("DBG: cost=" + cost_val + " coverage="
			+ coverages[eventtype - 1]);
	var cal_cover = cost_val * coverages[eventtype - 1];
	if (cal_cover > availReimburse && availReimburse > 0) {
		cal_cover = availReimburse;
	} else if(availReimburse < 0) {
		cal_cover = 0;
	}
	console.log(cal_cover);
	document.getElementById("projectedreimburse").value = cal_cover.toFixed(2);

}

function setUpEventListeners() {
	var cost = document.getElementById("cost");
	var evettype = document.getElementById("eventtype");

	cost.onkeyup = calculateProjectedReimburse;
	eventtype.onchange = calculateProjectedReimburse;
}