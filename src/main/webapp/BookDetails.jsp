<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="model.Book"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Information</title>
</head>
<body>

	<%
	Book bookInfo = (Book) session.getAttribute("bookInfo");
	%>

	<strong>Book Title:</strong>
	<%=bookInfo.getTitle()%>

	<br />
	<strong>Book Summary</strong>

	<p>
		<c:choose>
			<c:when test="${bookInfo.getBooksummaries() == null || fn:length(bookInfo.getBooksummaries()) == 0}">
			
				<c:out value="No summary" />
			</c:when>
			<c:otherwise>
				
				<c:forEach var="summary" items="${bookInfo.getBooksummaries()}">
    <c:out value="${summary.summary}" />
</c:forEach>
				
			</c:otherwise>

		</c:choose>

	</p>

	<br />
	<strong>Book Comments: <c:choose>
			<c:when test="${bookInfo.getBookcomments() == null }">
				<c:out value="0" />
			</c:when>
			<c:otherwise>
				<c:out value="${fn:length(bookInfo.getBookcomments())}" />
			</c:otherwise>

		</c:choose>

	</strong>

	<br />

	<table border="1" style="text-align: center;">
		<thead>
			<tr>
				<th>ID</th>
				<th>Comment</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="comment" items="${bookInfo.getBookcomments()}">
				<tr>
					<td><c:out value="${comment.id}" /></td>
					<td><c:out value="${comment.comment}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />

	<strong>Author <c:choose>
			<c:when test="${bookInfo.getAuthors() == null }">
				<c:out value="0" />
			</c:when>
			<c:otherwise>
				<c:out value="${fn:length(bookInfo.getAuthors())}" />
			</c:otherwise>

		</c:choose>
	</strong>

	<table border="1" style="text-align: center;">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${bookInfo.getAuthors() != null}">
				<c:forEach var="author" items="${bookInfo.getAuthors()}">
					<tr>
						<td><c:out value="${author.id}" /></td>
						<td><c:out value="${author.name}" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<a href="/BookStoreDAO">Home</a>




</body>
</html>