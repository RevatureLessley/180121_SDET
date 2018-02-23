

/**registerAjax() will be called on submission of a new registration form. All of the values will be parsed and sent to the RegisterEmp servlet for
 * further processing.**/
function registerAjax(){
	var xhr = new XMLHttpRequest(); //Creating new XMLHttpRequest object.
	xhr.open("POST","RegisterEmp"); //Configuring new XMLHttpRequest object to send a post to the servlet "RegisterEmp"
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){ // If successful,
			var d = document.createElement("div"); // Create div element
			d.setAttribute("class","well"); // Give this div the the attribute class="well"
			var p = document.createElement("h3"); // Create a h3 element
			var t = document.createTextNode("SUCCESS! Your account has been created successfully! Please use the navigation bar above to return to the home page or login!");
			p.appendChild(t); // Append the above message in the h3 that was created.
			d.appendChild(p); // Append the h3 element in the created div
			var w = document.getElementById("wholeform"); // Get the element ided by "wholeform"
			var b = document.getElementById("register"); // Get the element ided by "register"
			w.replaceChild(d,b); // Override the element ided by "register" with the new elements created.

		}else if(xhr.readyState == 4 && xhr.status!=200){
			var d = document.createElement("div");
			var p = document.createElement("p");
			var t = document.createTextNode("FAIL");
			p.appendChild(t);
			d.appendChild(p);
			document.getElementById("register").appendChild(d);
		}
	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");// Not entirely sure what this does
	
	var empid = document.forms["register"]["empid"].value; // Obtain the value of empid from the form
	var fname = document.forms["register"]["fname"].value; // Obtain the value of fname from the form
	var lname = document.forms["register"]["lname"].value; // Obtain the value of lname from the form
	var username = document.forms["register"]["username"].value; // Obtain the value of username from the form
	var password = document.forms["register"]["password"].value; // Obtain the value of password from the form
	var email = document.forms["register"]["email"].value; // Obtain the value of email from the form 
	var role = document.forms["register"]["role"].value; // Obtain the value of role from the form
	var departments = document.forms["register"]["departments"].value; // Obtain the value of departments from the form
	var supid = document.forms["register"]["supid"].value; // Obtain the value of supid from the form
	
	//Send the httprequest object using the information obtained from the form.
	xhr.send("empid=" + empid + "&fname=" + fname + "&lname=" + lname + "&username=" + username
			+ "&password=" + password + "&email=" + email + "&role=" + role + "&departments=" + 
			departments + "&supid=" + supid);
}

/**reimburseAjax() will be called on submission of a new reimbursement form. All of the values will be parsed and sent to the NewReimbursement servlet for
 * further processing.**/
function reimburseAjax(){
	
	var xhr = new XMLHttpRequest(); //Creating new XMLHttpRequest object.
	xhr.open("POST","../newreimburse"); //Configuring new XMLHttpRequest object to send a post to the servlet "NewReimbursement"
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){ // If successful,
			var d = document.createElement("div"); // Create div element
			d.setAttribute("class","well"); // Give this div the the attribute class="well"
			d.setAttribute("id","sucess");
			var p = document.createElement("h3"); // Create a h3 element
			var t = document.createTextNode("SUCCESS! Your reimbursement has been sumbitted! Please use the navigation bar above to return to the home page!");
			p.appendChild(t); // Append the above message in the h3 that was created.
			d.appendChild(p); // Append the h3 element in the created div
			var w = document.getElementById("wholeform1"); // Get the element ided by "wholeform1"
			var b = document.getElementById("reimburse"); // Get the element ided by "reimburse"
			w.replaceChild(d,b); // Override the element ided by "register" with the new elements created.

		}else if(xhr.readyState == 4 && xhr.status!=200){
			
		}
	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");// Not entirely sure what this does
	
	var typeofevent = document.forms["reimburse"]["typeofevent"].value; // Obtain the value of typeofevent from the form
	var desc = document.forms["reimburse"]["desc"].value; // Obtain the value of desc from the form
	var work = document.forms["reimburse"]["work"].value; // Obtain the value of work from the form
	var grade = document.forms["reimburse"]["grade"].value; // Obtain the value of location from the form
	var location = document.forms["reimburse"]["location"].value; // Obtain the value of location from the form
	var dateof = document.forms["reimburse"]["dateof"].value; // Obtain the value of dateof from the form
	var timeof = document.forms["reimburse"]["timeof"].value; // Obtain the value of timeof from the form
	var cost = document.forms["reimburse"]["cost"].value; // Obtain the value of typeofevent from the form
	var customgradeformat = document.forms["reimburse"]["customgradeformat"].value; // Obtain the value of location from the form
	var customgradedesc = document.forms["reimburse"]["customgradedesc"].value; // Obtain the value of location from the form

	//Send the httprequest object using the information obtained from the form.
	xhr.send("typeofevent=" + typeofevent + "&desc=" + desc + "&work=" + work + "&grade=" + grade + "&location=" + location
			+ "&dateof=" + dateof + "&timeof=" + timeof + "&cost=" + cost + "&customgradeformat=" + customgradeformat + "&customgradedesc=" + customgradedesc);
}