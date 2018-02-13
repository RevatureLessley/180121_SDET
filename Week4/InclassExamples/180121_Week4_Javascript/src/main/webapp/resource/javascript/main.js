window.onload = function(){ //Wait for the page to finish loading, then execute.
	document.getElementById("otherElement").innerHTML="stuff";	
	
	
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

var empCounter = 0;
var x = 0;