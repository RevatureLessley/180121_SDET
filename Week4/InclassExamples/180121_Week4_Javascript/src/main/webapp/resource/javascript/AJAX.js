function sendAJAX(){
<<<<<<< HEAD
	var xhr = new XMLHttpRequest();// state == 0
=======
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetEmployees"); //State == 1
>>>>>>> a1176a1f6a485bcbc06483a87b462e498942fa4c
	/*
	 * There exists 5 states of an XMLHttpRequest object.
	 * 0 - Request is not configured
	 * 		-We created our XMLHttpRequest object, but we have not configured it.
	 * 1 - Request has been configured
	 * 		-We have called the open(), but we have not called the send().
	 * 2 - Request has been sent
	 * 		-We have called send();
	 * 3 - Request is being processed
	 * 		-Communication with the server has been established.
	 * 		-Yet we have not received the full response yet.
	 * 4 - Request has been sent, and a proper response has been received back from server.
	 * 		-Request/Response lifecycle is complete.
	 */
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			/*
			 * In the event of a successful request/response, as indicated by state = 4, and
			 * status = 200, we will then take the data as XML.
			 */
			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("employee");
			//response is a collection of all employee tags.
			var resultTable = document.getElementById("results");
			//variable referencing our end table.
			for(e in response){
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				
				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				td4.innerHTML = response[e].childNodes[3].innerHTML;
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				
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
<<<<<<< HEAD
	xhr.open("GET","GetEmployees"); // state == 1
	xhr.send(); // state == 2
=======
	
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
>>>>>>> a1176a1f6a485bcbc06483a87b462e498942fa4c
}