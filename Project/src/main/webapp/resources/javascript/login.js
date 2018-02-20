function validateLogin(){
	
	
	xhr.onreadystatechange = function(){
		console.log(xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("test")
			var xmlText = xhr.responseXML;
			var response = xmlText.getElementById('result').innerHTML;
			if(response=="FAILED"){
				console.log(response);
				document.getElementById("result").innerHTML="INVALID USERNAME OR PASSWORD";
			} 
		}else if(xhr.readyState == 4 && xhr.status!=200){
			document.getElementById("result").innerHTML=xhr.status;
		}	
	}
	
	console.log("login")
	var xhr = new XMLHttpRequest(); 
	xhr.open("POST","login"); 
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	var username = document.forms["login"]["username"].value;
	var password = document.forms["login"]["password"].value;
	xhr.send("username=" + username + "&password=" + password);
	
}