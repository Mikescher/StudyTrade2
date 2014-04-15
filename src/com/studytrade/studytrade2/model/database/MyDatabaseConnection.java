package com.studytrade.studytrade2.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.studytrade.studytrade2.model.StudyTradeArticle;

import logging.STLog;

public class MyDatabaseConnection {
	private final String db_Url;
	private final String db_Username;
	private final String db_Password;
	
	private final Connection connection;
	public final  StudyTradeStatements PrepStatements;
	
	public MyDatabaseConnection(String u, String n, String p) {
		this.db_Url = u;
		this.db_Username = n;
		this.db_Password = p;
		
		connection = openConnection();
		
		PrepStatements = new StudyTradeStatements(connection);
	}
	
	private Connection openConnection() {
		java.sql.Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(db_Url, db_Username, db_Password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			STLog.log(e);
		}

		return con;
	}
	
	public ResultSet executeQuery(String query) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			return rs;
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
	}
}