/**
 * 
 */
src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"



<<<<<<< HEAD
function getEm() {}
=======
function getEm() {
>>>>>>> refs/remotes/origin/jawaun
	var firsty = document.getElementById("fir");
	var lasty = document.getElementById("las");
	
	if(firsty > lasty){
<<<<<<< HEAD
		temper = firsty; 
=======
		temper = firsty;
>>>>>>> refs/remotes/origin/jawaun
		firsty = lasty;
		lasty = temper;}
	
	if(firsty < 0 || lasty >100){
		window.alert('value-INVALID');
		return false;}
<<<<<<< HEAD
	for(i = firsty; i <= lasty; i++){
		var tables=document.getElementById("tabf");
		var res = fizzbuzz(i);
=======
	let count = lasty;
	for(i = firsty; i <= count; i++){
		var res = fizbuz(i);
>>>>>>> refs/remotes/origin/jawaun
		var nurow = document.createElement("tr");
		var numcol = document.createElement("td");
		var fizzcol = document.createElement("td");
		var nums;
		var fizz;
		
<<<<<<< HEAD
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
		
=======
>>>>>>> refs/remotes/origin/jawaun
		nums = document.createTextNode(i);
		fizz = document.createTextNode(res);
		numcol.appendChild(nums); 
	  	fizzcol.appendChild(fizz);
	  	nurow.appendChild(numcol);
		nurow.appendChild(fizzcol);
<<<<<<< HEAD
		tables.insertRow(nurow);
=======
		document.getElementById("tabf").appendChild(nurow);
>>>>>>> refs/remotes/origin/jawaun
	}	
}


<<<<<<< HEAD



=======
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
>>>>>>> refs/remotes/origin/jawaun

