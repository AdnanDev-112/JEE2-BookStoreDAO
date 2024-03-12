<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="java.util.List"%>
<%@ page import="model.Author"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books By Author</title>
</head>
<body>

	<%
	if (request.getParameter("cbxAuthor") != null) {
		response.sendRedirect("BookStoreServlet?action=bookByAuthor&authorID=" + request.getParameter("cbxAuthor"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Author> authorList = (List<Author>) session.getAttribute("authorList");
	%>

	<em>Total Authors :-</em>${fn:length(authorList)}

	<form action="ViewAuthorBooks.jsp" method="GET">
		Select an Author: <select name="cbxAuthor">
			<c:forEach items="${authorList}" var="author">
				<option value="${author.id}">${author.name}</option>
			</c:forEach>
		</select> <br />
		<br />
		<button type="submit">View Books</button>
	</form>

</body>
</html>