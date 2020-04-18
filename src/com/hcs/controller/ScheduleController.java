package com.hcs.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.hcs.model.Doctor;
import com.hcs.model.Hospital;
import com.hcs.model.Schedule;
import com.hcs.util.DBConnection;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ScheduleController {
	
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	public static final String HOSPITAL_URI="http://localhost:8081/HospitalService/HospitalService/Hospital";
	
	
	public String AddSchedule(Schedule schedule) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  schedule(SchduleID,hid,DoctorID,ScheduleDate,ScheduleTime) "
							+ "	VALUES (?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setInt(2, schedule.getHid());
			ps.setInt(3, schedule.getDoctorID());
			ps.setDate(4, (Date) schedule.getScheduleDate());
			ps.setTime(5, schedule.getScheduleTime());
		

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
	
	
	public List<Schedule> readSchedule() {
		List<Schedule> schedules = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT d.DoctorName,d.DoctorID,s.ScheduleDate,s.ScheduleTime,s.hid,s.SchduleID " + 
					" FROM schedule s " + 
					" INNER JOIN doctor d " + 
					" ON d.DoctorID = s.DoctorID ");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				ClientConfig cfg = new DefaultClientConfig(GensonJsonConverter.class);
				Client client = Client.create(cfg);
				WebResource webResource = client.resource(HOSPITAL_URI+"/search/"+rs.getInt("hid"));

				// you can map it to a pojo, no need to have a string or map
				Hospital hospital = webResource
				                .accept(MediaType.APPLICATION_JSON)
				                .get(Hospital.class);
				
//				Hospital hospital=response.getEntity(Hospital.class);
				
				Schedule sch = new Schedule();
				Doctor doc=new Doctor();
				doc.setDoctorName(rs.getString("DoctorName"));
				doc.setDoctorID(rs.getInt("DoctorID"));
				
				sch.setDoctorID(rs.getInt("DoctorID"));
				sch.setDoctor(doc);
				sch.setHospital(hospital);
				sch.setScheduleDate(rs.getDate("ScheduleDate"));
				sch.setScheduleTime(rs.getTime("ScheduleTime"));
				sch.setSchduleID(rs.getInt("SchduleID"));
				sch.setHid(rs.getInt("hid"));
				

				schedules.add(sch);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return schedules;
	}
	
	
	public String updateSchedule(Schedule schedule) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE schedule SET hid=?,DoctorID=?,ScheduleDate=?,ScheduleTime=? WHERE SchduleID=?");

			// binding values
			ps.setInt(1, schedule.getHid());
			ps.setInt(2, schedule.getDoctorID());
			ps.setDate(3,(Date) schedule.getScheduleDate());
			ps.setTime(4, schedule.getScheduleTime());
			ps.setInt(5, schedule.getSchduleID());
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
	
	
	public String deleteSchedule(String SchduleID) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from schedule where SchduleID=?");
			// binding values
			ps.setInt(1, Integer.parseInt(SchduleID));
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
	
	
	public Schedule searchSchedule(String id) {
		Schedule sch = new Schedule();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT d.DoctorName,d.DoctorID,s.ScheduleDate,s.ScheduleTime " + 
					" FROM schedule s " + 
					" INNER JOIN doctor d " + 
					" ON d.DoctorID = s.DoctorID where SchduleID="+id+"");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				
				Doctor doc=new Doctor();
				doc.setDoctorName(rs.getString("DoctorName"));
				doc.setDoctorID(rs.getInt("DoctorID"));
				
				sch.setDoctorID(rs.getInt("DoctorID"));
				sch.setDoctor(doc);
				sch.setScheduleDate(rs.getDate("ScheduleDate"));
				sch.setScheduleTime(rs.getTime("ScheduleTime"));
				
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return sch;
	}
	
	


}
