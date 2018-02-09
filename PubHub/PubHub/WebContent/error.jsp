	<%@ page isErrorPage="true" %>
	<!-- Header content -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!-- Content for the body -->
	<header>
	  <div class="container">
	
		<%
			//display message if present
			out.println("<p class=\"alert alert-danger\">Something went wrong</p>");
		%>
		
		<h1>PUBHUB <small>error page</small></h1>
		<hr class="book-primary">
		<p>
		  Your code threw an exception <i class="fa fa-frown-o fa-3"></i>
		</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>

	  </div>
	</header>

	<!-- Additional Facilities -->
	<jsp:include page="footer.jsp" />
