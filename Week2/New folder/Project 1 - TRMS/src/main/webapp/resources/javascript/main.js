
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

function viewApplicationDetail(appid){
	window.location = "applicationDetail.jsp?appId=" + appid;
}

function checkTitle(title){
	if (title == "Department Head"){
		document.forms["app_form"].action = "dhAction.do";
	}else if (title=="BenCo"){
		document.forms["app_form"].action = "bcAction.do";
	}
	document.forms["app_form"].submit();
}