<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert a Author</title>
</head>
<body>

	<form action="InsertAuthor.jsp" method=GET>
	Author Name : <input type="text" name="authorName">
	<br/>
	Country Name : <input type="text" name="countryName">
	<br/>
	<button type="submit">Add</button>
	
	</form>
	
	<%
		if(request.getParameter("authorName") != null){
			RequestDispatcher rd = request.getRequestDispatcher("BookStoreServlet?action=insertAuthor");
			request.setAttribute("action","insertBook");
			request.setAttribute("authorName", request.getParameter("authorName"));
			request.setAttribute("countryName", request.getParameter("countryName"));
			rd.forward(request,response);
		}
	
	%>

</body>
</html>