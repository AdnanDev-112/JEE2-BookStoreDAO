<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="java.util.List"%>
<%@ page import="model.Book"%>
<%@ page import="model.Author"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Book to an Existing AUthor</title>
</head>
<body>

	<%
	if (request.getParameter("cbxAuthor") != null) {
		response.sendRedirect("BookStoreServlet?action=assignBookAuthor&bookID=" + request.getParameter("cbxBook") +  "&authorID=" + request.getParameter("cbxAuthor"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Book> bookList = (List<Book>) session.getAttribute("bookList");
	
	/* @SuppressWarnings("unchecked")
	List<Author> authorList = (List<Author>) session.getAttribute("authorList"); */
	%>

	<em>Total Books :-</em>${fn:length(bookList)}

	<form action="AssignExisting.jsp" method="GET">
		<br /> Select an Author:&nbsp;<select name="cbxAuthor">
			<c:forEach items="${authorList}" var="author">
				<option value="${author.id}">${author.name}</option>
			</c:forEach>
		</select> <br /> Select an Book:&nbsp;<select name="cbxBook">
			<c:forEach items="${bookList}" var="book">
				<option value="${book.id}">${book.title}</option>
			</c:forEach>
		</select> <br /> <br />

		<button type="submit">Assign Book to the Author</button>
	</form>

</body>
</html>