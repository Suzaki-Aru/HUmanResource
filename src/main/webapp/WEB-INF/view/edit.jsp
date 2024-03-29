<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>社員編集</title>
<style>
ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

li {
	float: left;
	margin-right: 20px;
}
</style>
</head>
<body>
	<h1>社員編集</h1>
	
	<% String position = (String) session.getAttribute("position"); %>

	<form action="EditServlet" method="get">
		<input type="hidden" name="id" value='<%= request.getAttribute("id") %>'>
		
		<label for="department">部署</label><br>
		<% if (Objects.equals(position, "Leader") || Objects.equals(position, "Assistant Manager") || Objects.equals(position, "Manager")) { %>
		<input type="text" name="department" value='<%= request.getAttribute("department") %>'><br> <br>
		<% } else { %>
		<input type="text" name="department" value='<%= request.getAttribute("department") %>' disabled><br> <br>
		<input type="hidden" name="department" value='<%= request.getAttribute("department") %>'>
		<% } %>
	    
	    <label for="emproyee">社員名</label><br>
		<input type="text" name="emproyee" value='<%= request.getAttribute("emproyee") %>'><br> <br>
		
		<label for="position">役職</label><br>
		<% if (Objects.equals(position, "Leader") || Objects.equals(position, "Assistant Manager") || Objects.equals(position, "Manager")) { %>
		<select name="position" value="" id="position">
			<option <%= request.getAttribute("position").equals("Manager") ? "selected" : "" %>>Manager</option>
    		<option <%= request.getAttribute("position").equals("Assistant Manager") ? "selected" : "" %>>Assistant Manager</option>
    		<option <%= request.getAttribute("position").equals("Leader") ? "selected" : "" %>>Leader</option>
    		<option <%= request.getAttribute("position").equals("Assistant Leader") ? "selected" : "" %>>Assistant Leader</option>
    		<option <%= request.getAttribute("position").equals("Assistant") ? "selected" : "" %>>Assistant</option>
		</select> <br><br>
		<% } else { %>
		<select name="position" value="" id="position" disabled>
			<option <%= request.getAttribute("position").equals("Manager") ? "selected" : "" %>>Manager</option>
    		<option <%= request.getAttribute("position").equals("Assistant Manager") ? "selected" : "" %>>Assistant Manager</option>
    		<option <%= request.getAttribute("position").equals("Leader") ? "selected" : "" %>>Leader</option>
    		<option <%= request.getAttribute("position").equals("Assistant Leader") ? "selected" : "" %>>Assistant Leader</option>
    		<option <%= request.getAttribute("position").equals("Assistant") ? "selected" : "" %>>Assistant</option>
		</select> 
		<input type="hidden" name="position" value='<%= request.getAttribute("position") %>'><br><br>
		<% } %>
		<button type="submit">変更を保存する</button>
	</form><br>
	
	<form action = "DeleteServlet" method = "get">
		<input type="hidden" name="id" value='<%= request.getAttribute("id") %>'>
		<% if (Objects.equals(position, "Leader") || Objects.equals(position, "Assistant Manager") || Objects.equals(position, "Manager")) { %>
			<button type="submit">退社</button>
		<% } %>
	</form><br>
	
	<a href='ListServlet'>戻る</a>
</body>
</html>