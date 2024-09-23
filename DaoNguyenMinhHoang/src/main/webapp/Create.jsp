<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create</title>
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

        <h1>New Player Record</h1>
        <form action="Create">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" id="BookId" name="BookId" /></td>
			</tr>
			<tr>
				<td>Title</td>
				<td><input type="text" id="BookTitle" name="BookTitle" /></td>
			</tr>
			<tr>
				<td>Author</td>
				<td><input type="text" id="BookAuthor" name="BookAuthor" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="text" id="BookPrice" name="BookPrice" /></td>
			</tr>
		</table>
		<input type="submit" value="Create">
            <span style="color:red">${msg}</span>           
        </form>
           

</body>
</html>