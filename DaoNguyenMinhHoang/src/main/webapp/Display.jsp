<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Player List</title>
</head>
<body>
	<a href="Index.jsp">Home</a>|
	<a href="Create.jsp">Create</a>|
	<a href="Display">Display</a>|
	
	<%
    if (session.getAttribute("user") != null) {
    %>
	Hello:
	<%= session.getAttribute("user") %>
	<a href="LogoutServlet">Sign out</a>
	<%
    } else {
    %>
	<a href="login.jsp">Login</a>
	<%
    }
    %>

	<h1>Book list</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Price</th>
		</tr>

		<c:forEach var="item" begin="0" items="${requestScope.BookList}">
			<tr>
				<td>${item.bookId}</td>
				<td>${item.bookTitle}</td>
				<td>${item.bookAuthor}</td>
				<td>${item.bookPrice}</td>
				<td><a href="Update?BookId=${item.bookId}">Edit</a></td>
				<td><a href="Delete?BookId=${item.bookId}" onclick="return confirm('Are you sure?');">Delete</a></td>

			</tr>
		</c:forEach>

	</table>
</body>
</html>
