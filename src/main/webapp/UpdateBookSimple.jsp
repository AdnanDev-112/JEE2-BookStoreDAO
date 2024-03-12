<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update a Book</title>
</head>
<body>

	<form method=GET>
	Book ID : <input type="text" name="bookID">
	<br/>
	Book Name : <input type="text" name="bookName">
	<br/>
	<button type="submit">Update</button>
	
	<a href="BookStoreServlet?action=showAllBooks">Show All Books</a>
	
	</form>
	
	<%
		if(request.getParameter("bookID") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("BookStoreServlet?action=updateBookSimple");
			request.setAttribute("action","updateBookSimple");
			request.setAttribute("bookID", request.getParameter("bookID"));
			request.setAttribute("bookName", request.getParameter("bookName"));
			rd.forward(request,response);
		}
	
	%>

</body>
</html>