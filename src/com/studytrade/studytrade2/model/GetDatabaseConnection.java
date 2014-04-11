package com.studytrade.studytrade2.model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class GetDatabaseConnection {

	public java.sql.Connection getConnection() {
		String url="jdbc:mysql://localhost/StudyTrade";
		String password="password";
		String user="server";
		java.sql.Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
