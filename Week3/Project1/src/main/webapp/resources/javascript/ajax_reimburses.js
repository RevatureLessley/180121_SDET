window.onload = function() {
	getReimburse();
}

function getReimburse() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../../ApprovalReimburseServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			
			var table = document.getElementById("reimbursetbody");
			
			for(var i = 0; i < jsonObj.length; i++) {
				console.log("in loop");
				var tr = document.createElement("tr");
				var td0 = document.createElement("td");
				var td9 = document.createElement("td");
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
				var a = document.createElement("a");
				a.innerHTML = jsonObj[i].reimburseid;
				a.setAttribute("id", "rlink"+a.innerHTML);
				a.setAttribute("onclick", "singleapp(event)");
				
				td0.appendChild(a);
				//td0.innerHTML = jsonObj[i].reimburseid;
				if(jsonObj[i].urgent == 0) {
					td9.innerHTML = "-";
				} else {
					td9.innerHTML = "!";
					td9.setAttribute("style", "color:red");
				}
				
				td1.innerHTML = jsonObj[i].date;
				td2.innerHTML = jsonObj[i].eventtype;
				td3.innerHTML = jsonObj[i].learncenter;
				td4.innerHTML = jsonObj[i].gradeformat;
				td5.innerHTML = jsonObj[i].cost.toFixed(2);
				td6.innerHTML = jsonObj[i].projReimb.toFixed(2);
				td7.appendChild(span);
				td8.innerHTML = jsonObj[i].status;
				
				tr.appendChild(td0);
				tr.appendChild(td9);
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				tr.appendChild(td6);
				tr.appendChild(td7);
				tr.appendChild(td8);
				
				table.appendChild(tr);
			}
		}
	}
	
	xhr.send();
}

function singleapp(e) {
	console.log(e.target.id);
	var a = document.getElementById(e.target.id).innerHTML;
	//var url = "http://localhost:8089/TRMS/user/approver/appreimburse.html?rid=" + encodeURIComponent(a);
	var url = "http://localhost:8089/TRMS/user/editpersonalreimburse.jsp?rid=" + encodeURIComponent(a);
	
	document.location.href = url;
}