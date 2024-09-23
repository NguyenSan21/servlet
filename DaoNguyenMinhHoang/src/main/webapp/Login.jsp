<%@page import="java.awt.Checkbox"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>

	<form method="POST" action="Login">
		<h2>Username</h2>
		<input type ="text" name="user" value="${cookie.user.value}">
		<br>
		<h2>Password</h2>
		<input type ="password" name="password" value="${cookie.password.value}">
		<br>
		<input type="checkbox" name="remember" checked="checked" >Renember Me
		<button>Summit</button>

	</form>

</body>
</html>