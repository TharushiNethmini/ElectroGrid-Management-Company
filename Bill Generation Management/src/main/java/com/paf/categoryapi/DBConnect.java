package com.paf.categoryapi;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static String url = "jdbc:mysql://localhost:3306/electrogrid";
	private static String un = "root";
	private static String pw = "";
	private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, un, pw);
			System.out.println("Successful Connection!");
		} catch (Exception e) {
			System.out.println("Unsuccessful Connection: " + e.getMessage());
		}
		
		return con;
		
	}
}
