/*
	AJAX JS File
	-- For all methods using ajax
*/
window.onload = function() {
	getEmp();
}

function getEmp() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "../EmployeeInfoServlet");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			var jsonObj = JSON.parse(xhr.responseText);
			document.getElementById("firstname").value = jsonObj.fname;
			document.getElementById("lastname").value = jsonObj.lname;
			document.getElementById("empid").value = jsonObj.empid;
			document.getElementById("addr").value = jsonObj.addr;
			document.getElementById("city").value = jsonObj.city;
			document.getElementById("state").value = jsonObj.state;
			document.getElementById("zip").value = jsonObj.zip;
			document.getElementById("reportsto").value = jsonObj.reportsto;
		}
	}
	
	xhr.send();
}