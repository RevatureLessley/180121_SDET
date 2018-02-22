<%@page import="com.trms.services.ReimbursementService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href="../resources/css/main.css">

<title>EDIT REIMBURSEMENT</title>
</head>
<body>
	<div class='container'>
		<div class='well' style='margin-top: 50px'>
			<%@page import="com.trms.services.ReimbursementService" %>
			<%@page import="com.trms.beans.Reimbursement" %>
			<%@page import="com.trms.beans.AddedInfo" %>
			<%@page import="java.util.List" %>
			<%
				String s = request.getQueryString();
				int rId = Integer.parseInt(s.split("=")[1]);
				Reimbursement r = ReimbursementService.getReimbursement(rId);
				
			%>
			<% if(r.getAddedInfo().size() != 0) { 
				List<AddedInfo> lai = r.getAddedInfo();
			%>
				<% for(AddedInfo ai : lai) { %>
					<%= ai.getInfoEmpName() %> said <br>
					<%= ai.getInfoMessage() %>
				<% } %>
			<% } %>
		</div>
	</div>

</body>
</html>