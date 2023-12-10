package com.paf.categoryapi.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.paf.categoryapi.DBConnect;

public class CategoryDAO {
	private static Connection con;
	private static Statement stmt;
	private PreparedStatement pst;
	private ResultSet rs = null;

	// Retrieve method
	public String getAllCategories() {

		String output = "";

		try {

			con = DBConnect.getConnection();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Category ID</th><th>Category Name</th>" + "<th>Fixed Charge</th>"
					+ "<th>Unit Charge</th>" + "<th>Tax Amount</th>" + "<th>Relief</th>"
					+ "<th>EDIT</th><th>REMOVE</th></tr>";

			String query = "SELECT * FROM category";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String catID = Integer.toString(rs.getInt("categoryID"));
				String catName = rs.getString("categoryName");
				String fixedCharge = Double.toString(rs.getDouble("fixedCharge"));
				String unitCharge = Double.toString(rs.getDouble("unitCharge"));
				String taxCharge = Double.toString(rs.getDouble("taxCharge"));
				String relief = Double.toString(rs.getDouble("relief"));

				// Add into the html table
				output += "<tr><td>" + catID + "</td>";
				output += "<td>" + catName + "</td>";
				output += "<td>" + fixedCharge + "</td>";
				output += "<td>" + unitCharge + "</td>";
				output += "<td>" + taxCharge + "</td>";
				output += "<td>" + relief + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='EDIT' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + catID + "'>" + "</form></td></tr>";
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

	// Insert method
	public String insertCategory(String categoryID, String categoryName, String fixedCharge, String unitCharge,
			String taxCharge, String relief) {

		String output = "";

		try {
			Connection con = DBConnect.getConnection();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = "INSERT INTO category(`categoryID`, `categoryName`, `fixedCharge`, `unitCharge`, `taxCharge`, `relief`)"
					+ "" + "VALUES (?,?,?,?,?,?)";

			pst = con.prepareStatement(query);

			// binding values
			pst.setInt(1, Integer.parseInt(categoryID));
			pst.setString(2, categoryName);
			pst.setDouble(3, Double.parseDouble(fixedCharge));
			pst.setDouble(4, Double.parseDouble(unitCharge));
			pst.setDouble(5, Double.parseDouble(taxCharge));
			pst.setDouble(6, Double.parseDouble(relief));

			// execute the statement
			pst.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update method
	public String updateCategory(String categoryID, String categoryName, String fixedCharge, String unitCharge,
			String taxCharge, String relief) {

		String output = "";

		try {
			Connection con = DBConnect.getConnection();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE category SET categoryName=?,fixedCharge=?,unitCharge=?,taxCharge=?, relief=? WHERE categoryID=?";
			pst = con.prepareStatement(query);

			// binding values
			pst.setString(1, categoryName);
			pst.setDouble(2, Double.parseDouble(fixedCharge));
			pst.setDouble(3, Double.parseDouble(unitCharge));
			pst.setDouble(4, Double.parseDouble(taxCharge));
			pst.setDouble(5, Double.parseDouble(relief));
			pst.setInt(6, Integer.parseInt(categoryID));

			// execute the statement
			pst.execute();
			con.close();

			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Delete Method
	public String deleteCategory(String categoryID) {
		
		String output = "";
		
		try {
			
			con = DBConnect.getConnection();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "DELETE FROM `category` WHERE categoryID=?";
			pst = con.prepareStatement(query);
			
			// binding values
			pst.setInt(1, Integer.parseInt(categoryID));
			
			// execute the statement
			pst.execute();
			con.close();
			
			output = "Deleted successfully";
			
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}