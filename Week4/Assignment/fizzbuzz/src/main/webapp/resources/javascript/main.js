var counter = 0;



function fizzBuzz(){
	//generates new table if table already has values in it
	if(counter != 0){
		for(var i=1; i <= counter;i++){
			document.getElementById("result").remove();
		}
		
	}
	
	var num1 = document.getElementById("num1").value;
	var num2 = document.getElementById("num2").value;
	
	//checking values and alerting user to enter proper values
	if(num1 == "" || num2 == ""){
		window.alert("Please Enter two numbers between 0 and 100!!!!");
		return;
	}
	
	if(num1 > 100 || num1 < 0 || num2 > 100 || num2 < 0){
		window.alert("Please Enter two numbers between 0 and 100!!!!");
		return;
	}
	
	if(isNaN(num1) || isNaN(num2)){
			window.alert("Please Enter two numbers between 0 and 100!!!!");
		return;
}
	
	
	// swap numbers true condition using xor
	if(num1 > num2){ 
	num1 = ( num1 && !num2 ) || ( !num1 && num2) ;
	num2 = ( num1 && !num2 ) || ( !num1 && num2 );
	num1 = ( num1 && !num2 ) || ( !num1 && num2 );
		
	}
	

	//fizzbuzz  and creation/insertion of table
	for (var i=num1; i <= num2; i++)
	{
		var row = document.createElement('tr');
		var td1 = document.createElement('td');
		var td2 = document.createElement('td');
		
	    if (i % 15 == 0 && i!=0){
	    	counter++;
			td1.appendChild(document.createTextNode(i));
	    	td2.appendChild(document.createTextNode("FizzBuzz"));
	    }       
	    else if (i % 3 == 0 && i!=0){
	    	counter++;
			td1.appendChild(document.createTextNode(i));
	    	td2.appendChild(document.createTextNode("Fizz"));
	    }	
	    else if (i % 5 == 0 && i!=0){
	    	counter++;
			td1.appendChild(document.createTextNode(i));
	    	td2.appendChild(document.createTextNode("Buzz"));
	    }
	    else {
	    	counter++;
	    	td1.appendChild(document.createTextNode(i));
	    	td2.appendChild(document.createTextNode(i));

	    	
	    }
	    
		
    	row.appendChild(td1);
    	row.appendChild(td2);
    	row.setAttribute("id", "result");
    	document.getElementById("fbTable").appendChild(row);
    	
}

}
