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
<script src="../resources/javascript/ajax_editReimburse.js"></script>

<title>EDIT REIMBURSEMENT</title>
</head>
<body>
	<div class='container'>
		<div class='well' style='margin-top: 50px'>
			<%@page import="com.trms.services.ReimbursementService"%>
			<%@page import="com.trms.beans.Reimbursement"%>
			<%@page import="com.trms.beans.AddedInfo"%>
			<%@page import="java.util.List"%>
			<%
				String s = request.getQueryString();
				int rId = Integer.parseInt(s.split("=")[1]);
				Reimbursement r = ReimbursementService.getReimbursement(rId);
				// TODO make filter so can't access any reimbursement to edit
			%>

			<h3>Reimbursement #<span id='rid'><%=rId%></span></h3>
			<div class='row'>
				<div class='col-sm-8'>
					<label>DESCRIPTION : </label> <%= r.getDescription() %>
				</div>
				<div class='col-sm-2'>
					<label>GRADE : </label><input type='number' step='0.1' min='0' width='8' id='grade' value=<%= r.getGrade() %>>
				</div>
				<div class='col-sm-2'>
					<label>PASS GRADE : </label> <%= r.getPassGrade() %>
				</div>
			</div>
			
			<div class='row'>
				<div class='col-sm-4'>
					<label>EVENT : </label> <%= r.getEventStr() %>
				</div>
				<div class='col-sm-4'>
					<label>LEARNING CENTER : </label> <%= r.getCenterStr() %>
				</div>
				<div class='col-sm-4'>
					<label>GRADING FORMAT : </label> <%= r.getFormatStr() %>
				</div>
			</div>
			
			<div class='row'>
				<div class='col-sm-6'>
					<label>WORK MISSED : </label> <%= r.getWorkDaysMissed() %>
				</div>
				<div class='col-sm-6'>
					<label>JUSTIFICATION : </label> <%= r.getWorkJustification() %>
				</div>
			</div>
			
			<div class='row'>
				<div class='col-sm-4'>
					<label>DATE : </label> <%= r.getDate() %>
				</div>
				<div class='col-sm-4'>
					<label>COST : $</label> <%= r.getCost() %>
				</div>
				<div class='col-sm-4'>
					<label>PROJECTED REIMBURSEMENT : </label> <%= r.getProjectedReimb() %>
				</div>
			</div>
			
			<%
				if (r.getAddedInfo().size() != 0) {
					List<AddedInfo> lai = r.getAddedInfo();
			%>
				<h4>ADDITIONAL INFO</h4>
				<%
					for (AddedInfo ai : lai) {
				%>
					<label>AUTHOR : </label> <%=ai.getInfoEmpName()%>
					<br>
					<label>MESSAGE : </label> <%=ai.getInfoMessage()%>
					<hr>
			<%
					}
				}
			%>
			
			<% if(r.getNextInfoReq() == (Integer)session.getAttribute("empid")) { %>
				<label>PLEASE ENTER ADDITIONAL INFO</label>
				<input type='text' id='addinfo'>
			<% } %>
			
			<% if(r.getNextApprovalId() == (Integer)session.getAttribute("empid")) {%>
				<label>REQUEST ADDITIONAL INFO</label>
				<input type='text' id='reqinfo'>
				<select>
					<option value=<%= r.getEmpId() %>>REQUESTOR</option>
				</select>
			<% } %>
			
			<button type='button' id='save' onclick="readPageChanges()">SAVE</button>
		</div>
	</div>

</body>
</html>