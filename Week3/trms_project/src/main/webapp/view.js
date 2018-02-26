/**
 * 
 */
var XHR = new XMLHttpRequest();
function deptHeadApprove(i) {
	XHR.open("get", "/trms/deptHeadApprove?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}
/*
function processApprove(i) {
	if(XHR.readyState == 4 && XHR.status == 200){
		var button = document.getElementById("approveButton" + i);
		button.setAttribute("disabled", "true");
	}
}
*/
function deptHeadDeny(i){
	XHR.open("get", "/trms/deptHeadDeny?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}
/*function processDeny(i){
	if(XHR.readyState == 4 && XHR.status == 200){
		var button = document.getElementById("denyButton" + i);
		button.setAttribute("disabled", "true");
	}
}
*/
function deleteReimb(i){
	XHR.open("get", "/trms/DeleteReimb?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}
function processXML() {
	if(XHR.readyState == 4 && XHR.status == 200) {
		location.reload();
	}
}
function benCoApprove(i) {
	XHR.open("get", "/trms/benCoApprove?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}
function benCoDeny(i){
	XHR.open("get", "/trms/benCoDeny?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}
function dSupApprove(i){
	XHR.open("get", "/trms/dSupApprove?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}
function dSupDeny(i){
	XHR.open("get", "/trms/dSupDeny?rid=" + i);
	XHR.onreadystatechange = processXML;
	XHR.send();
}