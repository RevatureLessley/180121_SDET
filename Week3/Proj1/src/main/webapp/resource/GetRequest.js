function sendAJAX(){
	var xhr = new XMLHttpRequest();

	xhr.open("GET","GetRequests");
	xhr.send();
	
	xhr.onreadystatechange = function(){
		console.log("READY STATE CHANGE: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){

			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("reimbursement");
			var resultTable = document.getElementById("results");
			
			for(e in response){
							
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");			

				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				td4.innerHTML = response[e].childNodes[3].innerHTML;
				td5.innerHTML = response[e].childNodes[4].innerHTML;
				
				//create form
				var form = document.createElement("form");
				
				var formId = document.createAttribute("id");
				formId.value = "View R";
				form.setAttributeNode(formId);
				
				var formAction = document.createAttribute("action");
				formAction.value = "CheckReim";
				form.setAttributeNode(formAction);
				
				var formMethod = document.createAttribute("method");
				formMethod.value = "POST";
				form.setAttributeNode(formMethod);				
				
				//create input
				var input = document.createElement("input");
				input.setAttribute("type","submit");
				input.setAttribute("name","yolo");
				input.setAttribute("value", "View Details");
				
				var hiddenInput = document.createElement("input");
				hiddenInput.setAttribute("type","hidden");
				hiddenInput.setAttribute("name","rid");
				hiddenInput.setAttribute("value", response[e].childNodes[6].innerHTML);
	//			var inputValue = document.createAttribute("value");
	//			formId.value = "View R";
	//			form.setAttributeNode(formId);
	//			var inputName = document.createAttribute("id");
	//			formMethod.value = "POST";
	//			form.setAttributeNode(formMethod);	
				
				//create button
				var button = document.createElement("button");
				var buttonAtt = document.createAttribute("value");
				buttonAtt.value = response[e].childNodes[6].innerHTML;
				button.setAttributeNode(buttonAtt);
				button.innerHTML = "View Details";
				var buttonName = document.createAttribute("name");
				buttonName.value = "Rid";
				button.setAttribute("name","Rid");
		
				//append nodes
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(form);
				form.appendChild(hiddenInput);
				form.appendChild(input);
				
				resultTable.appendChild(row);
			}
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log(xhr.status);
			document.getElementById("results").innerHTML=xhr.status;
		}

	}

}