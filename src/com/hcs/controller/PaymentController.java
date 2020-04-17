package com.hcs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.hcs.model.Bill;
import com.hcs.model.Payment;
import com.hcs.util.DBConnection;

public class PaymentController {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String AddPayment(Payment payment) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  payment(PaymentID,BillID,PaymentDate,PaymentAmount,PaymentType,PaymentDescription)"
							+ "	VALUES (?,?,?,?,?,?)");
			
		

			ps.setInt(1, 0);
			ps.setString(2, payment.getBillID());
			ps.setDate(3, (Date) payment.getPaymentDate());
			ps.setDouble(4, payment.getPaymentAmount());
			ps.setString(5, payment.getPaymentType());
			ps.setString(6, payment.getPaymentDescription());
			
			ps.execute();
			 connection.close();
			 output = "Inserted successfully"; 

		}
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output; 

		
	}

	public List<Payment> readPayment() {
		List<Payment> payment = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from payment");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				Payment pay = new Payment();
				pay.setPaymentID(rs.getInt("PaymentID"));
				pay.setBillID(rs.getString("BillID"));
				pay.setPaymentDate(rs.getDate("PaymentDate"));
				pay.setPaymentAmount(rs.getDouble("PaymentAmount"));
				pay.setPaymentType(rs.getString("PaymentType"));
				pay.setPaymentDescription(rs.getString("PaymentDescription"));
			    
				
				payment.add(pay);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return payment;
	}

	public String updatepayment(Payment payment) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE payment SET BillID=?,PaymentDate=?,PaymentAmount=?,PaymentType=?,PaymentDescription=? WHERE PaymentID=?");

			// binding values
			ps.setString(1, payment.getBillID());
			ps.setDate(2, (Date) payment.getPaymentDate());
			ps.setDouble(3,payment.getPaymentAmount());
			ps.setString(4, payment.getPaymentType());
			ps.setString(5, payment.getPaymentDescription());
			ps.setInt(6, payment.getPaymentID());
			
			// execute the statement
			ps.execute();
			connection.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String PaymentID) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from payment where PaymentID=?");
			// binding values
			ps.setInt(1, Integer.parseInt(PaymentID));
			// execute the statement
			ps.execute();
			connection.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item. -"+ e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	

}
