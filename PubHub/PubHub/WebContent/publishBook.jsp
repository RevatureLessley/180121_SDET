	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<!-- Spring includes -->
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Publish</small></h1>
		<hr class="book-primary">

		<!-- NOTE: This form uses the enctype="multipart/form-data" attribute because it contains a file upload control (<input type="file" ... />).
				To support this special enctype, the PublishBookServlet also has the @MultiPartConfig annotation. You only need to use this
				enctype and its corresponding annotation if you need to use a file upload control. Do not use it otherwise. -->
				
		<form:form action="PublishBookController2" method="post" class="form-horizontal" enctype="multipart/form-data" modelAttribute="book">
		  <div class="form-group">
		    <form:label path="isbn13" for="isbn13" class="col-sm-4 control-label">ISBN 13</form:label>
		    <div class="col-sm-5">
		      <form:input path="isbn13" type="text" class="form-control" id="isbn13" name="isbn13" placeholder="ISBN 13" required="required" value="${param.isbn13 }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <form:label path="title" for="title" class="col-sm-4 control-label">Title</form:label>
		    <div class="col-sm-5">
		      <form:input path="title" type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${param.title }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <form:label path="author" for="author" class="col-sm-4 control-label">Author</form:label>
		    <div class="col-sm-5">
		      <form:input path="author" type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${param.author }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <form:label path="price" for="price" class="col-sm-4 control-label">Price</form:label>
		    <div class="col-sm-5">
		      <form:input path="price" type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${param.price }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <form:label path="content" for="content" class="col-sm-4 control-label">Content</form:label>
		    <div class="col-sm-5">
		      <form:input path="content" type="file" class="form-control" id="content" name="content" placeholder="Content" required="required" value="${param.content }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Publish</button>
		    </div>
		  </div>
		</form:form>	

	  </div>
	</header>



	<!-- Footer -->
	<jsp:include page="footer.jsp" />
	