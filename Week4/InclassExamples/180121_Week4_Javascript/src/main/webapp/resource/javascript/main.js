
var empCounter = 0;
var x = 0;

window.onload = function(){ //Wait for the page to finish loading, then execute.
	document.getElementById("otherElement").innerHTML="stuff";	
	
	var d1 = document.getElementById("d1");
	var d2 = document.getElementById("d2");
	var d3 = document.getElementById("d3");
	//You can add events in javascript.
	//Takes 3 parameters:
	//	(event, functionToExecute, useCapture)
	/*
	 * event= the event you are listening for. It will just be the same as the html events,
	 * minus the 'on' from the beginning.
	 * 
	 * functionToExecute = the function that is executed. Please NOTE, you do not include
	 * parenthesis on the function call in this case. You pass the function as a variable to be
	 * called. (This is called a callback function)
	 * 
	 * Use capture - if true, we use capturing instead of bubbling.
	 * --if false, we use bubbling
	 * --if blank, we default to false. 
	 */
	d1.addEventListener("click", imClicked1, false);
	d2.addEventListener("click", imClicked2); //By default useCapture is false
	d3.addEventListener("click", imClicked3, false);
	
}
/*
 * When using eventListeners, it passes a single input of an Event object.
 * It is up to you whether or not to use it, but if you do, you can have access
 * to properties of the event itself.
 */
function imClicked1(event){
	window.alert("d1");
}
function imClicked2(x){
	window.alert("d2");
	x.stopPropagation();
	for(key in x){
		console.log(key + ": " + x[key]);
	}
	
}
function imClicked3(x){
	window.alert("d3");
	x.stopPropagation();
}

function logit(){
	console.log("This is log #" + ++x);
	document.getElementById("logPara").innerHTML += ("This is log #" + x + "<br>");
}

function addEmp(){
	//Store input values first
	var empName = document.getElementById("empName").value;
	var favMouse = document.getElementById("favMouse").value;
	
	//Create nodes for text to go into
	var nameText = document.createTextNode(empName); //Input
	var favText = document.createTextNode(favMouse); //Input
	var empId = document.createTextNode(++empCounter); //Counter
	var del = document.createTextNode("X"); //X
	
	//Create the elements to store our information
	var td1 = document.createElement('td'); //<td></td>
	var td2 = document.createElement('td'); //<td></td>
	var td3 = document.createElement('td'); //<td></td>
	var td4 = document.createElement('td'); //<td></td>
	
	var delBut = document.createElement('button'); //<button></button>
	delBut.setAttribute("onclick", "removeRow(" + empCounter + ")");
	//<button onclick="removeRow(Counter)></button>
	delBut.setAttribute("style","color:red");
	//<button onclick="removeRow(Counter) style="color:red"></button>
	delBut.appendChild(del);
	//<button onclick="removeRow(Counter) style="color:red">X</button>
	
	
	
	td1.appendChild(empId);
	//<td>Counter</td>
	td2.appendChild(nameText);
	//<td>input1</td>
	td3.appendChild(favText);
	//<td>input2</td>
	td4.appendChild(delBut);
	//<td><button onclick="removeRow(Counter) style="color:red">X</button></td>
	
	
	//Finally create the row
	var row = document.createElement('tr'); //<tr></tr>
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	/*
	 * <tr>
		 * 	<td>Counter</td>
		 * 	<td>input1</td>
		 * 	<td>input2</td>
		 * 	<td><button onclick="removeRow(Counter) style="color:red">X</button></td>
	 * </tr>
	 * 
	 */
	
	row.setAttribute("id", empCounter);
	/*
	 * <tr id=Counter>
		 * 	<td>Counter</td>
		 * 	<td>input1</td>
		 * 	<td>input2</td>
		 * 	<td><button onclick="removeRow(Counter) style="color:red">X</button></td>
	 * </tr>
	 */
	document.getElementById("theTable").appendChild(row);
	
	document.getElementById("empName").value = "";
	document.getElementById("favMouse").value = "";
	
}

function removeRow(x){
	document.getElementById(x).remove();
}

function changeWell(){
	var el = document.getElementById("magicWell");
	
	var random = Math.floor((Math.random()*4));
	if(random==0){
		el.setAttribute("style", "background-color:red");
	}
	if(random==1){
		el.setAttribute("style", "background-color:blue");
	}
	if(random==2){
		el.setAttribute("style", "background-color:green");
	}
	if(random==3){
		el.setAttribute("style", "background-color:yellow");
	}
	
}

//The purpose of any function referenced by 'onsubmit' is that the function must return
//TRUE in order for the submission to go through.
function validateRegistration(){
	var username = document.forms["register"]["username"].value;
	var password1 = document.forms["register"]["pwd1"].value;
	var password2 = document.forms["register"]["pwd2"].value;
	
	var errorDiv = document.createElement("div");
	errorDiv.setAttribute("class","alert alert-danger");
	errorDiv.setAttribute("id","error");
	
	console.log(document.getElementById("error")!=null);
	if(document.getElementById("error")!=null){
		document.getElementById("error").remove();
	}

	if(username=="bobbert"){
		var text = document.createTextNode("USERNAME TAKEN!");
		errorDiv.appendChild(text);
		
		
		document.getElementById("username").appendChild(errorDiv);
		return false;
	}

	if(password1!=password2){
		var text = document.createTextNode("PASSWORDS DON't MATCH");
		errorDiv.appendChild(text);
		
		document.getElementById("pwd").appendChild(errorDiv);
		return false;
	}
	
	return true;
}
