package com.hcs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hcs.model.Appointment;
import com.hcs.util.DBConnection;

public class AppointmentController {


	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String AddAppointment(Appointment appointment) {
	String output = "";
	try {
		connection = DBConnection.getConnection();
		 if (connection == null)
		 {return "Error while connecting to the database for inserting."; } 
		
		ps = connection.prepareStatement(
				"INSERT INTO  Appointment(AppointmentID,PatientName,PatientEmail,DoctorName,Reason) "
						+   "	VALUES (?,?,?,?,?)");

		
		ps.setInt(1, 0);
		ps.setString(2, appointment.getPatientName());
		ps.setString(3, appointment.getPatientEmail());
		ps.setString(4, appointment.getDoctorName());
		ps.setString(5, appointment.getReason());

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

	public List<Appointment> readAppointments() {
	List<Appointment>  Appointments= new ArrayList<>();
	try {
		connection = DBConnection.getConnection();
		if (connection == null) {
			System.err.println("connecting failed.");
		}
		// Prepare the html table to be displayed
		

		Statement stmt = connection.createStatement();
		rs = stmt.executeQuery("select * from appointment");
		

		// iterate through the rows in the result set
		while (rs.next()) {
			Appointment app = new Appointment();
			app.setAppointmentID(rs.getInt("AppointmentID"));
			app.setPatientName(rs.getString("PatientName"));
			app.setPatientEmail(rs.getString("PatientEmail"));
			app.setDoctorName(rs.getString("DoctorName"));
			app.setReason(rs.getString("Reason"));

			Appointments.add(app);
		}
		connection.close();

	} catch (Exception e) {
		System.err.println(e.getMessage());
	}
	return Appointments;
}

	public String updateAppointment(Appointment appointment) {
	String output = "";
	try {
		connection = DBConnection.getConnection();
		if (connection == null) {
			return "Error while connecting to the database for updating.";
		}
		// create a prepared statement
		ps = connection.prepareStatement(
				"UPDATE appointment SET PatientName=?,PatientEmail=?,DoctorName=?,Reason=? WHERE AppointmentID=?");

		// binding values
		ps.setString(1, appointment.getPatientName());
		ps.setString(2, appointment.getPatientEmail());
		ps.setString(3,appointment.getDoctorName());
		ps.setString(4, appointment.getReason());
		ps.setInt(5, appointment.getAppointmentID());
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

	public String deleteAppointment(String AppointmentID) {
	String output = "";
	try {
		connection = DBConnection.getConnection();
		if (connection == null) {
			return "Error while connecting to the database for deleting.";
		}
		// create a prepared statement

		connection = DBConnection.getConnection();
		ps = connection.prepareStatement("delete from appointment where AppointmentID=?");
		// binding values
		ps.setInt(1, Integer.parseInt(AppointmentID));
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
