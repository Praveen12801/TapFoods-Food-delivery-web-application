package com.tapfoods.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static final Connection getConnection(){
		
		String URL = "jdbc:mysql://localhost:3306/tap-foods";
		String USERNAME = "root";
		String PASSWORD = "12801";
		Connection connection = null;
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			System.out.println("Database connected successfully.");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
