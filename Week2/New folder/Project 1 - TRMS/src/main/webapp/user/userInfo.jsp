<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="com.trms.services.EmployeeService"%>
<%@ page import="com.trms.beans.Employee"%>
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
<title>TRMS - Personal Info</title>
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
		Employee emp = EmployeeService.getEmpByUsername(username);
		pageContext.setAttribute("firstname", emp.getFname());
		pageContext.setAttribute("lastname", emp.getLname());
		pageContext.setAttribute("email", emp.getEmail());
		pageContext.setAttribute("address", emp.getAddress());
		pageContext.setAttribute("city", emp.getCity());
		pageContext.setAttribute("state", emp.getState());
		pageContext.setAttribute("tel", emp.getTel());
		pageContext.setAttribute("emp_id", emp.getEmpId());
		pageContext.setAttribute("title", emp.getTitle());
		pageContext.setAttribute("supervisor", emp.getSupervisor());
	%>
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
					<li><label class="">Application</label>
						<ul class="nav nav-bar nav-stacked">
							<li><a href="newApplication.jsp">Start an Application</a></li>
							<li><a href="applicationHistory.jsp">Application History</a></li>
							<li><a href="manageApplication.jsp">Manage Applications</a></li>
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
				</ul>
			</div>
			<div class="col-md-9">
				<h3>Personal Information</h3>
				<hr>
				<div class="well">
					<form id="emp_info_form">
						<table class="form-table">
							<tr>
								<td><label>Employee ID:</label></td>
								<td><span>${emp_id}</span></td>
							</tr>
							<tr>
								<td><label>Username:</label></td>
								<td><span>${username}</span></td>
							</tr>
							<tr>
								<td><label>Title:</label></td>
								<td><span>${title}</span></td>
							</tr>
							<tr>
								<td><label>Supervisor:</label></td>
								<td><span>${supervisor}</span></td>
							</tr>
							<tr>
								<td><label>First Name:</label></td>
								<td><input class="form-control" type="text"
									name="firstname" value="${firstname}" size=35 required></td>
							</tr>
							<tr>
								<td><label>Last Name:</label></td>
								<td><input class="form-control" type="text" name="lastname"
									value="${lastname}" required></td>
							</tr>
							<tr>
								<td><label>E-mail:</label></td>
								<td><input class="form-control" type="email" name="email"
									value="${email}" required></td>
							</tr>
							<tr>
								<td><label>Address:</label></td>
								<td><input class="form-control" type="text" name="address"
									value="${address}"></td>
							</tr>
							<tr>
								<td><label>City:</label></td>
								<td><input class="form-control" type="text" name="city"
									value="${city}"></td>
							</tr>
							<tr>
								<td><label>State:</label></td>
								<td><input class="form-control" type="text" name="state"
									value="${state}"></td>
							</tr>
							<tr>
								<td><label>Phone Number:</label></td>
								<td><input class="form-control" type="tel" name="tel"
									value="${tel}"></td>
							</tr>
						</table>
						<hr>
						<button type="button" class="btn btn-warning"
							onclick="updateEmp('${sessionScope.username}')">Update</button>
						<button type="reset" class="btn btn-secondary">Reset</button>
					</form>
					<div id="updateMessage"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>