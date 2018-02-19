function sendAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetEmployees"); //State == 1
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			/*
			 * In the event of a successful request/response, as indicated by state = 4, and
			 * status = 200, we will then take the data as XML.
			 */
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
		/*
		 * The open method is used to configure the actual XMLHttpRequest object.
		 * We configure what kind of HTTP method we are using, and where it is going.
		 * The parameters look like:
		 * 	open(HTTPMETHOD, ENDPOINT, UseAsynchronous)
		 * 			-note, UseAsynchronous is optional and will default to true.
		 * 
		 */

	}
	
	xhr.send(); //State == 2
}

function postAjax(){
	var xhr = new XMLHttpRequest(); //State = 0
	xhr.open("POST","RegisterEmployee"); //State == 1
	
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
	
	var name = document.forms["register"]["name"].value;
	var salary = document.forms["register"]["salary"].value;
	var title = document.forms["register"]["title"].value;
	
	xhr.send("name=" + name + "&salary=" + salary + "&title=" + title);
}