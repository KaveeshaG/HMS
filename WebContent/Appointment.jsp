<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Management</title>
</head>
<body>

	<h1>Appointment Management</h1>
	<form method="post" action="Appointment.jsp">
			Patient Name  :  <input name="patientName" type="text"><br><br> 
			Patient Email :   <input name="patientEmail" type="text"><br><br> 
			Doctor Name   :   <input name="doctorName" type="text"><br><br>     
			Reason        :   <input name="reason" type="text"><br><br> 
			
								 
			 <input name="btnSubmit" type="submit" value="Submit"><br><br>
								 
								 				 
								 									
	</form>
	
	<br>
	<table border="1">
		<tr>
			<th>Patient Name</th>
			<th>Patient Email</th>
			<th>Doctor Name</th>
			<th>Reason</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<tr>
			<td>
				<%
					out.print(session.getAttribute("patientName"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("patientEmail"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("doctorName"));
				%>
			</td>
			<td>
				<%
					out.print(session.getAttribute("reason"));
				%>
			</td>
		
			<td><input name="btnUpdate" type="button" value="Update"></td>
			<td><input name="btnDelete" type="button" value="Delete"></td>
		</tr>
	</table>
	

</body>
</html>