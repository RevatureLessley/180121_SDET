function getStatus(){
	console.log("status")
	var xhr = new XMLHttpRequest(); 
	xhr.open("GET","../TuitionStatus.do"); 
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var xmlText = xhr.responseXML;
			
			var response = xmlText.getElementsByTagName("tuition");
		
			var resultTable = document.getElementById("results");

			for(e in response){
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
			
				
				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				td4.innerHTML = response[e].childNodes[3].innerHTML;
				td5.innerHTML = response[e].childNodes[4].innerHTML;
				td6.innerHTML = response[e].childNodes[5].innerHTML;
				td7.innerHTML = response[e].childNodes[6].innerHTML;
				td8.innerHTML = response[e].childNodes[7].innerHTML;
				td9.innerHTML = response[e].childNodes[8].innerHTML;
				td10.innerHTML = response[e].childNodes[9].innerHTML;
			
				
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
			
				
				resultTable.appendChild(row).style.textAlign = "center";
			}
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			document.getElementById("Error").innerHTML=xhr.status;
		}

	}
	
	xhr.send(); 
}

function deleteTuition(){
	document.getElementById("empty").innerHTML="";
	document.getElementById("success").innerHTML="";
	document.getElementById("failed").innerHTML="";
	console.log("delete");
	var t_id = document.getElementById("t_id").value;
	if (t_id == "") {
		console.log("empty");
		document.getElementById("empty").innerHTML="Id must be filled out";
    }else{
    	var xhr = new XMLHttpRequest(); 
    	xhr.open("POST","../DeleteTuition.do");
    	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	xhr.send("t_id=" + t_id);
    	xhr.onreadystatechange = function(){
			console.log(xhr.readyState);
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log("conection success")
				var xmlText = xhr.responseXML;
				var response = xmlText.getElementById('rs').innerHTML;
				console.log(response);
				if(response=="success"){
					document.getElementById("success").innerHTML="Delete Successful";
				}else if(response=="failed"){
					document.getElementById("failed").innerHTML="Delete Failed";
				}
			}
		}	
    }
}