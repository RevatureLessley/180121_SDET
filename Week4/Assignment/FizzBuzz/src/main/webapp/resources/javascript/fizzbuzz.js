/*
* Generate FizzBuzz
*/



function fizzbuzz(){
	var x = document.getElementById("number1");
	x = x.value;
	value = "";
	if(x % 3 == 0){
		value += "fizz";
	}
	if(x % 5 == 0){
		value += "buzz";
	}
	
	console.log(value);
	return value;
}

function checkValues() {
	num1 = document.getElementById("number1").value;
	num2 = document.getElementById("number2").value;
	
	console.log("num1: " + num1 + " num2: " + num2);
	
	if(num1 == "" || num2 == ""){
		return false;
	} else if(num1 < 0 || num1 > 100 || num2 < 0 || num2 > 100) {
		return false;
	} else {
		if(num2 < num1) {
			var tmp = num1;
			num1 = num2;
			num2 = num1;
			return true;
		}
	}
	
}

function generate() {
	fizzbuzz();
	
	document.getElementById("resulttable").innerHTML = "";
	
	var th1 = document.createElement("th");
	var th2 = document.createElement("th");
	
	th1.appendChild(document.createTextNode("NUMBER"));
	th2.appendChild(document.createTextNode("RESULT"));
	
	var row = document.createElement("tr");
	row.appendChild(th1);
	row.appendChild(th2);
	
	document.getElementById("resulttable").appendChild(row);
	
	if(checkValues()){
		
	} else {
		var h2 = document.createElement("h2");
		h2.appendChild("Please input valid numbers")
	}
}

var r = 0;
var num1 = -1;
var num2 = -1;