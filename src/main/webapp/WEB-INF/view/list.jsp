<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Todoリスト</title>
</head>
<body>

	<%
	ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>) session.getAttribute("rows");
	%>

	<table border="2">

		<tr>
			<th>部署</th>
			<th>社員名</th>
			<th>役職</th>
			<th>入社日</th>
		</tr>
		<%
		for (HashMap<String, String> columns : rows) {
		%>
		<tr>
			<form action="DetailServlet" method="post">
			<input type = "hidden" name = id value = '<%=columns.get("id")%>'>
			<td><%=columns.get("department")%></td><input type = "hidden" name = department value = '<%=columns.get("department")%>'>
			<td><%=columns.get("emproyee")%></td><input type = "hidden" name = emproyee value = '<%=columns.get("emproyee")%>'>
			<td><%=columns.get("position")%></td><input type = "hidden" name = position value = '<%=columns.get("position")%>'>
			<td><%=columns.get("joined")%></td><input type = "hidden" name = joined value = '<%=columns.get("joined")%>'>
			<td><input type="submit" value="編集"></td>
			</form>
		</tr>
		<%
		}
		%>
	</table>
	<% String position_param = (String) session.getAttribute("position_param"); %>
	<% if (Objects.equals(position_param, "Leader") || Objects.equals(position_param, "Assistant Manager") || Objects.equals(position_param, "Manager")) { %>
			<p><a href="TServlert">社員を追加</a></p>
	<% } %>
	<br>
	<br>
	<form method="POST" action="LogoutServlet">
		<input type="submit" value="Logout">
	</form>
</body>
</html>
