function FizzBuzz() {
	// Clearing any previous generations
	if (total != 0) {
		for (var j = 1; j <= total; j++) {
			clearTable("generation");
		}
		total = 0;
	}

	// Storing inputs
	var c = parseInt(document.getElementById("num1").value);
	var d = parseInt(document.getElementById("num2").value);

	// Checking to ensure they are greater than 0 and less than 100
	if (c < 0) {
		return false;
	} else if (d < 0) {
		return false;
	} else if (c > 100) {
		return false;
	} else if (d > 100) {
		return false;
	}
	console.log("BEFORE CHECK c=" + c);
	console.log("BEFORE CHECK d=" + d);
	// Checking which input is less than the other.
	if (c < d) {
		n1 = c;
		n2 = d;
	} else {
		n1 = d;
		n2 = c;
	}
	console.log("BEFORE LOOP n1=" + n1);
	console.log("BEFORE LOOP n2=" + n2);
	//itterating through all numbers between n1 and n2
	for (var i = n1; i <= n2; i++) {
		counter = i;
		console.log("AFTER LOOP n1=" + n1);
		console.log("AFTER LOOP n2=" + n2);
		//ensure 0 is included
		if (i == 0) {
			console.log(i);
			printTable(i);
			total++;
		//If a multiple of 3 and 5, print "FizzBuzz"
		} else if (i % 15 == 0) {
			console.log(i);
			printTable("FizzBuzz");
			total++;
		//If a multiple of 3, print "Fizz"
		} else if (i % 3 == 0) {
			console.log(i);
			printTable("Fizz");
			total++;
		//If a multiple of 5, print "Buzz"
		} else if (i % 5 == 0) {
			console.log(i);
			printTable("Buzz");
			total++;
		//Otherwise just print the number.
		} else {
			console.log(i);
			printTable(i);
			total++;
		}
	}
}

//function used for creating and printing  the table
function printTable(a) {
	// Creating text nodes for numbers to be printed
	var countText = document.createTextNode(counter); // Counter
	var nText = document.createTextNode(a); // Number to be printed
	// Creating column for table
	var td1 = document.createElement('td'); // <td></td>
	var td2 = document.createElement('td'); // <td></td>
	// Adding values into elements
	td1.appendChild(countText);// <td>counter</td>
	td2.appendChild(nText);// <td>First Number</td>
	// Creating rows for table
	var row = document.createElement('tr'); // <tr></tr>
	row.appendChild(td1);
	row.appendChild(td2);
	//Set id of rows to "generation" so they can be identified and deleted on the next run.
	row.setAttribute("id", "generation");
	// Append all to existing table in html document
	document.getElementById("theTable").appendChild(row);
}

//Remove every row with "generations" as the id
function clearTable(elementID) {document.getElementById(elementID).remove();}

//Variable for storing the actual value of the number next to its fizzbuzz counterpart
var counter = 0;
//Variable for storing the total number of rows created in the table
var total = 0;
//Variable for storing the first number
var n1 = 0;
//Variable for storing the second number
var n2 = 0;