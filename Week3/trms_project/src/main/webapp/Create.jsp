<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.revature.beans.Employees"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

<title>Create Reimbursement</title>
</head>
<body style="background-color:#f7fdfa;">
<% Employees emp = (Employees) request.getSession().getAttribute("emp"); %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Tuition Reimbursement</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/trms/EmployeeView">View<span class="sr-only">(current)</span></a>
      </li>
      <% if(emp.isBenCo()) {%>
      <li class="nav-item">
      	<a class="nav-link" href="BenCoView">Approve</a>
      </li>
      <% } else if(emp.isDeptHead()) { %>
      <li class="nav-item">
      	<a class="nav-link" href="DeptHeadView">Approve</a>
      </li>
      <% } else if(emp.isSup()) { %>
      <li class="nav-item">
      	<a class="nav-link" href="SupervisorView">Approve</a>
      </li>
      <% } %>
      <li class="nav-item">
        <a class="nav-link" href="login">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<h1 style="color: #7c795d; font-family: 'Trocchi', serif; font-size: 45px; font-weight: normal; line-height: 48px; margin: 1; margin-left: 0.5cm;">Create Reimbursement</h1>
<form action="Create" method="post" style="width: 250px; margin-left: 1cm">
<div class="form-group">
  Reimbursement Type:
  <select name="rtype" class="form-control">
    <option value="University Course">University Course</option>
    <option value="Seminar">Seminar</option>
    <option value="Certification">Certification</option>
    <option value="Technical Training">Technical Training</option>
    <option value="Other">Other</option>
  </select>
  </div>
  <div class="form-group">
  Cost of Course: 
  <input name="cost" class="form-control" type="number" step="10" placeholder="0.00">
  </div>
  <div class="form-group">
  Description:
  <input name="description" class="form-control" type="text" placeholder="Description">
  </div>
  <div class="form-group">
  Location:
  <input name="location" class="form-control" type="text" placeholder="Loaction">
  </div>
  <div class="form-group">
  Grading Format:
  <select name="grade" class="form-control">
  	<option value="Pass/Fail">Pass/Fail</option>
  	<option value="4.0 Scale">4.0 scale</option>
  </select>
  </div>
  <div class="form-group">
  <button style="box-shadow: rgb(145, 184, 179) 0px 1px 3px 0px inset; background: linear-gradient(rgb(118, 141, 135) 5%, rgb(108, 124, 124) 100%) rgb(118, 141, 135); border-radius: 5px; border: 1px solid rgb(86, 105, 99); display: inline-block; cursor: pointer; color: rgb(255, 255, 255); font-family: Arial; font-size: 15px; font-weight: bold; padding: 7px 15px; text-decoration: none; text-shadow: rgb(43, 102, 94) 0px -1px 0px;" type="submit">Submit</button>
  </div>
</form>
</body>
</html>