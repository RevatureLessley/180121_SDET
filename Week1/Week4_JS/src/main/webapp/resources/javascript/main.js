window.onload = function(){ //wait for the page to finish loading then execute
		document.getElementById("otherElement").innerHTML = "stuff";
}

var empCounter = 0;
var x = 0;

function logit() {
	console.log("This is log #" + ++x);
	document.getElementById("logPara").innerHTML += ("This is log #" + x + "<br>");
}

function addEmp(){
	//Store input values first
	var empName = document.getElementById("empName").value;
	var favMouse = document.getElementById("favMouse").value;
	
	//Create nodes for text to go into
	var nameText = document.createTextNode(empName);
	var favText = document.createTextNode(favMouse);
	var empId = document.createTextNode(++empCounter);
	var del = document.createTextNode("X"); //X
	
	//Create the elements to store our information
	var td1 = document.createElement('td'); //<td></td>
	var td2 = document.createElement('td');
	var td3 = document.createElement('td');
	var td4 = document.createElement('td');
	
	var delBut = document.createElement('button'); //<button></button>
	//<button onclick="removeRow(Counter)></button>
	delBut.setAttribute("onclick","removeRow("+empCounter+")");
	//<button onclick="removeRow(Counter) style="color:red"></button>
	delBut.setAttribute("style", "color:red");
	//<button onclick="removeRow(Counter) style="color:red">X</button>
	delBut.appendChild(del);
	
	td1.appendChild(empId);  //<td>counter</td>
	td2.appendChild(nameText);
	td3.appendChild(favText);
	td4.appendChild(delBut);
	//<td><button onclick="removeRow(Counter) style="color:red">X</button></td>
	
	//Finally create the row
	var row = document.createElement('tr');//<tr></tr>
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	/*
	 * Create the full row with each of the columns <td>'s
	 */
	
	row.setAttribute("id", empCounter); //add an id to your dynamic row
	
	document.getElementById("theTable").appendChild(row);
	
	document.getElementById('empName').value = "";
	document.getElementById('favMouse').value = "";
	
}

function removeRow(x){
	document.getElementById(x).remove();
}

function changeWell(){
	var el = document.getElementById("magicWell");
	
	var random = Math.floor(Math.random()*4);
	if(random==0){
		el.setAttribute("style", "background-color:red");
	}
	if(random==1){
		el.setAttribute("style", "background-color:blue");
	}
	if(random==2){
		el.setAttribute("style", "background-color:green");
	}
	if(random==4){
		el.setAttribute("style", "background-color:yellow");
	}
}

//The purpose of any function referenced by 'onsubmit' is that the function must return
//True in order for the submission to go through.
function validateRegistration(){
	var valid = true;
	var username = document.forms["register"]["username"].value;
	var password1 = document.forms["register"]["pwd1"].value;
	var password2 = document.forms["register"]["pwd2"].value;
	
	var errorDiv = document.createElement("div");
	errorDiv.setAttribute("class", "alert alert-danger");
	errorDiv.setAttribute("id", "error");
	
	if(document.getElementById("error") != null) {
		document.getElementById("error").remove();
	}
	
	if(username == "bobbert"){
		var text = document.createTextNode("USERNAME TAKEN!");
		errorDiv.appendChild(text);
		
		document.getElementById("username").appendChild(errorDiv);
		return false;
	}
	if(password1!=password2){
		var text = document.createTextNode("PASSWORD DON'T MATCH!");
		errorDiv.appendChild(text);
		
		document.getElementById("pwd1").appendChild(errorDiv);
		return false;
	}
	
	return valid;
}