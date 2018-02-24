<%@page import="com.trms.services.EmployeeService"%>
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
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">TRMS</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">Page 1</a></li>
					<li><a href="#">Page 2</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right" id='user-options'>

					<li><a href="Logout.do"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</nav>

		<div class='well' style='margin-top: 50px'>
			<%@page import="com.trms.services.ReimbursementService"%>
			<%@page import="com.trms.services.EmployeeService"%>
			<%@page import="com.trms.beans.Reimbursement"%>
			<%@page import="com.trms.beans.AddedInfo"%>
			<%@page import="com.trms.beans.Employee"%>
			<%@page import="java.util.List"%>
			<%
				int empId = (Integer) session.getAttribute("empid");
				String s = request.getQueryString();
				int rId = Integer.parseInt(s.split("=")[1]);
				Reimbursement r = ReimbursementService.getReimbursement(rId);
				Employee e = EmployeeService.getUserEmpId(r.getEmpId());
				Employee user = EmployeeService.getUserEmpId(empId);
				if (r.getDescription() == null) {
					r.setDescription("N/A");
				}
				// TODO make filter so can't access any reimbursement to edit
			%>

			<h3>
				Reimbursement #<span id='rid'><%=rId%></span>
				<%
					if (r.getUrgent() == 1) {
				%>
				<span style='color: red'><strong>!</strong></span>
				<%
					}
					// TODO also display urgencey on all reimbursements displayed page
				%>
			</h3>
			<div class='row'>
				<div class='col-sm-12'>
					<table class='table-bordered' id="empinfo">
						<tr>
							<th>FIRST NAME</th>
							<th>LAST NAME</th>
							<th>DEPARTMENT</th>
							<th>TITLE</th>
							<th>AVAILABLE</th>
							<th>ADDRESS</th>
							<th>CITY</th>
							<th>STATE</th>
							<th>ZIP CODE</th>
						</tr>
						<tr>
							<td><%=e.getFname()%></td>
							<td><%=e.getLname()%></td>
							<td><%=e.getDepartment()%></td>
							<td><%=e.getTitle()%></td>
							<td id="availreimb"><%=e.getAvailReimburse()%></td>
							<td><%=e.getAddr()%></td>
							<td><%=e.getCity()%></td>
							<td><%=e.getState()%></td>
							<td><%=e.getZipCode()%></td>
						</tr>
					</table>
				</div>
			</div>
			<br>
			<div class='row'>
				<div class='col-sm-6'>
					<label>DESCRIPTION : </label>
					<%=r.getDescription()%>
				</div>
				<div class='col-sm-4'>
					<label>GRADE : </label><input type='number' step='0.1' min='0'
						width='8' id='grade' value=<%=r.getGrade()%>>
				</div>
				<div class='col-sm-2'>
					<label>PASS GRADE : </label>
					<%=r.getPassGrade()%>
				</div>
			</div>
			<br>
			<div class='row'>
				<div class='col-sm-4'>
					<label>EVENT : </label>
					<%=r.getEventStr()%>
				</div>
				<div class='col-sm-4'>
					<label>LEARNING CENTER : </label>
					<%=r.getCenterStr()%>
				</div>
				<div class='col-sm-4'>
					<label>GRADING FORMAT : </label>
					<%=r.getFormatStr()%>
				</div>
			</div>

			<div class='row'>
				<div class='col-sm-6'>
					<label>WORK MISSED : </label>
					<%=r.getWorkDaysMissed()%>
				</div>
				<div class='col-sm-6'>
					<label>JUSTIFICATION : </label>
					<%=r.getWorkJustification()%>
				</div>
			</div>

			<div class='row'>
				<div class='col-sm-4'>
					<label>DATE : </label>
					<%=r.getDate()%>
				</div>
				<div class='col-sm-4'>
					<label>COST : $</label>
					<%=r.getCost()%>
				</div>
				<div class='col-sm-4'>
					<label>PROJECTED REIMBURSEMENT : </label>
					<%
						if (user.getTitle().equals("BENEFITS COORDINATOR")) {
					%>
					<input type='number' step='0.01' min='0'
						value=<%=r.getProjectedReimb()%> id='projreimb'>
					<%
						} else {
					%>
					<%=r.getProjectedReimb()%>
					<%
						}
					%>

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
			<label>AUTHOR : </label>
			<%=ai.getInfoEmpName()%>
			<br> <label>MESSAGE : </label>
			<%=ai.getInfoMessage()%>
			<hr>
			<%
				}
				}
			%>

			<%
				if (r.getNextInfoReq() == (Integer) session.getAttribute("empid")) {
			%>
			<label>PLEASE ENTER ADDITIONAL INFO</label> <input type='text'
				id='addinfo'>
			<%
				}
			%>

			<%
				if (r.getNextApprovalId() == (Integer) session.getAttribute("empid")) {
			%>
			<br>
			<div class='row'>
				<div class='col-sm-6'>
					<label>REQUEST ADDITIONAL INFO</label>
					<div>
						<input type='text' id='reqinfo'> <select id='reqinfoemp'>
							<option value=<%=r.getEmpId()%>>REQUESTOR</option>
						</select>
					</div>
				</div>

				<%
					if (user.getTitle().equals("BENEFITS COORDINATOR")) {
				%>
				<div class='col-sm-3' id='awardiv'>
					<label for="award">AWARD AMOUNT</label> <select
						class="form-control" name="award" id="award" required>
						<option value=-1>---</option>
						<option value=1>YES</option>
						<option value=0>NO</option>
					</select>
				</div>
				<%
					}
				%>

				<!-- Approve/Deny Request -->
				<div class='col-sm-3' id='resdiv'>
					<label for="response">RESPONSE</label> <select class="form-control"
						name="response" id="response" onchange="denyRe()" required>
						<option value=-1>---</option>
						<option value=1>APPROVE</option>
						<option value=0>DENY</option>
					</select>

				</div>
			</div>

			<%
				}
			%>
			<%
				if (r.getEmpId() == user.getId()) {
			%>
					<label for="inputFile">ADDITIONAL ATTACHMENTS</label> <input
						type="file" class="form-control-file" id="inputFile"
						name='inputFiles'
						accept=".png, .pdf, .jpeg, .jpg, .txt, .doc, .docx, .msg"
						multiple>
						
					<label for='formattype'>ATTACHMENT TYPE : </label>
						<label class="radio-inline"><input type="radio"
								name="formattype" value="EXAMINATION">EXAMINATION</label> <label
								class="radio-inline"><input type="radio"
								name="formattype" value="PRESENTATION">PRESENTATION</label>
								
			<%
				}
			%>
			<hr>
			<button type='button' id='back' onclick="goBack()">BACK</button>
			<button type='button' id='save' onclick="readPageChanges()">SAVE</button>
		</div>
	</div>

</body>
</html>