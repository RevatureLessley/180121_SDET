function sendSuperAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetRequests"); //State == 1
	
	
	xhr.onreadystatechange = function(){
		
		console.log("HEYYYYYY");
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			
			
			var xmlText = xhr.responseXML;
			//console.log(xmlText.getElementsByTagName("request"))
			var response = xmlText.getElementsByTagName("req");
			console.log(response);
			//response is a collection of all employee tags.
			var resultTable = document.getElementById("results");
			//variable referencing our end table.
			for(r in response){
				
				var row = document.createElement("tr");
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

				
				td1.innerHTML = response[r].childNodes[0].innerHTML;
				td2.innerHTML = response[r].childNodes[1].innerHTML;
				td3.innerHTML = response[r].childNodes[2].innerHTML;
				td4.innerHTML = response[r].childNodes[3].innerHTML;
				td5.innerHTML = response[r].childNodes[4].innerHTML;
				td6.innerHTML = response[r].childNodes[5].innerHTML;
				td7.innerHTML = response[r].childNodes[6].innerHTML;
				td8.innerHTML = response[r].childNodes[7].innerHTML;
				td9.innerHTML = response[r].childNodes[8].innerHTML;
				td10.innerHTML = response[r].childNodes[9].innerHTML;
				td11.innerHTML = response[r].childNodes[10].innerHTML;
				
				
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
				
				resultTable.appendChild(row);
				
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

function sendDeptAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetDeptRequests"); //State == 1
	
	xhr.onreadystatechange = function(){
		
		console.log("HEYYYYYY");
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			
			
			var xmlText = xhr.responseXML;
			//console.log(xmlText.getElementsByTagName("request"))
			var response = xmlText.getElementsByTagName("req");
			console.log(response);
			//response is a collection of all employee tags.
			var resultTable = document.getElementById("results");
			//variable referencing our end table.
			for(r in response){
				
				var row = document.createElement("tr");
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

				
				td1.innerHTML = response[r].childNodes[0].innerHTML;
				td2.innerHTML = response[r].childNodes[1].innerHTML;
				td3.innerHTML = response[r].childNodes[2].innerHTML;
				td4.innerHTML = response[r].childNodes[3].innerHTML;
				td5.innerHTML = response[r].childNodes[4].innerHTML;
				td6.innerHTML = response[r].childNodes[5].innerHTML;
				td7.innerHTML = response[r].childNodes[6].innerHTML;
				td8.innerHTML = response[r].childNodes[7].innerHTML;
				td9.innerHTML = response[r].childNodes[8].innerHTML;
				td10.innerHTML = response[r].childNodes[9].innerHTML;
				td11.innerHTML = response[r].childNodes[10].innerHTML;
				
				
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
				
				resultTable.appendChild(row);
				
			}
			
		}
		else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			document.getElementById("AJAXError").innerHTML=xhr.status;
		}

	}
	
	xhr.send(); //State == 2
}

function sendAllAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","GetAllRequests"); //State == 1
	
	
	xhr.onreadystatechange = function(){
		
		console.log("HEYYYYYY");
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			
			
			var xmlText = xhr.responseXML;
			//console.log(xmlText.getElementsByTagName("request"))
			var response = xmlText.getElementsByTagName("req");
			console.log(response);
			//response is a collection of all employee tags.
			var resultTable = document.getElementById("results");
			//variable referencing our end table.
			for(r in response){
				
				var row = document.createElement("tr");
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

				
				td1.innerHTML = response[r].childNodes[0].innerHTML;
				td2.innerHTML = response[r].childNodes[1].innerHTML;
				td3.innerHTML = response[r].childNodes[2].innerHTML;
				td4.innerHTML = response[r].childNodes[3].innerHTML;
				td5.innerHTML = response[r].childNodes[4].innerHTML;
				td6.innerHTML = response[r].childNodes[5].innerHTML;
				td7.innerHTML = response[r].childNodes[6].innerHTML;
				td8.innerHTML = response[r].childNodes[7].innerHTML;
				td9.innerHTML = response[r].childNodes[8].innerHTML;
				td10.innerHTML = response[r].childNodes[9].innerHTML;
				td11.innerHTML = response[r].childNodes[10].innerHTML;
				
				
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
				
				resultTable.appendChild(row);
				
			}
			
		}
		else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			document.getElementById("AJAXError").innerHTML=xhr.status;
		}
	}
	
	xhr.send(); //State == 2
}


function DocAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("POST","DownloadDocs"); //State == 1
	
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("SUCCESS");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("download").appendChild(d);

		}else if(xhr.readyState == 4 && xhr.status!=200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("FAIL");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("download").appendChild(d);
		}

	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	var RequestDocID = document.forms["download"]["req_ID"].value;
	
	xhr.send("RequestDocID =" + RequestDocID);
	
	xhr.send(); //State == 2
}



