function validateLogin(){
	
	var xhr = new XMLHttpRequest(); 
	xhr.open("POST","login"); 
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	var username = document.forms["login"]["username"].value;
	var password = document.forms["login"]["pwd"].value;
	
	xhr.send("username=" + username + "&password=" + pwd);
	
	xhr.onreadystatechange = function(){
		console.log(xhr.readyState)
	}
	
}