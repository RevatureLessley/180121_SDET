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
			var response = xmlText.getElementsByTagName("person");
			var resultTable = document.getElementById("results");
			for(e in response){
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				
				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				
				resultTable.appendChild(row);
			}
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
		}

	}
	xhr.send();
}