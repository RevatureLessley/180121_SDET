function FizzBuzz(){
	//Clearing any previous generations
	if(total != 0){
		for(var j=1; j <= total;j++){
			clearTable("generation");
		}
		total = 0;
	}
	//Storing inputs
	var c = document.getElementById("num1").value;
	var d = document.getElementById("num2").value;
	
	//Checking which input is less than the other.
	if(c < d){
		n1 = c;
		n2 = d;
	}else{
		n1 = d;
		n2 = c;
	}
	


for (var i=n1; i <= n2; i++){
	counter = i;
	
	if(i == 0){
		printTable(i);
		total++;
		}
	else if (i % 15 == 0){
        printTable("FizzBuzz");
        total++;
		}
    else if (i % 3 == 0){
        printTable("Fizz");
        total++;
    	}
    else if (i % 5 == 0){
        printTable("Buzz");
        total++;
    	}
    else{
        printTable(i);
        total++;
    	}
  }
}
function printTable(a){
	//Creating text nodes for numbers to be printed
	var countText = document.createTextNode(counter); //Counter
	var nText = document.createTextNode(a); //Number to be printed
	
	//Creating column for table
	var td1 = document.createElement('td'); //<td></td>
	var td2 = document.createElement('td'); //<td></td>

	//Adding values into elements
	td1.appendChild(countText);//<td>counter</td>
	td2.appendChild(nText);//<td>First Number</td>
	
	//Creating rows for table
	var row = document.createElement('tr'); //<tr></tr>
	row.appendChild(td1);
	row.appendChild(td2);
	
	row.setAttribute("id","generation");
	
	//Append all to existing table in html document
	document.getElementById("theTable").appendChild(row);
}

function clearTable(elementID){
		document.getElementById(elementID).remove();
}


var counter = 0;
var total = 0;
var n1 = 0;
var n2 = 0;
var gen = 0;