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
<title>Insert a Book with Details</title>
</head>
<body>

	<%
	if (request.getParameter("cbxAuthor") != null) {
		response.sendRedirect("BookStoreServlet?action=insertBookWithDetails&authorID=" + request.getParameter("cbxAuthor") + "&bookName="+ request.getParameter("bookName") + "&textSummary=" + request.getParameter("textSummary"));
	}
	%>

	<%
	@SuppressWarnings("unchecked")
	List<Author> authorList = (List<Author>) session.getAttribute("authorList");
	%>

	<em>Total Authors :-</em>${fn:length(authorList)}

	<form action="InsertBookWithDetails.jsp" method="GET">
	Book Name : <input type="text" name="bookName"/>
	<br/>
		Select an Author:&nbsp; <select name="cbxAuthor" style="width: 200px">
			<c:forEach items="${authorList}" var="author">
				<option value="${author.id}">${author.name}</option>
			</c:forEach>
		</select> <br />
		<br />
		Summary:
		<textarea name="textSummary" rows="7" cols="40"></textarea>
		<br/>
		<button type="submit">Add Book</button>
	</form>

</body>
</html>