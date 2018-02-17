/**
 * 
 */
src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"



function getEm() {
	var firsty = document.getElementById("fir");
	var lasty = document.getElementById("las");
	
	if(firsty > lasty){
		temper = firsty;
		firsty = lasty;
		lasty = temper;}
	
	if(firsty < 0 || lasty >100){
		window.alert('value-INVALID');
		return false;}
	let count = lasty;
	for(i = firsty; i <= count; i++){
		var res = fizbuz(i);
		var nurow = document.createElement("tr");
		var numcol = document.createElement("td");
		var fizzcol = document.createElement("td");
		var nums;
		var fizz;
		
		nums = document.createTextNode(i);
		fizz = document.createTextNode(res);
		numcol.appendChild(nums); 
	  	fizzcol.appendChild(fizz);
	  	nurow.appendChild(numcol);
		nurow.appendChild(fizzcol);
		document.getElementById("tabf").appendChild(nurow);
	}	
}


function fizbuz(fb1){
	l=(this.fb1 % 3);
	m=(this.fb1 % 5);
	n= (((Boolean(l)==false) && (Boolean(m)==false))  && fb1!=0);
	if(n==true){	
    	fb = document.createTextNode("FizzBuzz"); 
    		return "fizzbuzz";
    	} else if ((Boolean(l)==false) && fb1!=0){				    		
		return "fizz";			
	} else if((Boolean(m)==false) && fb1!=0){				
		return"buzz";	
	} else if (this.fb1 == null){
		return Nan;
	} else{
		return v;
	}
}

