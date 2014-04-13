package com.studytrade.studytrade2.model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabaseConnection {

	public java.sql.Connection getConnection() {
		String url = "jdbc:mysql://localhost/StudyTrade";
		String user = "server";
		String password = "password";
		java.sql.Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return con;
	}
}
