package com.hcs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hcs.model.Hospital;
import com.hcs.util.DBConnection;

public class HospitalController {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String Addhospital(Hospital hospital) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  hospital(hid,hospitalname,hospitaladdress,contactnumber,email) "
							+ "	VALUES (?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2, hospital.getHospitalname());
			ps.setString(3, hospital.getHospitaladdress());
			ps.setString(4, hospital.getContactnumber());
			ps.setString(5, hospital.getEmail());
			

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

	public List<Hospital> readHospital() {
		List<Hospital> hospitals = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from hospital");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				Hospital hos = new Hospital();
				
				hos.setHid(rs.getInt("hid"));
				hos.setHospitalname(rs.getString("hospitalname"));
				hos.setHospitaladdress(rs.getString("hospitaladdress"));
				hos.setContactnumber(rs.getString("contactnumber"));
				hos.setEmail(rs.getString("email"));

				hospitals.add(hos);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return hospitals;
	}

	public String updatehospital(Hospital hospital) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE hospital SET hospitalname=?,hospitaladdress=?,contactnumber=?,email=? WHERE hid=?");

			// binding values
			ps.setString(1, hospital.getHospitalname());
			ps.setString(2, hospital.getHospitaladdress());
			ps.setString(3,hospital.getContactnumber());
			ps.setString(4,hospital .getEmail());
			ps.setInt(5, hospital.getHid());
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

	public String deleteHospital(String hid) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared stateme

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from hospital where hid=?");
			// binding values
			ps.setInt(1, Integer.parseInt(hid));
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
	
	
	public Hospital searchHospital(String id) {
		Hospital hos = new Hospital();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from hospital where hid="+id+"");
			

			// iterate through the rows in the result set
			
			while (rs.next()) {
				
				hos.setHid(rs.getInt("hid"));
				hos.setHospitalname(rs.getString("hospitalname"));
				hos.setHospitaladdress(rs.getString("hospitaladdress"));
				hos.setContactnumber(rs.getString("contactnumber"));
				hos.setEmail(rs.getString("email"));
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return hos;
	}


}
