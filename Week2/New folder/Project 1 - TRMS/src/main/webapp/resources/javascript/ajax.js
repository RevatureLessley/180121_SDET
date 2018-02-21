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
	xhr.send("username="+ username);
}