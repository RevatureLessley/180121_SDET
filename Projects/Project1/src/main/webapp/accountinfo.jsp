<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.project1.services.AccountServices" %>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<% 
   String email = (String)session.getAttribute("email");
   String name = AccountServices.getFullName(email);
   String accountStatus = AccountServices.accountStatus(email);
   String join_date = AccountServices.getDate(email);
   double available = AccountServices.getReimbursements(email, "available"); 
   double pending = AccountServices.getReimbursements(email, "pending"); 
   double awarded = AccountServices.getReimbursements(email, "awarded"); 
   double total = AccountServices.getReimbursements(email, "total"); 
 %>
</head>
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
        <li><a href="LogOutServlet"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
	<div class="container" style="width:800px">
  <h1 style="color:white">Account Info</h1>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
           <h2 style="color:#D0DEED">Name: <%= name %> <br></h2>
           <h2 style="color:#D0DEED">Join date:  <%= join_date %> <br></h2>
           <h2 style="color:#D0DEED">Account Type:  <%= accountStatus %> <br></h2>
           <h2 style="color:#D0DEED">Available for reimbursement:  <%= available %> <br></h2>
           <h2 style="color:#D0DEED">Pending reimbursement:  <%= pending %> <br></h2>
           <h2 style="color:#D0DEED">Awarded reimbursements:  <%= awarded %> <br></h2>
      </div>
    </div>
</div>
</body>
</html>