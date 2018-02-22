function getName(){
	var xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.open("GET","GetStringValue");
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		var resp = xhr.responseText;
		if(xhr.readyState == 4 && xhr.status == 200){
			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("stringval");
			console.log(response);
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			window.location.href = "index.html";
		}
	}
	xhr.send();
}

function postAjax(){
	var xhr = new XMLHttpRequest();
	xhr.open("POST","PostPersonalInfo");
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("SUCCESS");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("register").appendChild(d);

		}else if(xhr.readyState == 4 && xhr.status!=200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("FAIL");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("register").appendChild(d);
		}

	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	var email = document.forms["register"]["email"].value;
	var firstname = document.forms["register"]["firstname"].value;
	var lastname = document.forms["register"]["lastname"].value;
	var address = document.forms["register"]["address"].value;
	var jobtitle = document.forms["register"]["jobtitle"].value;
	var date = document.forms["register"]["date"].value;
	
	
	xhr.send("email=" + email + "&firstname=" + firstname + "&lastname=" 
			+ lastname + "&address=" + address + "&jobtitle=" + jobtitle
			+ "&date=" + date);
}