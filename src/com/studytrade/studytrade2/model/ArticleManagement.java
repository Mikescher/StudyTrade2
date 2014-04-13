package com.studytrade.studytrade2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticleManagement {
	public ResultSet ArticleSearch(String search, String name, String condition, String colour, String price) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		MyDatabaseConnection databasecon = new MyDatabaseConnection();
		con = databasecon.getConnection();
		try {
			stmt = con.createStatement();
			String query = "select * from article";
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	public boolean ArticleSafe(String name, double price, int condition, String place, int seller_id, String picture, String description) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		MyDatabaseConnection databasecon = new MyDatabaseConnection();
		con = databasecon.getConnection();
		String query = "insert into article ('','', etc) VALUES (default,?,?,?,?,?,?,?,?,?)";// TODO values eintragen
		ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, String.valueOf(price).toString());
		ps.setString(3, String.valueOf(condition).toString());
		ps.setString(4, place);
		ps.setString(5, String.valueOf(seller_id).toString());
		ps.setString(6, picture);
		ps.setString(7, description);
		ps.executeUpdate();
		con.close();
		return true;
	}
}
