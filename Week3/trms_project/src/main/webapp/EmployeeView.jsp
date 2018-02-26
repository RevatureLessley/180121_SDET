<%@ page import="java.util.List" %>
<%@ page import="com.revature.beans.Reimbursements"%>
<%@ page import="com.revature.beans.Employees"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<link rel="stylesheet" href="table.css">

<title>EmployeeView</title>
</head>
<body>
<script src=view.js></script>
<% Employees emp = (Employees) request.getSession().getAttribute("emp"); %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Tuition Reimbursement</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/trms/Create">Create<span class="sr-only">(current)</span></a>
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
<h1 style="color: #7c795d; font-family: 'Trocchi', serif; font-size: 45px; font-weight: normal; line-height: 48px; margin: 1; margin-left: 0.5cm;">Hello <%= emp.getFirstname() %> <%= emp.getLastname()%></h1>
<% List<Reimbursements> reimb = (List<Reimbursements>) request.getSession().getAttribute("reimbursements");
	if(reimb.size() != 0) {
%>
<table id="ReimbursementTable" border="1">
	<tbody>
		<tr>
			<td>ReimbursementID</td>
			<td>EmployeeId</td>
			<td>Reimbursement</td>
			<td>Cost</td>
			<td>Type</td>
			<td>Description</td>
			<td>Status</td>
			<td>Delete</td>
		</tr>
		<% for(int i = 0; i < reimb.size(); i++){ %>
		<tr>
			<td><%= reimb.get(i).getRid() %></td>
			<td><%= reimb.get(i).getEid()%></td>
			<td><%= reimb.get(i).getCost()%></td>
			<td><%= reimb.get(i).getReimbursement() %></td>
			<td><%= reimb.get(i).getRtype()%></td>
			<td><%= reimb.get(i).getDescription() %></td>
			<% if(reimb.get(i).getStatus().equals("Pending")) {%>
			<td style="color: #ffcc00" align="center"><%= reimb.get(i).getStatus() %> </td>
			<% } else if(reimb.get(i).getStatus().equals("Approved")) {%>	
			<td style="color: #14cc14" align="center"><%= reimb.get(i).getStatus() %> </td>
			<% } else if(reimb.get(i).getStatus().equals("Denied")) {%>	
			<td style="color: #ED4337;" align="center"><%= reimb.get(i).getStatus() %> </td>
			<% } %>
			<td><button id="deleteButton<%=reimb.get(i).getRid()%>" type="button" onclick='deleteReimb(<%=reimb.get(i).getRid()%>)'>Delete</button>
		</tr> 
		<% } %>	
	</tbody>
</table>
<% } else { %>
<p class="noreimbursements" style="margin-left: 1cm">You have not submitted any Reimbursements click the create tab above to create a new one<p>
<% } %>
</body>
</html>