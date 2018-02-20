function register(){
	console.log("register AJAX");
	var xhr = new XMLHttpRequest(); 
	xhr.open("POST","Register.do");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var firstname = document.forms["register"]["firstname"].value;
	var lastname = document.forms["register"]["lastname"].value;
	var title = document.forms["register"]["title"].value;
	var username = document.forms["register"]["username"].value;
	var password = document.forms["register"]["password"].value;
	var password2 = document.forms["register"]["password2"].value;
	var email = document.forms["register"]["email"].value;
	var s_firstname = document.forms["register"]["s_firstname"].value;
	var s_lastname = document.forms["register"]["s_lastname"].value;
	
	if(password == password2){
		xhr.send("firstname=" + firstname + "&lastname=" + lastname + "&title=" + title + "&username=" + username 
				+ "&password=" + password + "&email=" + email + "&s_firstname=" + s_firstname + "&s_lastname="+ s_lastname);
		xhr.onreadystatechange = function(){
			console.log(xhr.readyState);
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log("conection success")
				var xmlText = xhr.responseXML;
				var response = xmlText.getElementById('rs').innerHTML;
				console.log(response);
				if(response=="UserNameTaken"){
					document.getElementById("nameTaken").innerHTML="Username Taken. Try different name";
				} else if(response=="success"){
					document.getElementById("success").innerHTML="Registration Successful";
				}else if(response=="failed"){
					document.getElementById("failed").innerHTML="Registration Failed";
				}
			}
		}
	}else {
		document.getElementById("notMatch").innerHTML="Password does not match";
	}
	
}

