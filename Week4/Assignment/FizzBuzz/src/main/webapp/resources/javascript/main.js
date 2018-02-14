function genFizzBuzz(){
	
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	
	//swap if start is bigger then end
	if(start > end){
		temp = start;
		start = end;
		end = temp;
	}
	
	//check allowable range of start and end
	if(start < 0 || end >100){
		window.alert('Entered value is out of range, Please enter value between 0 and 100');
		return false;
	}
	
	//Fizz Buzz Logic
	for (var i=start; i <= end; i++)
	{
		var row = document.createElement('tr');
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		var num;
		var fb;
		
	    if (i % 15 == 0 && i!=0){
	    	num = document.createTextNode(i);
	    	fb = document.createTextNode("FizzBuzz"); 
	    }       
	    else if (i % 3 == 0 && i!=0){
	    	num = document.createTextNode(i);
	    	fb = document.createTextNode("Fizz");
	    }	
	    else if (i % 5 == 0 && i!=0){
	    	num = document.createTextNode(i);
	    	fb = document.createTextNode("Buzz");
	    }
	    else {
	    	num = document.createTextNode(i);
	    	fb = document.createTextNode(i);
	    }
	    
		td1.appendChild(num);
    	td2.appendChild(fb);
    	row.appendChild(td1);
    	row.appendChild(td2);
    	row.setAttribute("id", "row");
    	document.getElementById("theTable").appendChild(row);
    	
	}
     
}

//clear table contents
function clearTable(){
	
	var elmtTable = document.getElementById('theTable');
	var tableRows = elmtTable.getElementsByTagName('tr');
	var rowCount = tableRows.length;

	for (var x=rowCount-1; x>0; x--) {
	   elmtTable.removeChild(tableRows[x]);
	}
}
