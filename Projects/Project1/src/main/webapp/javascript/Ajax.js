function sendAJAX(){
	var xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.open("GET","GetPersonalInfo");
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		var resp = xhr.responseText;
		if(xhr.readyState == 4 && xhr.status == 200){
			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("employee");
			var resultTable = document.getElementById("results");
			for(e in response){
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");
				var td6 = document.createElement("td");
				
				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				td4.innerHTML = response[e].childNodes[3].innerHTML;
				td5.innerHTML = response[e].childNodes[4].innerHTML;
				td6.innerHTML = response[e].childNodes[5].innerHTML;
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				
				resultTable.appendChild(row);
			}
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			document.getElementById("AJAXError").innerHTML=xhr.status;
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