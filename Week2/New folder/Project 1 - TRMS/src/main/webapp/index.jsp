<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/css/main.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/javascript/main.js"></script>
<script type="text/javascript" src="resources/javascript/ajax.js"></script>
<title>TRMS</title>
</head>
<%
	session.invalidate();
%>
<body>
	<nav class="navbar navbar-inverse navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">TRMS</a>
			</div>
		</div>
	</nav>
	<div id="maincontainer" class="container">
		<div class="well row">
			<h1 class="text-center">Tuition Reinbursement Management System</h1>
			<hr>
			<div class="text-center col-md-6 col-md-offset-3">
				<h4>Please sign in:</h4>
				<form id="loginForm" action="Login.do" method="POST">
					<table class='form-table' align="center">
						<tr>
							<td><label>Username:</label></td>
							<td><input class="form-control" type="text" name="username"
								id="username" required>
						</tr>
						<tr>
							<td><label>Password:</label></td>
							<td><input class="form-control" type="password"
								name="password" id="password" required>
						</tr>
					</table>
<%-- 					<span class="text-danger"><strong>${response }</strong></span><br> --%>
					<c:if test="${requestScope.invalid == 'true' }">
						<span id="invalidCredential" class="text-danger"><strong>Invalid
								Cretentials.</strong></span>
						<br>
					</c:if>
					<button class="btn sized-btn btn-warning" type="submit">Sign
						in</button>
				</form>
				<hr>
				<h3 class="text-center">- OR -</h3>
				<h4>For first time user, please register:</h4>
				<a href="register.html"><button
						class="btn sized-btn btn-warning" type="button">Register</button></a>
			</div>
		</div>

	</div>

</body>
</html>