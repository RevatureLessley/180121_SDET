<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trms.services.FormServices"%>
<%@ page import="com.trms.beans.Application"%>
<%@ page import="java.util.List"%>
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
<title>TRMS - Application History</title>
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
		List<Application> apps = FormServices.getFormsByUsername(username);
		for (Application a : apps) {
			System.out.println(a);
		}
		pageContext.setAttribute("apps", apps);
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
				<h3>Application History</h3>
				<hr>
				<div class="">
					<c:choose>
						<c:when test="${apps} == null">
							No application History.
						</c:when>
						<c:otherwise>
							<table class="table table-hover" style="width: 100%">
								<tr>
									<th>Application ID</th>
									<th>Date Submitted</th>
									<th>Type</th>
									<th>Cost</th>
									<th>Status</th>
								</tr>
								<c:forEach items="${apps}" var="app">
									<tr onclick="viewApplicationDetail(${app.appId})">
										<td>${app.appId}</td>
										<td>${app.submitDate}</td>
										<td>${app.type}</td>
										<td>${app.cost}</td>
										<td>${app.status}</td>
									</tr>
								</c:forEach>
							</table>


						</c:otherwise>
					</c:choose>



				</div>
			</div>
		</div>
	</div>
</body>
</html>