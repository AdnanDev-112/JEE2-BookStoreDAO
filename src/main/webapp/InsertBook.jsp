<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert a Book</title>
</head>
<body>

	<form method=GET>
	Book Name : <input type="text" name="bookName">
	<br/>
	<button type="submit">Add</button>
	
	</form>
	
	<%
		if(request.getParameter("bookName") != null){
			RequestDispatcher rd = request.getRequestDispatcher("BookStoreServlet?action=insertBook");
			request.setAttribute("action","insertBook");
			request.setAttribute("bookName", request.getParameter("bookName"));
			rd.forward(request,response);
		}
	
	%>

</body>
</html>