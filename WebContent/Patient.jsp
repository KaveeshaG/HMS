<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Management</title> 
</head>
<body>
      
      <h1>Patient Management</h1> 
       <form method="post" action="Patient.jsp">  
        Patient Name : <input name="PatientName" type="text" ><br> 
        Phone No : <input name="PhoneNo" type="text" ><br>  
        Email : <input name="Email" type="text" ><br> 
        Address : <input name="Address" type="text" ><br> 
        <input name="btnSubmit" type="submit" value="Save">  </form>  <br> 
 
 <table border="1">  
  <tr>  
    <th>Patient Name</th> 
    <th>Phone No</th> 
    <th>Email</th> 
    <th>Address</th> 
    <th>Update</th> 
    <th>Remove</th>  
   </tr>  
   
    <tr> 
       <td><%out.print(session.getAttribute("PatientName")); %></td>       
       <td><%out.print(session.getAttribute("PhoneNo")); %></td>    
       <td><%out.print(session.getAttribute("Email")); %></td> 
       <td><%out.print(session.getAttribute("Address")); %></td>    
       <td><input name="btnUpdate" type="button" value="Update"></td>
       <td><input name="btnRemove" type="button" value="Remove"></td> 
     </tr> 
   </table>

</body>
</html>