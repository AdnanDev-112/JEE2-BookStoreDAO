<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="java.util.List"%>
<%@ page import="model.Book"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert a Comment to Book</title>
</head>
<body>

	<%
	if (request.getParameter("cbxBook") != null) {
		response.sendRedirect("BookStoreServlet?action=insertCommentToBook&bookID=" + request.getParameter("cbxBook") +  "&textComment=" + request.getParameter("textComment"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Book> bookList = (List<Book>) session.getAttribute("bookList");
	%>

	<em>Total Books :-</em>${fn:length(bookList)}

	<form action="AddBookComment.jsp" method="GET">
	<br/>
		Select an Book:&nbsp;<select name="cbxBook">
			<c:forEach items="${bookList}" var="book">
				<option value="${book.id}">${book.title}</option>
			</c:forEach>
		</select> <br />
		<br />
		Comment:
		<textarea name="textComment" rows="7" cols="40"></textarea>
		<br/>
		<button type="submit">Add Book</button>
	</form>

</body>
</html>