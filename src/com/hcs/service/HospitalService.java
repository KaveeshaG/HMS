package com.hcs.service;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.hcs.model.Hospital;
import com.hcs.util.DBConnection;

public class HospitalService {
	public String insertHospital(Hospital app) {

		DBConnection connection = new DBConnection();
        String output = "";

        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for inserting.";
            }

            String query = " insert into hospital (`hid`,`name`,`address`,`contact`,`email`)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, app.getHid());
            preparedStmt.setString(2, app.getHospitalName());
            preparedStmt.setString(4, app.getHospitalAddress());
            preparedStmt.setInt(3, app.getHospitalContact());
            preparedStmt.setString(5, app.getHospitalEmail());

            
            preparedStmt.executeUpdate();
            con.close();
            output = "Inserted successfully";
            System.out.println("Hospital inserted successfully");

        } catch (Exception e) {
            output = "Error while inserting hospital details.";
            System.out.println("Error while inserting hospital");
            System.out.println(e.getMessage());
        }
        return output;
    }

    public String readHospital() {
        DBConnection connection = new DBConnection();
        StringBuilder output = new StringBuilder();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for reading.";
            }

            output = new StringBuilder("<table border=\"1\"><tr><th>Hospital ID</th><th>Hospital Name</th><th>Contact</th><th>Email</th>" +
                    "<th>Address</th>" +"<th>Update</th><th>Remove</th></tr>");
            String query = "select * from hospital";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String Hid =Integer.toString(rs.getInt("hid"));
                String HospitalName = rs.getString("hospitalName");
                String HospitalAddress = Integer.toString(rs.getInt("hospitalAddress"));
                String HospitalContact = rs.getString("hospitalContact");
                String HospitalEmail = rs.getString("hospitalEmail");


                output.append("<tr><td>").append(Hid).append("</td>");
                output.append("<td>").append(HospitalName).append("</td>");
                output.append("<td>").append(HospitalAddress).append("</td>");
                output.append("<td>").append(HospitalContact).append("</td>");
                output.append("<td>").append(HospitalEmail).append("</td>");

                output.append("<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"></td></tr>");
            }

            output.append("</table>");
            System.out.println("Hospital Details retrieving Successful");
            con.close();
        } catch (Exception e) {
            output = new StringBuilder("Error while reading hospital.");
            System.out.println("Hospital Details retrieving Unsuccessful");
            System.err.println(e.getMessage());
        }
        return output.toString();

    }

    public Hospital readHospital(String hid) {
        DBConnection connection = new DBConnection();
        Hospital hospital = new Hospital();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
            }

            String query = "select * from hospital where hid = '" + hid + "'";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                
            	hospital.setHid(rs.getInt("hid"));
            	hospital.setHospitalName(rs.getString("hospiatalName"));
            	hospital.setHospitalAddress(rs.getString("hospitalAddress"));
            	hospital.setHospitalContact(rs.getInt("hospitalContact"));
            	hospital.setHospitalEmail(rs.getString("hospitalEmail"));

                    System.out.println("Hospital details retrieving Successful");
                    con.close();
                    return hospital;
                }
            


        } catch (Exception e) {
            System.out.println("Error while reading hospital details. hospital details retrieving Unsuccessful");
            System.err.println(e.getMessage());
        }
        System.out.println("No any hospital in system");
        return new Hospital();

    }


    public String updateHospital(Hospital hospital) {
        DBConnection connection = new DBConnection();
        String output = "";
        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            }

            String query = "UPDATE hospital SET name=?, address=?, contact=?, email=? WHERE hid=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, hospital.getHospitalName());
            preparedStmt.setString(2, hospital.getHospitalAddress());
            preparedStmt.setInt(3, hospital.getHospitalContact());
            preparedStmt.setString(4, hospital.getHospitalEmail());
            
            

            preparedStmt.executeUpdate();
            con.close();
            output = "Updated successfully";
            System.out.println("Update successful on hospital");
        } catch (Exception e) {
            output = "Error while updating doctor.";
            System.out.println("Update unsuccessful on hospital");
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deleteItem(String hid) {
        DBConnection connection = new DBConnection();
        String output = "";

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }

            String query = "delete from hospital where hid=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, hid);
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
            System.out.println("Hospital details deleted successfully");
        } catch (Exception e) {
            output = "Error while deleting hospital details.";
            System.err.println(e.getMessage());
            System.out.println("Hospital deletion error");
        }
        return output;
    }
		
		}
		