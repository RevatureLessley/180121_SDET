document.getElementsById('VALUE1').oninput = function () {
        var max = parseInt(this.max);

        if (parseInt(this.value) > max) {
            this.value = max; 
        }
    }


function createFizzBuzz(){
	
	var elmtTable = document.getElementById('myTable');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var z = rowCount-1; z > 0; z--) {
	   elmtTable.removeChild(tableRows[z]);
	}
	
	
	
	var val1 = document.getElementById("VALUE1").value;
	var val2 = document.getElementById("VALUE2").value;
	var fizzBuzzArray = [];
	var numOutputArray = [];
	var td1Array = [];
	var td2Array = [];
	
	var x = val1;
	var y = val2;
	
	for(var num in numOutputArray){
		numOutputArray.pop();
	}
	
	for(var fb in fizzBuzzArray){
		fizzBuzzArray.pop(fb);
	}
	
	
	if (x < y){
		
		for(var i = x; i <= y; i++){
			if((i % 5) == 0)
				fizzBuzzArray.push("buzz");
			else if((i % 3) == 0)
				fizzBuzzArray.push("fizz");	
			else
				fizzBuzzArray.push(i);
			
			numOutputArray.push(i);
			}
		}
		
	else if (x > y){
		for(var i = y; i <= x; i++){
			if((i % 5) == 0){
				fizzBuzzArray.push("buzz");
			}else if((i % 3) == 0){
				fizzBuzzArray.push("fizz");	
			}else{
				fizzBuzzArray.push(i);
			}
			numOutputArray.push(i);
			}
		}
	
	
	for(var fb in fizzBuzzArray){
		console.log(fizzBuzzArray[fb]);
	}

	
	for(var num in numOutputArray){

		var td;
		var numOutput = document.createTextNode(numOutputArray[num]);
		var td = document.createElement('td'); //<td></td>
		td.appendChild(numOutput);
		td1Array.push(td);
	}
	
	for(var fb in fizzBuzzArray){
		var td1;
		var fizzBuzz = document.createTextNode(fizzBuzzArray[fb]);
		var td1 = document.createElement('td'); //<td></td>
		td1.appendChild(fizzBuzz);
		td2Array.push(td1);
	}
	
	for(var x = 0; x <td1Array.length; x++){
		
		var row = document.createElement('tr'); //<tr></tr>
		row.appendChild(td1Array[x]);
		row.appendChild(td2Array[x]);
		document.getElementById("myTable").appendChild(row);
	}
	
	}

	
	
	
	
	
