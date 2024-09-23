<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search for Session and Cookie in JSP and Servlet</title>
</head>
<style>
#nav {
	width: 1200px;
	margin: auto;
	height: 30px;
}

#content {
	width: 1200px;
	margin: auto;
	height: 500px;
	background-color: gray;
	color: white;
	padding: 20px;
}

</style>
<body>
	<div id="nav">
		<a href="Index.jsp">Home</a>|
		<a href="Create.jsp">Create</a>|		
		<a href="Display">Display</a>|		
		
		<%
		if (session.getAttribute("user") != null) {
		%>
		Hello:${sessionScope.user} <a href="LogoutServlet"> Sign out</a>
		<%
		} else {
		%>
		<a href="login.jsp"> Login</a>
		<%
		}
		%>
	</div>
	<div id="content">
		<h2>Aptech - Batch 183 - Dao Nguyen Minh Hoang</h2>
	</div>
</body>
</html>