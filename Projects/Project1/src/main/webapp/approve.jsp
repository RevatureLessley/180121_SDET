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
<title>RRTech Home</title>
<body style="background-color:#2C3A50">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="index.html">RRTech</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.html">Home</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="signup.html"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container" style="width:500px">
  <h2 style="color:white">Sign into your account</h2>
  <form class="form-horizontal" method="POST" action="LoginServlet">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email" style="color:white">Email:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" placeholder="Enter email" name="email">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="password" style="color:white">Password:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" placeholder="Enter password" name="password">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Log in</button>
      </div>
    </div>
  </form>
</div>
<div style="width:500px" class="container-fluid">
		<div class="well" id="dtheme">
			<h1 style="color:white">Don't have an account?</h1>
			<h2><a style="color:#A1BFDE" href="signup.html">Click here to create one!</a></h2>
		</div>
</div>
</body>
</html>