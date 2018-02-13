window.onload = function(){ //wait for the page to finish loading then execute
		document.getElementById("otherElement").innerHTML = "stuff";
}

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
	
	row.setAttribute("id", empCounter);
	
	document.getElementById("theTable").appendChild(row);
	
	document.getElementById('empName').value = "";
	document.getElementById('favMouse').value = "";
	
}

function removeRow(x){
	document.getElementById(x).remove();
}
var empCounter = 0;
var x = 0;