package com.hcs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hcs.model.LabTest;
import com.hcs.utill.DBConnection;

public class LabTestController {
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String AddLabTest(LabTest labtest) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  labtest(LabTestID,TestName,TestType,TestDescription,LabDate) "
							+ "	VALUES (?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2, labtest.getTestName());
			ps.setString(3, labtest.getTestType());
			ps.setString(4, labtest.getTestDescription());
			ps.setDate(5, labtest.getLabDate());
	
		

			ps.execute();
			 connection.close();
			 output = "Inserted successfully"; 

		}
		 catch (Exception e)
		 {
		 output = "Error while inserting the lab test.";
		 System.err.println(e.getMessage());
		 }
		 return output; 

		
	}

	public List<LabTest> readLabTest() {
		List<LabTest> labtests = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from labtest");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				LabTest lab = new LabTest();
				lab.setLabTestID(rs.getInt("LabTestID"));
				lab.setTestName(rs.getString("TestName"));
				lab.setTestType(rs.getString("TestType"));
				lab.setTestDescription(rs.getString("TestDescription"));
				lab.setLabDate(rs.getDate("LabDate"));


				labtests.add(lab);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return labtests;
	}

	public String updateLabTest(LabTest labtest) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE labtest SET TestName=?,TestType=?,TestDescription=?,LabDate=? WHERE LabTestID=?");

			// binding values
			ps.setString(1, labtest.getTestName());
			ps.setString(2, labtest.getTestType());
			ps.setString(3,labtest.getTestDescription());
			ps.setDate(4, labtest.getLabDate());
			ps.setInt(5, labtest.getLabTestID());
			
			
			// execute the statement
			ps.execute();
			connection.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the lab test.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteLabTest(String LabTestID) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from labtest where LabTestID=?");
			// binding values
			ps.setInt(1, Integer.parseInt(LabTestID));
			// execute the statement
			ps.execute();
			connection.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the lab test. -"+ e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

}
