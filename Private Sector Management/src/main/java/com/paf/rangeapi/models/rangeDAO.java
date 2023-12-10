package com.paf.rangeapi.models;

import java.sql.*;

import com.paf.rangeapi.DBConnect;

public class rangeDAO {
	
	private static Connection con;

	// Insert range
	public String insertRange(String rangeID, String upperLimit, String lowerLimit, String unitPrice) 
	 { 
		String output = ""; 
		
		try
		{ 
			con = DBConnect.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			} 
			
			// create a prepared statement
			String query = "INSERT INTO `range`(`rangeID`, `upperLimit`, `lowerLimit`, `unitPrice`) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setInt(2, Integer.parseInt(upperLimit)); 
			preparedStmt.setInt(3, Integer.parseInt(lowerLimit)); 
			preparedStmt.setDouble(4, Double.parseDouble(unitPrice)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
	 }

	//Retrieve range
	public String getRange() {
		
		String output = "";

		try {
			con = DBConnect.getConnection();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Range ID</th><th>Upper Limit</th>" + "<th>Lower Limit</th>"
					+ "<th>Unit Price</th>" + "<th>EDIT</th><th>REMOVE</th></tr>";

			String query = "SELECT * FROM `range`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String rangeID = Integer.toString(rs.getInt("rangeID"));
				String upperLimit = Integer.toString(rs.getInt("upperLimit"));
				String lowerLimit = Integer.toString(rs.getInt("lowerLimit"));
				String unitPrice = Double.toString(rs.getDouble("unitPrice"));

				// Add into the html table
				output += "<tr><td>" + rangeID + "</td>";
				output += "<td>" + upperLimit + "</td>";
				output += "<td>" + lowerLimit + "</td>";
				output += "<td>" + unitPrice + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='EDIT' "
						+ "class='btn btn-secondary'></td>" + "<td><form method='post' action='range.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' " + "class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + rangeID + "'>" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String updateRange(String rangeID, String upperLimit, String lowerLimit, String unitPrice) 
	{
		
		String output = ""; 
		
		try
		{ 
			con = DBConnect.getConnection();
			
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			
			// create a prepared statement
			String query = "UPDATE `range` SET `upperLimit`=?,`lowerLimit`=?,`unitPrice`=? WHERE rangeID=?";					
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(upperLimit));
			preparedStmt.setInt(2, Integer.parseInt(lowerLimit));
			preparedStmt.setDouble(3, Double.parseDouble(unitPrice));
			preparedStmt.setInt(4, Integer.parseInt(rangeID));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		 } 
		
		return output;
	}
	
	public String deleteRange(String resourceID)
	{ 
		String output = ""; 
		
		try
		{ 
			con = DBConnect.getConnection();
			
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			
			// create a prepared statement
			String query = "DELETE FROM `range` WHERE rangeID=?"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(resourceID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
		 	output = "Error while deleting the item."; 
		 	System.err.println(e.getMessage()); 
		} 
		
		return output; 
	 }

}
