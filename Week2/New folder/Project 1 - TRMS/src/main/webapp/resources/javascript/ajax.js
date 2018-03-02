function getUser() {
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {

			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("user");

			// sessionStorage.setItem

		} else if (xhr.readyState == 4 && xhr.status != 200) {
			console.log("ERROR, STATUS: " + xhr.status);
		}
	}
	xhr.open("GET", "GetUser");
	xhr.send();
}

function checkUsername() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "CheckUsernameAvailability");

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var message = document.getElementById("usernameAvail");
			if (this.responseText.trim() != "taken") {
				message.className += " text-success";
				message.innerHTML = "<Strong>Username available</Strong>";
			} else {
				message.className += " text-danger";
				message.innerHTML = "<Strong>Username is taken</Strong>";
			}

		} else if (xhr.readyState == 4 && xhr.status != 200) {
			console.log("ERROR, STATUS: " + xhr.status);
		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var username = document.forms["registerForm"]["username"].value;
	xhr.send("username=" + username);
}

function fillForm(checkbox, username) {
	var firstname = document.getElementById("firstname");
	var lastname = document.getElementById("lastname");
	var email = document.getElementById("email");
	var address = document.getElementById("address");
	var city = document.getElementById("city");
	var state = document.getElementById("state");
	var tel = document.getElementById("tel");
	if (checkbox.checked == true) {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "../GetCurrentEmp");

		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var xmlText = xhr.responseXML;
				var response = xmlText.getElementsByTagName("employee");

				for (e in response) {
					firstname.value = response[e].childNodes[2].innerHTML;
					lastname.value = response[e].childNodes[3].innerHTML;
					email.value = response[e].childNodes[4].innerHTML;
					address.value = response[e].childNodes[7].innerHTML;
					city.value = response[e].childNodes[8].innerHTML;
					state.value = response[e].childNodes[9].innerHTML;
					tel.value = response[e].childNodes[10].innerHTML;
				}

			} else if (xhr.readyState == 4 && xhr.status != 200) {
				console.log("ERROR, STATUS: " + xhr.status);
			}
		}
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		console.log(username);
		xhr.send("username=" + username);

	} else {
		firstname.value = "";
		lastname.value = "";
		email.value = "";
		address.value = "";
		city.value = "";
		state.value = "";
		tel.value = "";
	}

}

