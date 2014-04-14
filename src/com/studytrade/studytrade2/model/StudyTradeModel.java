package com.studytrade.studytrade2.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudyTradeModel {

	private MyDatabaseConnection DBConnection = new MyDatabaseConnection();
	
	public StudyTradeModel() {
		// nothing
	}

	public List<StudyTradeArticle> GetSearchResults(String searchstring) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		MyDatabaseConnection databasecon = new MyDatabaseConnection();
		con = databasecon.getConnection();
		
		List<StudyTradeArticle> result = new ArrayList<>();
		
		try {
			stmt = con.createStatement();
			String query = "select * from article";
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				StudyTradeArticle article = new StudyTradeArticle(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("place"), 
						rs.getString("description"));

				result.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}

}
