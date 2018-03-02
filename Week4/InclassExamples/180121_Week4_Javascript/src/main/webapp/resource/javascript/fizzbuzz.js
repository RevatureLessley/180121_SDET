
function passValue(){
	
	var oneNum = document.getElementById("oneNum").value;
	var twoNum = document.getElementById("twoNum").value;
	
	if(oneNum == "" || twoNum == ""){
		window.alert("Enter two nums between 0 and 100");
		return;
	}
	
	if(oneNum > 100 || oneNum < 0 || twoNum > 100 || twoNum < 0){
		window.alert("Input must be between 0 and 100");
		return;
	}
	
	removeBody("theBody");
	
	addBody();
	
	if(oneNum - twoNum > 0){
		var highNum = oneNum;
		var lowNum = twoNum;
	}
	else{
		var highNum = twoNum;
		var lowNum = oneNum;
	}
	
	console.log("lowNum: " + lowNum);
	console.log("highNum: " + highNum);
	i = lowNum;
	console.log("i: " + i);
	console.log(highNum - i > 0);
	console.log(i < highNum);
	for(i = lowNum; highNum - i + 1 > 0; i++){
		var passTo;
		if(i != 0 && (i%3 == 0 || i%5 == 0)){
			passTo = "";
			if(i%3 == 0){
				passTo += "fizz";
			}
			if(i%5 == 0){
				passTo += "buzz";				
			}
		}
		else{
			passTo = i;
		}			
		makeRow(i, passTo);
	}

}

function makeRow(lowNum, highNum){
	
	var row = document.createElement('tr'); //<tr></tr>
	
	var td1 = document.createElement('td'); //<td></td>
	var td2 = document.createElement('td'); //<td></td>
	
	var lowNum = document.createTextNode(lowNum); //Input
	var highNum = document.createTextNode(highNum); //Input
			
	td1.appendChild(lowNum);
	td2.appendChild(highNum);

	row.appendChild(td1);
	row.appendChild(td2);

	row.setAttribute("num", i);

	document.getElementById("theBody").appendChild(row);
	
//	document.getElementById("lowNum").value = "";
//	document.getElementById("favNum").value = "";
}

function removeBody(x){
	document.getElementById(x).remove();
}

function addBody(){
	var body = document.createElement('tbody');
	document.getElementById("theTable").appendChild(body);
	body.id = "theBody";
}
