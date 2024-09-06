package com.diva.file_hider_project.db;

import java.sql.*;

public class MyConnection {
	
	public static Connection connection;
	private static  String url = "jdbc:mysql://localhost:3306/filehider";
	private static String user = "root";
	private static String password = "root";
	
	public static Connection getConnection() {
		//load the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("load driver successfully !!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while loading the driver !");
			e.printStackTrace();
		}
		try {
				connection = DriverManager.getConnection(url, user, password);
				//1System.out.println("Connection established successfully !!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in connection established !!");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static void closeConnection()  {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
