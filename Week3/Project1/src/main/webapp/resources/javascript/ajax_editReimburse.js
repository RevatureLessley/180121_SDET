function goBack() {
	window.history.back();
}

function appResponse() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../ApproveReimburse");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update approval info");
		}
	}
	
	var id = document.getElementById("rid").innerHTML;
	var r = document.getElementById("response").value;
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + id + "&response=" + r); //app response & emp id
	
	// TODO not back but like redirect
	window.history.back();
}

function readPageChanges() {
	var grade = document.getElementById("grade").value;
	
	if(grade != "") {
		aPostGradeUpdate();
	}
	
}

function aPostGradeUpdate() {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../UpdateGradeServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("update grade");
		}
	}
	
	var r = document.getElementById("rid").innerHTML;
	var g = document.getElementById("grade").value;
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + r + "&grade=" + g);
}