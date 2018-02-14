function genFizzBuzz(){
	
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	
	if(start > end){
		temp = start;
		start = end;
		end = temp;
	}
	
	if(start < 0 || end >100){
		window.alert('Entered value is out of range, Please enter value between 0 and 100');
		return true;
	}
	
	var row = document.createElement('tr');
	var td1 = document.createElement('td');
	var td2 = document.createElement('td');
	var num;
	var fb;

	for (var i=start; i <= end; i++)
	{
		
	    if (i % 15 == 0){
	    	num = document.createTextNode(i);
	    	fb = document.createTextNode("FizzBuzz"); 
	    }       
	    else if (i % 3 == 0){
	    	num = document.createTextNode(i);
	    	fb = document.createTextNode("Fizz");
	    }	
	    else if (i % 5 == 0){
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
    	document.getElementById("theTable").appendChild(row);
	}
     
}
