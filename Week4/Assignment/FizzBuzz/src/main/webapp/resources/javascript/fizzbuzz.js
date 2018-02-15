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
	num1 = parseInt(document.getElementById("number1").value);
	num2 = parseInt(document.getElementById("number2").value);
	
	if(document.getElementById("errormsg") != null){
		document.getElementById("errormsg").remove();
	}
	
	console.log("num1: " + num1 + " num2: " + num2);
	
	if(isNaN(num1) || isNaN(num2)){
		console.log("a value is empty");
		var h6 = document.createElement("h6");
		h6.appendChild(document.createTextNode("Can't have empty values!"));
		h6.setAttribute('id', 'errormsg');
		h6.setAttribute('style', 'color:red;');
		document.getElementById("question").appendChild(h6);
		return false;
	} else if(num1 < 0 || num1 > 100 || num2 < 0 || num2 > 100) {
		console.log("2nd if");
		return false;
	} else {
		console.log("b4 if| NUM1: " + num1 + "NUM2: " + num2);
		if(num2 < num1) {
			var tmp = num1;
			num1 = num2;
			num2 = tmp;
		}
		console.log("aft if| NUM1: " + num1 + "NUM2: " + num2);
		return true;
	}
	
}

function generate() {
	fizzbuzz();
	
	if(checkValues()){
		console.log("after check| NUM1: " + num1 + "NUM2: " + num2);
		document.getElementById("resulttable").innerHTML = "";
		
		var th1 = document.createElement("th");
		var th2 = document.createElement("th");
		
		th1.appendChild(document.createTextNode("NUMBER"));
		th2.appendChild(document.createTextNode("RESULT"));
		
		var row = document.createElement("tr");
		row.appendChild(th1);
		row.appendChild(th2);
		
		document.getElementById("resulttable").appendChild(row);
		console.log("after table| NUM1: " + num1 + "NUM2: " + num2);
		for(i = num1; i <= num2; i++) {
			console.log("NUM1: " + num1 + "NUM2: " + num2);
			var td1 = document.createElement("td");
			var td2 = document.createElement("td");
			
			td1.appendChild(document.createTextNode(i));
			td2.appendChild(document.createTextNode(fizzbuzz(i)));
			
			var row = document.createElement("tr");
			row.appendChild(td1);
			row.appendChild(td2);
			
			document.getElementById("resulttable").appendChild(row);
		}
		
	}
}

var r = 0;
var num1 = -1;
var num2 = -1;