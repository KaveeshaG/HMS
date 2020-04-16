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

import com.hcs.model.Doctor;
import com.hcs.util.DBConnection;

public class DoctorController {

	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String AddDoctor(Doctor doctor) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  doctor(DoctorID,DoctorName,Email,PhoneNumber,DoctorType,WorkHospital) "
							+ "	VALUES (?,?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2, doctor.getDoctorName());
			ps.setString(3, doctor.getEmail());
			ps.setInt(4, doctor.getPhoneNumber());
			ps.setString(5, doctor.getDoctorType());
			ps.setString(6, doctor.getWorkHospital());

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

	public List<Doctor> readDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from doctor");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				Doctor doc = new Doctor();
				doc.setDoctorID(rs.getInt("DoctorID"));
				doc.setDoctorName(rs.getString("DoctorName"));
				doc.setPhoneNumber(rs.getInt("PhoneNumber"));
				doc.setEmail(rs.getString("Email"));
				doc.setDoctorType(rs.getString("DoctorType"));

				doctors.add(doc);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return doctors;
	}

	public String updatedoctor(Doctor doctor) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE doctor SET DoctorName=?,Email=?,PhoneNumber=?,DoctorType=?,WorkHospital=? WHERE DoctorID=?");

			// binding values
			ps.setString(1, doctor.getDoctorName());
			ps.setString(2, doctor.getEmail());
			ps.setInt(3,doctor.getPhoneNumber());
			ps.setString(4, doctor.getDoctorType());
			ps.setString(5, doctor.getWorkHospital());
			ps.setInt(6, doctor.getDoctorID());
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

	public String deleteDoctor(String DoctorID) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from doctor where DoctorID=?");
			// binding values
			ps.setInt(1, Integer.parseInt(DoctorID));
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
