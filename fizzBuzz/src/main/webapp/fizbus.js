/**
 * 
 */
src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"



function getEm() {}
	var firsty = document.getElementById("fir");
	var lasty = document.getElementById("las");
	
	if(firsty > lasty){
		temper = firsty; 
		firsty = lasty;
		lasty = temper;}
	
	if(firsty < 0 || lasty >100){
		window.alert('value-INVALID');
		return false;}
	for(i = firsty; i <= lasty; i++){
		var tables=document.getElementById("tabf");
		var res = fizzbuzz(i);
		var nurow = document.createElement("tr");
		var numcol = document.createElement("td");
		var fizzcol = document.createElement("td");
		var nums;
		var fizz;
		
		function fizzbuzz(i){
			l=(this.i % 3);
			m=(this.i % 5);
			n= (((Boolean(l)==false) && (Boolean(m)==false))  && i!=0);
			if(n==true){	
		    	fb = document.createTextNode("FizzBuzz"); 
		    		return "fizzbuzz";
		    	} else if ((Boolean(l)==false) && i!=0){				    		
				return "fizz";			
			} else if((Boolean(m)==false) && i!=0){				
				return"buzz";	
			} else if (this.i == null){
				return Nan;
			} else{
				return i;
			}
		}
		
		nums = document.createTextNode(i);
		fizz = document.createTextNode(res);
		numcol.appendChild(nums); 
	  	fizzcol.appendChild(fizz);
	  	nurow.appendChild(numcol);
		nurow.appendChild(fizzcol);
		tables.insertRow(nurow);
	}	
}






