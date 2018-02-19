window.onload = function(){ 

	var xhr = new XMLHttpRequest();

	xhr.open("POST","GetLoginName");
	xhr.send();

	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){

			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("LoggedInUser");
			//response is a collection of all employee tags.
			var paragraph = document.getElementById("LoginName");
			//variable referencing our end table.

			for(e in response){
				paragraph.innerHTML = response[e].childNodes[0].innerHTML;			
			}


		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log(xhr.status);
//			document.getElementById("LoginName").innerHTML=xhr.status;
		}

	}

}