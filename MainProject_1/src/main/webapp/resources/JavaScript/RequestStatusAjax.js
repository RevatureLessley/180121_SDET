window.onload=function(){
//GetAJAX();
sendAJAX();

}

function GetAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","UserViewDocRequest"); //State == 1
	
	
	xhr.onreadystatechange = function(){
		
		//console.log("HEYYYYYY");
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			/*
			 * In the event of a successful request/response, as indicated by state = 4, and
			 * status = 200, we will then take the data as XML.
			 */
			
			var xmlText = xhr.responseXML;
			//console.log(xmlText.getElementsByTagName("request"))
			
			
			var response = xmlText.getElementsByTagName("doc");
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
				

				
				td1.innerHTML = response[r].childNodes[0].innerHTML;
				td2.innerHTML = response[r].childNodes[1].innerHTML;
				td3.innerHTML = response[r].childNodes[2].innerHTML;
				td4.innerHTML = response[r].childNodes[3].innerHTML;
				
				
				
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
			
				
				
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


function sendAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	console.log(xhr.readyState);
	xhr.open("GET","UserViewRequest"); //State == 1
	
	
	xhr.onreadystatechange = function(){
		
		//console.log("HEYYYYYY");
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			/*
			 * In the event of a successful request/response, as indicated by state = 4, and
			 * status = 200, we will then take the data as XML.
			 */
			
			var xmlText = xhr.responseXML;
			//console.log(xmlText.getElementsByTagName("request"))
			var response = xmlText.getElementsByTagName("view");
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





function postAJAX(){
	var xhr = new XMLHttpRequest(); //State = 0
	xhr.open("GET","SubmitGradeServlet"); //State == 1
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("GRADE SUBMITTED");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("SubmitGrade").appendChild(d);

		}else if(xhr.readyState == 4 && xhr.status!=200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("WRONG GRADING FORMAT SUBMITTED");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("SubmitGrade").appendChild(d);
		}

	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	var requestId = document.forms["SubmitGrade"]["req_ID"].value;
	var grade = document.forms["SubmitGrade"]["Grade"].value;
	
	console.log(requestId);
	
	xhr.send("requestId=" + requestId + "grade=" + grade);
}