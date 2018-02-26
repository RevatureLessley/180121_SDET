<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
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
<title>TRMS - New Application</title>
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
				<h3>Start an Application</h3>
				<hr>
				<div class="well">
					<form id="application_form" action="SubmitForm.do" method="POST">
						<table class="form-table">
							<tr>
								<td><label>First Name:</label></td>
								<td><input class="form-control" type="text"
									name="firstname" id="firstname" size=35 required></td>
							</tr>
							<tr>
								<td><label>Last Name:</label></td>
								<td><input class="form-control" type="text" name="lastname"
									id="lastname" required></td>
							</tr>
							<tr>
								<td><label>E-mail:</label></td>
								<td><input class="form-control" type="email" name="email"
									id="email" required></td>
							</tr>
							<tr>
								<td><label>Address:</label></td>
								<td><input class="form-control" type="text" name="address"
									id="address" required></td>
							</tr>
							<tr>
								<td><label>City:</label></td>
								<td><input class="form-control" type="text" name="city"
									id="city" required></td>
							</tr>
							<tr>
								<td><label>State:</label></td>
								<td><input class="form-control" type="text" name="state"
									id="state" required></td>
							</tr>
							<tr>
								<td><label>Phone Number:</label></td>
								<td><input class="form-control" type="tel" name="tel"
									id="tel" required></td>
							</tr>
						</table>
						<input type="checkbox"
							onchange="fillForm(this,'${sessionScope.username}')"> Use
						profile information
						<hr>
						<table class="form-table">
							<tr>
								<td><label>Event Type:</label></td>
								<td><select class="form-control" name="event_type"
									form="application_form" required>
										<option value="" disabled selected>Choose one</option>
										<option value="university courses">University courses</option>
										<option value="seminars">Seminars</option>
										<option value="certification preparation classes">Certification
											preparation classes</option>
										<option value="certifications">Certifications</option>
										<option value="technical training">Technical training</option>
								</select></td>
							</tr>
							<tr>
								<td><label>Event Date:</label></td>
								<td><input class="form-control" type="date"
									name="event_date" required></td>
							</tr>
							<tr>
								<td><label>Event Location:</label></td>
								<td><input class="form-control" type="text"
									name="event_location" required></td>
							</tr>
							<tr>
								<td><label>Event Cost ($):</label></td>
								<td><input class="form-control" type="number" min=0
									name="event_cost" required></td>
							</tr>
							<tr>
								<td><label>Work-related Justification:</label></td>
								<td><textarea class="form-control" rows="3" cols="35"
										form="application_form" name="relation" required></textarea></td>
							</tr>
							<tr>
								<td><label>Grading Format:</label></td>
								<td><select class="form-control" form="application_form"
									name="grading_format" required>
										<option value="" disabled selected>Choose one</option>
										<option value="presentation">Presentation</option>
										<option value="grade">Grade</option>
								</select></td>
							</tr>
							<tr>
								<td><label>Description (Optional):</label></td>
								<td><textarea class="form-control" rows="5" cols="35"
										form="application_form" name="description"></textarea></td>
							</tr>
						</table>
						<hr>
						<button type="submit" class="btn btn-warning">Submit
							Application</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>