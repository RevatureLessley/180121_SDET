function home(){
	console.log("home")
	var xhr = new XMLHttpRequest(); 
	xhr.open("GET","../Home.do"); 
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var xmlText = xhr.responseXML;
			
			var response = xmlText.getElementById("reimburse");
			var name = xmlText.getElementById("user").innerHTML;
			
			console.log(name);
			document.getElementById("name").innerHTML = "Welcome "+name;
			
			var resultTable = document.getElementById("reimburse");

				var row = document.createElement("tr");
				
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				
				td1.innerHTML = response.childNodes[0].innerHTML;
				td2.innerHTML = response.childNodes[1].innerHTML;
				td3.innerHTML = response.childNodes[2].innerHTML;
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
			
				resultTable.appendChild(row).style.textAlign = "center";
				
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			document.getElementById("Error").innerHTML=xhr.status;
		}

	}
	
	xhr.send(); 
}