function logout(){
	
	console.log("logout");
	var xhr = new XMLHttpRequest(); //State = 0
	xhr.open("POST","Logout"); //State == 1
	xhr.send();
	while(!(xhr.readyState == 4 && xhr.status == 200)){
	}
	return true;
};