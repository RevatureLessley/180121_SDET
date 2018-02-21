//import {getUser} from 'ajax';
//window.onload = function(){
//	getUser();
//}

function checkPasswordMatch(){
	var pass = document.getElementById("password").value;
	var pass2 = document.getElementById("password2").value;
	var errMsg = document.getElementById("pwMismatchMsg");
	if(pass != pass2){
		errMsg.innerHTML = "<Strong>Passwords do not match</Strong>";
		return false;
	}else{
		errMsg.innerHTML = "";
		return true;
	}
}

