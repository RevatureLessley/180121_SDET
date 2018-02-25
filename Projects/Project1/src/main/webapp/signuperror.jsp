<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	
<link rel="stylesheet" type="text/css" href="stylesheets/styles.css">
</head>
<title>Error</title>
<body style="background-color:#2C3A50">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">RRTech</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Info<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">FAQ</a></li>
            <li><a href="#">Events</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="signup.html"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="signin.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container" style="width:500px">
  <h2 style="color:white">Error registering your account. Try again:</h2>
  <form data-toggle="validator" role="form" method="POST" action="signup.do">
  <div class="form-group">
    <label for="firstname" class="control-label" style="color:white">First name</label>
    <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First name" required>
  </div>
  <div class="form-group">
    <label for="lastname" class="control-label" style="color:white">Last name</label>
    <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last name" required>
  </div>
  <div class="form-group">
    <label for="address" class="control-label" style="color:white">Address</label>
    <input type="text" class="form-control" id="address" name="address" placeholder="Address" required>
  </div>
  <div class="radio">
  		<label style="color:white"><input type="radio" name="radio" value="0">Regular account</label>
	</div>
	<div class="radio">
  		<label style="color:white"><input type="radio" name="radio" value="1">Supervisor</label>
	</div>
	<div class="radio">
  		<label style="color:white"><input type="radio" name="radio" value="2">Department Head</label>
	</div>
  <div class="form-group">
    <label for="inputEmail" class="control-label" style="color:white">Email</label>
    <input type="email" class="form-control" name="email" placeholder="Email" data-error="invalid email" required>
    <div class="help-block with-errors"></div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="control-label" style="color:white">Password</label>
    <div class="form-inline row">
      <div class="form-group col-sm-6">
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
      </div>
      <div class="form-group col-sm-6">
        <input type="password" class="form-control" id="confirm" name="confirm" placeholder="Confirm" required>
      </div>
    </div>
  </div>
  <div class="form-group">
    <button type="submit" onclick="getName()" class="btn btn-default">Submit</button>
  </div>
</form>
</div>
	<div class="container" style="width:500px">
		<div class="well" id="dtheme">
			<h1 style="color:white">Already have an account?</h1>
			<h2><a style="color:#A1BFDE" href="signin.html">Sign in here!</a></h2>
		</div>
	</div>
</body>
</html>