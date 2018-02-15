function validateOnblur(element) {
	var x = element.value;
	if (x >= 0 && x <= 100) {
		element.parentNode.nextElementSibling.className = "hidden";
		return true;
	}
	element.parentNode.nextElementSibling.className = "";
	return false;
}

function validate() {
	var e1 = document.getElementById("num1");
	var e2 = document.getElementById("num2");
	if (validateOnblur(e1) && validateOnblur(e2)) {
		console.log(e1.value);
		console.log(e2.value);
		fizzBuzz(e1.value, e2.value);
		return;
	}
	console.log("Not doing stuff");
}

function fizzBuzz(a, b) {
	if (a > b) {
		var c = a;
		a = b;
		b = c;
	}
	document.getElementById("result").innerHTML = "<h4>fizzbuzz between " + a + " and " + b + "</h4>" +
			"<table id='fizzBuzzTable' border = '2px'>" +
			"<tr><th>Index</th><th>Fizz Buzz</th></tr></table>";

	console.log("generating fizzbuzz between " + a + " and " + b);

	var fizzBuzz;
	var numText;
	var fizzBuzzText;
	var td1, td2;
	var row;
	for (i = a; i <= b; i++) {
		fizzBuzz = "";
		if (i % 3 == 0) {
			fizzBuzz += "fizz ";
		}
		if (i % 5 == 0) {
			fizzBuzz += "buzz";
		}
		if (i % 3 != 0 && i % 5 != 0) {
			fizzBuzz = i;
		}

		numText = document.createTextNode(i);
		fizzBuzzText = document.createTextNode(fizzBuzz);

		td1 = document.createElement('td');
		td2 = document.createElement('td');

		td1.appendChild(numText);
		td2.appendChild(fizzBuzzText);

		row = document.createElement('tr');
		row.appendChild(td1);
		row.appendChild(td2);

		document.getElementById("fizzBuzzTable").appendChild(row);
	}

}