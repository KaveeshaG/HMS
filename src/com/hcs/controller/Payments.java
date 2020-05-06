package com.hcs.controller;

import java.sql.*;

public class Payments {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hcs", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readPayments()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	// Prepare the html table to be displayed
	 output = "<table border=\'1\'><tr><th>BillID</th>"
	  		 + "<th>PaymentDate</th>  "
	  		 + "<th>PaymentAmount</th><th>PaymentType</th><th>PaymentDescription</th>         "
	  		 + "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payment";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String PaymentID = Integer.toString(rs.getInt("PaymentID"));
			 String BillID = rs.getString("BillID");
			 String PaymentDate = rs.getString("PaymentDate");
			 String PaymentAmount = Double.toString(rs.getDouble("PaymentAmount"));
			 String PaymentType = rs.getString("PaymentType");
			 String PaymentDescription = rs.getString("PaymentDescription");
			// Add into the html table
			 output += "<tr><td><input id='hidPaymentIDUpdate'        "
			   		  + "name='hidPaymentIDUpdate'          "
			   		  + "type='hidden' value='" + PaymentID
			   		  + "'>"  + BillID +   "</td>";    
			       
			   output += "<td>" + PaymentDate + "</td>";    
			   output += "<td>" + PaymentAmount + "</td>"; 
			   output += "<td>" + PaymentType + "</td>"; 
			   output += "<td>" + PaymentDescription + "</td>";  
			
			// buttons
			 output += "<td><input name='btnUpdate'          "
			   		  + "type='button' value='Update'         "
			   		  + "class='btnUpdate btn btn-secondary'></td>"      
			   		  + "<td><input name='btnRemove'         "
			   		  + "type='button' value='Remove'         "
			   		  + "class='btnRemove btn btn-danger'        "
			   		  + "data-PaymentID='"       
			   		  + PaymentID + "'>" + "</td></tr>";
			 
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }

	public String insertPayment(String BillID, String PaymentDate, String PaymentAmount, String PaymentType,
			String PaymentDescription) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment (`PaymentID`,`BillID`,`PaymentDate`,`PaymentAmount`,`PaymentType`, `PaymentDescription`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, BillID);
			preparedStmt.setString(3, PaymentDate);
			preparedStmt.setDouble(4, Double.parseDouble(PaymentAmount));
			preparedStmt.setString(5, PaymentType);
			preparedStmt.setString(6, PaymentDescription);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String PaymentID, String BillID, String PaymentDate, String PaymentAmount,
			String PaymentType, String PaymentDescription) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payment SET BillID=?,PaymentDate=?,PaymentAmount=?,PaymentType=?, PaymentDescription=? WHERE PaymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, BillID);
			preparedStmt.setString(2, PaymentDate);
			preparedStmt.setDouble(3, Double.parseDouble(PaymentAmount));
			preparedStmt.setString(4, PaymentType);
			preparedStmt.setString(5, PaymentDescription);
			preparedStmt.setInt(6, Integer.parseInt(PaymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			System.out.println("error is "+e);
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayments(String PaymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from Payment where PaymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PaymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}