<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trms.services.FormServices"%>
<%@ page import="com.trms.beans.Application"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../resources/css/main.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/javascript/main.js"></script>
<script type="text/javascript" src="../resources/javascript/ajax.js"></script>
<title>TRMS - Application Detail</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-inverse navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="home.jsp">TRMS</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="home.jsp">Home</a></li>
					</ul>
					<ul class="pull-right nav navbar-nav">
						<li><a href="../index.jsp"><span
								class="glyphicon glyphicon-log-out"></span>Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>
		<div class="container" id="maincontainer">
			<div class="col-md-3 left-nav">
				<ul class="nav nav-bar nav-stacked">
					<c:choose>
						<%-- admin leftnav--%>
						<c:when test="${sessionScope.username == 'admin'}">
							<li><label>Manage</label>
								<ul class="nav nav-bar nav-stacked">
									<li><a href="manageUser.jsp">Manager Users</a></li>
									<li><a href="viewAll.jsp">View All Applications</a></li>
								</ul></li>
							<li><label>Help</label>
								<ul class="nav nav-bar nav-stacked">
									<li><a href="#">About TRMS</a></li>
									<li><a href="#">Reinbursement process</a></li>
								</ul></li>
						</c:when>
						<%-- user leftnav --%>
						<c:otherwise>
							<li><label class="">Application</label>
								<ul class="nav nav-bar nav-stacked">
									<li><a href="newApplication.jsp">Start an Application</a></li>
									<li><a href="applicationHistory.jsp">Application
											History</a></li>
									<li><a href="manageApplication.jsp">Manage
											Applications</a></li>
								</ul></li>
							<li><label>Profile</label>
								<ul class="nav nav-bar nav-stacked">
									<li><a href="userInfo.jsp">View/Update personal
											information</a></li>
									<li><a href="#">Change password</a></li>
								</ul></li>
							<li><label>Help</label>
								<ul class="nav nav-bar nav-stacked">
									<li><a href="#">About TRMS</a></li>
									<li><a href="#">Reinbursement process</a></li>
								</ul></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

			<div class="col-md-9">
				<%
					long appId = Long.parseLong(request.getParameter("appId"));
					Application app = FormServices.getFormByAppId(appId);
					pageContext.setAttribute("app", app);
				%>
				<h3>Application Detail</h3>
				<hr>
				<table class="table-bordered">
					<tr>
						<td><label>Application ID: </label></td>
						<td>${app.appId}</td>
					</tr>
					<tr>
						<td><label>Submit Date: </label></td>
						<td>${app.submitDate}</td>
					</tr>
					<tr>
						<td><label>Status: </label></td>
						<td>${app.status}</td>
					</tr>
					<tr>
						<td><label>Applicant: </label></td>
						<td>${app.firstname} ${app.lastname}</td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td>${app.email }</td>
					</tr>
					<tr>
						<td><label>Address: </label></td>
						<td>${app.address }</td>
					</tr>
					<tr>
						<td><label>City: </label></td>
						<td>${app.city }</td>
					</tr>
					<tr>
						<td><label>State: </label></td>
						<td>${app.state }</td>
					</tr>
					<tr>
						<td><label>Event Date: </label></td>
						<td>${app.eventDate}</td>
					</tr>
					<tr>
						<td><label>Event Location: </label></td>
						<td>${app.location }</td>
					</tr>
					<tr>
						<td><label>Event Type: </label></td>
						<td>${app.type}</td>
					</tr>
					<tr>
						<td><label>Work-related Justification: </label></td>
						<td>${app.relation}</td>
					</tr>
					<tr>
						<td><label>Cost: </label></td>
						<td>$${app.cost}</td>
					</tr>
					<tr>
						<td><label>Grading Format: </label></td>
						<td>${app.gradingFormat }</td>
					</tr>
					<tr>
						<td><label>Description: </label></td>
						<td>${app.description}</td>
					</tr>
					<tr>
						<td><label>Supervisor Approval: </label></td>
						<td>${app.svDecision }</td>
					</tr>
					<tr>
						<td><label>Supervisor Comment: </label></td>
						<td>${app.svComment}</td>
					</tr>
					<tr>
						<td><label>Department Head Approval: </label></td>
						<td>${app.dhDecision }</td>
					</tr>
					<tr>
						<td><label>Department Head Comment: </label></td>
						<td>${app.dhComment}</td>
					</tr>
					<tr>
						<td><label>BenCo Approval: </label></td>
						<td>${app.bcDecision }</td>
					</tr>
					<tr>
						<td><label>BenCo Comment: </label></td>
						<td>${app.bcComment}</td>
					</tr>
				</table>
				<hr>
				<button type=button class="btn btn-md btn-secondary" onclick="history.back()">Back</button>
			</div>
		</div>
	</div>
</body>
</html>