window.onload=function(){
sendSuperAJAX();

}


function sendSuperAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetDocRequests"); //State == 1
	
	
	xhr.onreadystatechange = function(){
		
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			
			
			var xmlText = xhr.responseXML;
			//console.log(xmlText.getElementsByTagName("request"))
			var response = xmlText.getElementsByTagName("view");
			console.log(response);
			//response is a collection of all employee tags.
			var resultTable = document.getElementById("docResults");
			//variable referencing our end table.
			for(r in response){
				
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");
				

				
				td1.innerHTML = response[r].childNodes[0].innerHTML;
				td2.innerHTML = response[r].childNodes[1].innerHTML;
				td3.innerHTML = response[r].childNodes[2].innerHTML;
				td4.innerHTML = response[r].childNodes[3].innerHTML;
				td5.innerHTML = response[r].childNodes[4].innerHTML;
				
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				
				
				resultTableA.appendChild(row);
				
				
			}
			
		}
		else if(xhr.readyState == 4 && xhr.status!=200){
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