package com.hcs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hcs.model.Hospital;
import com.hcs.util.DBConnection;

public class HospitalController {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String AddDoctor(Hospital hospital) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  doctor(hid,HospitalName,HospitalAddress,HospitalContact,HospitalEmail)"
							+ "	VALUES (?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2, hospital.getHospitalName());
			ps.setString(3, hospital.getHospitalAddress());
			ps.setString(4, hospital.getHospitalContact());
			ps.setString(5, hospital.getHospitalEmail());

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
		List<Hospital> hospital = new ArrayList<>();
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
				hos.setHospitalName(rs.getString("HospitalName"));
				hos.setHospitalAddress(rs.getString("HospitalAddress"));
				hos.setHospitalContact(rs.getString("HospitalContact"));
				hos.setHospitalEmail(rs.getString("HospitalEmail"));

				hospital.add(hos);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return hospital;
	}

	public String updatedoctor(Hospital hospital) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE doctor SET name=?,address=?,contact=?,email=? WHERE hid=?");

			// binding values
			ps.setString(1, hospital.getHospitalName());
			ps.setString(2, hospital.getHospitalAddress());
			ps.setString(3,hospital.getHospitalContact());
			ps.setString(4, hospital.getHospitalEmail());
			ps.setInt(6, hospital.getHid());
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
			// create a prepared statement

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

}