function updateEmp(username) {
	var firstname = document.forms["emp_info_form"]["firstname"].value;
	var lastname = document.forms["emp_info_form"]["lastname"].value;
	var email = document.forms["emp_info_form"]["email"].value;
	var address = document.forms["emp_info_form"]["address"].value;
	var city = document.forms["emp_info_form"]["city"].value;
	var state = document.forms["emp_info_form"]["state"].value;
	var tel = document.forms["emp_info_form"]["tel"].value;

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../UpdateEmployee");

	xhr.onreadystatechange = function() {
		var message = document.getElementById("updateMessage");
		if (xhr.readyState == 4 && xhr.status == 200) {
			message.className += " text-success";
			message.innerHTML = "Information Updated.";
		} else if (xhr.readyState == 4 && xhr.status != 200) {
			console.log("ERROR, STATUS: " + xhr.status);
			message.className += " text-danger";
			message.innerHTML = "Update Failed.";
		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("username=" + username + "&firstname=" + firstname + "&lastname="
			+ lastname + "&email=" + email + "&as=" + address + "&city=" + city
			+ "&state=" + state + "&tel=" + tel);
}

function loadUsers() {
	var xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.open("POST", "../GetEmployees");

	xhr.onreadystatechange = function() {
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if (xhr.readyState == 4 && xhr.status == 200) {
			var xmlText = xhr.responseXML;
			console.log(xmlText);

			var response = xmlText.getElementsByTagName("employee");
			console.log(response.length);

			var userTable = document.getElementById("userTable");

			for (e in response) {
				console.log(response[e]);
				var row = document.createElement("tr");
				row.setAttribute("id", response[e].childNodes[0].innerHTML);
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");
				var td6 = document.createElement("td");
				var td7 = document.createElement("td");
				var td8 = document.createElement("td");
				var td9 = document.createElement("td");
				var td10 = document.createElement("td");
				var td11 = document.createElement("td");

				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				td4.innerHTML = response[e].childNodes[3].innerHTML;

				// Title Select column
				var titleColumn = document.createElement("select");
				titleColumn.setAttribute("form", "update_emp_form");
				titleColumn.setAttribute("name", "title");
				titleColumn.setAttribute("id", "title");
				titleColumn.addEventListener("change", updateTitle);
				var option1 = document.createElement("option");
				option1.setAttribute("value", "Employee");
				option1.innerHTML = "Employee";
				var option2 = document.createElement("option");
				option2.setAttribute("value", "Department Head");
				option2.innerHTML = "Department Head";
				var option3 = document.createElement("option");
				option3.setAttribute("value", "BenCo");
				option3.innerHTML = "BenCo";
				if (response[e].childNodes[4].innerHTML == "Department Head")
					option2.setAttribute("selected", "selected");
				else if (response[e].childNodes[4].innerHTML == "BenCo")
					option3.setAttribute("selected", "selected");
				else
					option1.setAttribute("selected", "selected");
				titleColumn.add(option1);
				titleColumn.add(option2);
				titleColumn.add(option3);
				td5.appendChild(titleColumn);

				//Supervisor column
				var svColumn = document.createElement("select");
				svColumn.setAttribute("form", "update_emp_form");
				svColumn.setAttribute("name", "supervisor");
				svColumn.setAttribute("id", "supervisor");
				svColumn.addEventListener("change", updateSupervisor);
				var defaultOption = document.createElement("option");
				defaultOption.setAttribute("value", "");
				defaultOption.innerHTML = "N/A";
				svColumn.appendChild(defaultOption);
				if(response[e].childNodes[5].innerHTML == " ")
					defaultOption.setAttribute("selected", "selected");
				for (i = 0; i < response.length; i++) {
					var svOption = document.createElement("option");
					svOption.setAttribute("value",
							response[i].childNodes[0].innerHTML);
					svOption.innerHTML = response[i].childNodes[2].innerHTML
							+ " " + response[i].childNodes[3].innerHTML;
					svColumn.appendChild(svOption);
					if(response[e].childNodes[5].innerHTML == response[i].childNodes[2].innerHTML
							+ " " + response[i].childNodes[3].innerHTML)
						svOption.setAttribute("selected", "selected");
				}
				td6.appendChild(svColumn);

				td7.innerHTML = response[e].childNodes[6].innerHTML;
				td8.innerHTML = response[e].childNodes[7].innerHTML;
				td9.innerHTML = response[e].childNodes[8].innerHTML;
				td10.innerHTML = response[e].childNodes[9].innerHTML;
				td11.innerHTML = response[e].childNodes[10].innerHTML;

				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				row.appendChild(td11);

				userTable.appendChild(row);
			}
		} else if (xhr.readyState == 4 && xhr.status != 200) {
			console.log("ERROR, STATUS: " + xhr.status);
			// document.getElementById("AJAXError").innerHTML=xhr.status;
		}
	}

	xhr.send();
}


function updateTitle(event){
	var select = event.target;
	var userId = select.parentNode.parentNode.getAttribute("id");
	var option = select.options[select.selectedIndex].value;
	
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../UpdateTitle");

	xhr.onreadystatechange = function() {
		var message = document.getElementById("updateMsg");
		if (xhr.readyState == 4 && xhr.status == 200) {
			message.className += " text-success";
			message.innerHTML = "Title Updated.";
		} else if (xhr.readyState == 4 && xhr.status != 200) {
			console.log("ERROR, STATUS: " + xhr.status);
			message.className += " text-danger";
			message.innerHTML = "Update Failed.";
		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("id=" + userId + "&title=" + option);
}

function updateSupervisor(event){
	var select = event.target;
	var userId = select.parentNode.parentNode.getAttribute("id");
	var supervisorId = select.options[select.selectedIndex].value;
	console.log(userId);
	console.log(supervisorId);
	
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../UpdateSupervisor");

	xhr.onreadystatechange = function() {
		var message = document.getElementById("updateMsg");
		if (xhr.readyState == 4 && xhr.status == 200) {
			message.className += " text-success";
			message.innerHTML = "Supervisor Updated.";
		} else if (xhr.readyState == 4 && xhr.status != 200) {
			console.log("ERROR, STATUS: " + xhr.status);
			message.className += " text-danger";
			message.innerHTML = "Update Failed.";
		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("id=" + userId + "&supervisorId=" + supervisorId);
}


