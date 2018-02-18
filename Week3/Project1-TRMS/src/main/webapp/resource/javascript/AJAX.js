function sendAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetEmployees"); //State == 1
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
	
	xhr.send(); //State == 2
}


















/**postAjax() will be called on submission of a new registration form. All of the values will be parsed and sent to the RegisterEmp servlet for
 * further processing.**/
function registerAjax(){
	var xhr = new XMLHttpRequest(); //Creating new XMLHttpRequest object.
	xhr.open("POST","RegisterEmp"); //Configuring new XMLHttpRequest object to send a post to the servlet "RegisterEmp"
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){ // If successful,
			var d = document.createElement("div"); // Create div element
			d.setAttribute("class","well"); // Give this div the the attribute class="well"
			var p = document.createElement("h3"); // Create a h3 element
			var t = document.createTextNode("SUCCESS! Your account has been created successfully! Please use the navigation bar above to return to the home page or login!");
			p.appendChild(t); // Append the above message in the h3 that was created.
			d.appendChild(p); // Append the h3 element in the created div
			var w = document.getElementById("wholeform"); // Get the element ided by "wholeform"
			var b = document.getElementById("register"); // Get the element ided by "register"
			w.replaceChild(d,b); // Override the element ided by "register" with the new elements created.

		}else if(xhr.readyState == 4 && xhr.status!=200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("FAIL");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("register").appendChild(d);
		}
	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");// Not entirely sure what this does
	
	var empid = document.forms["register"]["empid"].value; // Obtain the value of empid from the form
	var fname = document.forms["register"]["fname"].value; // Obtain the value of fname from the form
	var lname = document.forms["register"]["lname"].value; // Obtain the value of lname from the form
	var username = document.forms["register"]["username"].value; // Obtain the value of username from the form
	var password = document.forms["register"]["password"].value; // Obtain the value of password from the form
	var email = document.forms["register"]["email"].value; // Obtain the value of email from the form 
	var role = document.forms["register"]["role"].value; // Obtain the value of role from the form
	var departments = document.forms["register"]["departments"].value; // Obtain the value of departments from the form
	var supid = document.forms["register"]["supid"].value; // Obtain the value of supid from the form
	
	//Send the httprequest object using the information obtained from the form.
	xhr.send("empid=" + empid + "&fname=" + fname + "&lname=" + lname + "&username=" + username
			+ "&password=" + password + "&email=" + email + "&role=" + role + "&departments=" + 
			departments + "&supid=" + supid);
}


function loginAjax(){
	
	
}