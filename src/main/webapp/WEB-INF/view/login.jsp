<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action = "LoginServlet" method = "post">
		<label for = "id">UserID:</label>
		<input type = "text" name = "id">
		<label for = "password">Password:</label>
		<input type = "text" name = "password">
		<input type = "submit" value = "Login">
	</form>
	
</body>
</html>