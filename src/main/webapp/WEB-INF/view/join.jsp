<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
</head>
<body>
	<h1>社員を追加</h1>
	
	<form action = "JoinServlet" method = "get">
		<label for="department">部署</label><br>
		<input type="text" name="department"><br> <br>
	    <label for="emproyee">社員名</label><br>
		<input type="text" name="emproyee"><br> <br>
		<label for="password">パスワード</label><br>
		<input type="text" name="password"><br><br>
		<label for="position">役職</label><br>
		<select name="position" value="" id="position">
			<option>Manager</option>
    		<option>Assistant Manager</option>
    		<option>Leader</option>
    		<option>Assistant Leader</option>
    		<option>Assistant</option>
		</select> <br><br>
		<button type="submit">入社</button>
	</form>
	
</body>
</html>