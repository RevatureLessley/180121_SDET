
<!-- Header -->
<jsp:include page="header.jsp" />

<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

		<h1>
			PUBHUB <small>Tag Publishing</small>
		</h1>
		<hr class="book-primary">

		<table
			class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>
					<td>Book Name</td>
					<td>ISBN-13:</td>
					<td>Tag:</td>
					<td>Delete</td>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="tag" items="${tags}">
					<c:set var="bookTitle" value="No Book" />
					<c:forEach var="book" items="${books}">
						<c:choose>
							<c:when test="${tag.getIsbn13().equals(book.getIsbn13()) }">
								<c:set var="bookTitle" value="${book.getTitle() }" />
							</c:when>
						</c:choose>

					</c:forEach>
					<tr>
						<td><c:out value="${bookTitle }" /></td>
						<td><c:out value="${tag.isbn13}" /></td>
						<td><c:out value="${tag.tag}" /></td>
						<td>
							<form action="DeleteTag" method="post">
								<input type="hidden" name="isbn13" value="${tag.isbn13 }" /> 
								<input type="hidden" name="tag" value="${tag.tag }" />
								<button type="submit">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				
				
			</tbody>
		</table>

	</div>
</header>

<!-- Footer -->
<jsp:include page="footer.jsp" />