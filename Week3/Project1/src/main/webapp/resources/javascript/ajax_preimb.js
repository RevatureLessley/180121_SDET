window.onload = function() {
	getPersonalReimb();
}

function getPersonalReimb() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../PersonalReimburseServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			
			var table = document.getElementById("reimbursements");
			
			for(var i = 0; i < jsonObj.length; i++) {
				var tr = document.createElement("tr");
				var td0 = document.createElement("td");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");
				var td6 = document.createElement("td");
				var td7 = document.createElement("td");
				var td8 = document.createElement("td");
				
				var span = document.createElement("span");
				if(jsonObj[i].attachments) {
					span.className = "glyphicon glyphicon-ok";
				} else {
					span.className = "glyphicon glyphicon-remove";
				}
				
				td0.innerHTML = jsonObj[i].reimburseid;
				td1.innerHTML = jsonObj[i].date;
				td2.innerHTML = jsonObj[i].eventtype;
				td3.innerHTML = jsonObj[i].learncenter;
				td4.innerHTML = jsonObj[i].gradeformat;
				td5.innerHTML = jsonObj[i].cost.toFixed(2);
				td6.innerHTML = jsonObj[i].projReimb.toFixed(2);
				td7.appendChild(span);
				td8.innerHTML = jsonObj[i].status;
				
				tr.appendChild(td0);
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				tr.appendChild(td6);
				tr.appendChild(td7);
				tr.appendChild(td8);
				
				table.appendChild(tr);
				console.log(jsonObj[i]);
			}
		}
	}
	
	xhr.send();
}