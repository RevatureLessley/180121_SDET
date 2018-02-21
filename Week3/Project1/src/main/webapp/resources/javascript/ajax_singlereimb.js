var id;
window.onload = function() {
	var url = document.location.href;
	id = url.split('?')[1].split('=')[1];
	console.log("id="+id);
	getEmpInfo(id);
	getReimbInfo(id);
}

function getEmpInfo(x) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../../SingleReEmpInfoServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			console.log(jsonObj);
			
			var table = document.getElementById("empinfo");
			
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
			
			td0.innerHTML = jsonObj.fname;
			td1.innerHTML = jsonObj.lname;
			td2.innerHTML = jsonObj.department;
			td3.innerHTML = jsonObj.title;
			td4.innerHTML = jsonObj.availreim;
			td5.innerHTML = jsonObj.addr;
			td6.innerHTML = jsonObj.city;
			td7.innerHTML = jsonObj.state;
			td8.innerHTML = jsonObj.zip;
			
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
			
		}
	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + x);
}

function getReimbInfo(x) {
	var xhr = new XMLHttpRequest();
	//xhr.open("POST", "../../ApproveReimburse");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("display info");
		}
	}
	
	//xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	//xhr.send("rid=" + x);
}

function appResponse() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../../ApproveReimburse");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update approval info");
		}
	}
	
	var r = document.getElementById("response").value;
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + id + "&response=" + r); //app response & emp id
}

function infoReq() {
	console.log("infoReq() : update in db");
}