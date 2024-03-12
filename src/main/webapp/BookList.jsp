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
<title>Books List</title>
</head>
<body>

	<%
	if (request.getParameter("cbxBook") != null) {
		response.sendRedirect("BookStoreServlet?action=bookInfo&bookID=" + request.getParameter("cbxBook"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Book> bookList = (List<Book>) session.getAttribute("bookList");
	%>

	<em>Total Books :-</em>${fn:length(bookList)}

	<form action="BookList.jsp" method="GET">
		Select a Book: <select name="cbxBook">
			<c:forEach items="${bookList}" var="book">
				<option value="${book.id}">${book.title}</option>
			</c:forEach>
		</select> <br />
		<br />
		<button type="submit">View Book</button>
	</form>

</body>
</html>