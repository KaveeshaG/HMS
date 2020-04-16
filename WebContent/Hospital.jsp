<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management System</title>
</head>
<body>
<h1>Items Management</h1>
	<form method="post" action="items.jsp">
		Hospital code: <input name="hid" type="text"><br> Item
		Hospital Name: <input name="hospitalName" type="text"><br> Item
		Hospital Address: <input name="hospitalAddress" type="text"><br> Item
		Hospital Contact: <input name="hospitalContact" type="text"><br> 
		Hospital Email: <input name="hospitalEmail" type="text"><br> 
		<input name="btnSubmit" type="submit" value="Save">
	</form>
	<br>
	<table border="1">
		<tr>
			<th>Hospital Code</th>
			<th>Hospital Name</th>
			<th>Hospital Address</th>
			<th>Hospital Contact</th>
			<th>Hospital Email</th>
			<th>Update</th>
			<th>Remove</th>
		</tr>
		<tr>
			<td>
				<%
					out.print(session.getAttribute("hid"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("hospitalName"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("hospitalAddress"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("hospitalContact"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("hospitalEmail"));
				%>
			</td>
			<td><input name="btnUpdate" type="button" value="Update"></td>
			<td><input name="btnRemove" type="button" value="Remove"></td>
		</tr>
	</table>
</body>
</html>