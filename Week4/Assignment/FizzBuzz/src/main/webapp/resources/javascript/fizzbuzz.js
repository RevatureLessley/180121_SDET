/*
* Generate FizzBuzz
*/



function fizzbuzz(x){
	value = "";
	if(x % 3 == 0){
		value += "fizz";
	}
	if(x % 5 == 0){
		value += "buzz";
	}
	if(value == ""){
		value = x;
	}
	
	console.log(value);
	return value;
}

function checkValues() {
	num1 = document.getElementById("number1").value;
	num2 = document.getElementById("number2").value;
	
	console.log("num1: " + num1 + " num2: " + num2);
	
	if(num1 == "" || num2 == ""){
		console.log("1st if");
		return false;
	} else if(num1 < 0 || num1 > 100 || num2 < 0 || num2 > 100) {
		console.log("2nd if");
		return false;
	} else {
		if(num2 < num1) {
			var tmp = num1;
			num1 = num2;
			num2 = num1;
		}
		return true;
	}
	
}

function generate() {
	fizzbuzz();
	
	if(checkValues()){
		document.getElementById("resulttable").innerHTML = "";
		
		var th1 = document.createElement("th");
		var th2 = document.createElement("th");
		
		th1.appendChild(document.createTextNode("NUMBER"));
		th2.appendChild(document.createTextNode("RESULT"));
		
		var row = document.createElement("tr");
		row.appendChild(th1);
		row.appendChild(th2);
		
		document.getElementById("resulttable").appendChild(row);
		for(i = num1; i <= num2; i++) {
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			
			td1.appendChild(document.createTextNode(i));
			td2.appendChild(document.createTextNode(fizzbuzz(i)));
			
			var row = document.createElement("tr");
			row.appendChild(td1);
			row.appendChild(td2);
			
			document.getElementById("resulttable").appendChild(row);
		}
		
	} else {
		document.getElementById("resulttable").innerHTML = "";
		var h2 = document.createElement("h2");
		h2.appendChild(document.createTextNode("Please input valid numbers"));
		document.getElementById("resultdiv").appendChild(h2);
	}
}

var r = 0;
var num1 = -1;
var num2 = -1;