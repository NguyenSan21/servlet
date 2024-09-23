<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Player</title>
</head>
<body>

    <a href="Index.jsp">Home</a>|
    <a href="Create.jsp">Create</a>|
    <a href="Display">Display</a>|
    
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            Hello: ${sessionScope.user}
            <a href="LogoutServlet">Sign out</a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp">Login</a>
        </c:otherwise>
    </c:choose>

    <h1>Update book list</h1>
	<form action="Update" method="POST" >
		<table>
			<tr>
				<td><input type="hidden" type="text" id="BookId" name="BookId"
					value="${UpdateBooklist[0]}" /></td>
			</tr>

			<tr>
				<td>Title</td>
				<td><input type="text" id="BookTitle" name="BookTitle"
					value="${UpdateBooklist[1]}" /></td>
			</tr>
			<tr>
				<td>Author</td>
				<td><input type="text" id="BookAuthor" name="BookAuthor"
					value="${UpdateBooklist[2]}" /></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="text" id="BookPrice" name="BookPrice"
					value="${UpdateBooklist[3]}" /></td>
			</tr>
		</table>

		<input type="submit" value="Update">
	</form>

</body>
</html>
