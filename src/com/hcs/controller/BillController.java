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
import com.hcs.util.DBConnection;

public class BillController {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String AddBill(Bill bill) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  bill(BillID,BillType,BillDate,BillAmount,ReferenceID,ReferenceType) "
							+ "	VALUES (?,?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2,bill.getBillType());
			ps.setDate(3, (Date) bill.getBillDate());
			ps.setDouble(4, bill.getBillAmount());
			ps.setString(5,bill.getReferenceID());
			ps.setString(6,bill.getReferenceType());
			
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

	public List<Bill> readBill() {
		List<Bill> bill = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from bill");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				Bill b = new Bill();
				b.setBillID(rs.getInt("BillID"));
				b.setBillType(rs.getString("BillType"));
				b.setBillDate(rs.getDate("BillDate"));
				b.setBillAmount(rs.getFloat("BillAmount"));
				b.setReferenceID(rs.getString("ReferenceID"));
				b.setReferenceType(rs.getString("ReferenceType"));
				
				bill.add(b);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return bill;
	}

	public String updatebill(Bill bill) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE bill SET BillType=?,BillDate=?,BillAmount=?,ReferenceID=?,ReferenceType=? WHERE BillID=?");

			// binding values
			ps.setString(1, bill.getBillType());
			ps.setDate(2, (Date) bill.getBillDate());
			ps.setDouble(3,bill.getBillAmount());
			ps.setString(4, bill.getReferenceID());
			ps.setString(5, bill.getReferenceType());
			ps.setInt(6, bill.getBillID());
			
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

	public String deleteBill(String BillID) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from bill where BillID=?");
			// binding values
			ps.setInt(1, Integer.parseInt(BillID));
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