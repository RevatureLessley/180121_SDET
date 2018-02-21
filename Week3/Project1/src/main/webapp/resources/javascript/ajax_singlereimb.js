window.onload = function() {
	var url = document.location.href;
	var id = url.split('?')[1].split('=')[1];
	console.log("id="+id);
	getReimbInfo(id);
}

function getReimbInfo(x) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "../../ApproveReimburse");
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("display info");
		}
	}
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhr.send("rid=" + x);
}

/*
function getSingleReimburse(){

	
	var rid = document.getElementByName("rlink")
}*/