package com.hcs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hcs.model.User;
import com.hcs.util.DBConnection;

public class UserController {
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public String Adduser(User user) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			 if (connection == null)
			 {return "Error while connecting to the database for inserting."; } 
			
			ps = connection.prepareStatement(
					"INSERT INTO  hospital(uid,username,usertype,usercontact,useremail) "
							+ "	VALUES (?,?,?,?,?)");

			ps.setInt(1, 0);
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getUsertype());
			ps.setString(4, user.getUsercontact());
			ps.setString(5, user.getUseremail());
			

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

	public List<User> readUser() {
		List<User> user = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user");
			

			// iterate through the rows in the result set
			while (rs.next()) {
				User use = new User();
				
				use.setUid(rs.getInt("uid"));
				use.setUsername(rs.getString("username"));
				use.setUsertype(rs.getString("usertype"));
				use.setUsercontact(rs.getString("usercontact"));
				use.setUseremail(rs.getString("useremail"));

				user.add(use);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user;
	}

	public String updateuser(User user) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			ps = connection.prepareStatement(
					"UPDATE user SET username=?,usertype=?,usercontact=?,useremail=? WHERE uid=?");

			// binding values
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getUsertype());
			ps.setString(3,user.getUsercontact());
			ps.setString(4,user .getUseremail());
			ps.setInt(5, user.getUid());
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

	public String deleteUser(String uid) {
		String output = "";
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared stateme

			connection = DBConnection.getConnection();
			ps = connection.prepareStatement("delete from user where uid=?");
			// binding values
			ps.setInt(1, Integer.parseInt(uid));
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
	
	
	public User searchUser(String id) {
		User use = new User();
		try {
			connection = DBConnection.getConnection();
			if (connection == null) {
				System.err.println("connecting failed.");
			}
			// Prepare the html table to be displayed
			

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where uid="+id+"");
			

			// iterate through the rows in the result set
			
			while (rs.next()) {
				
				use.setUid(rs.getInt("uid"));
				use.setUsername(rs.getString("username"));
				use.setUsertype(rs.getString("usertype"));
				use.setUsercontact(rs.getString("usercontact"));
				use.setUseremail(rs.getString("useremail"));
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return use;
	}

}
