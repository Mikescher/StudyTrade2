package com.studytrade.studytrade2.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logging.STLog;

import com.studytrade.studytrade2.model.database.MyDatabaseConnection;
import com.studytrade.studytrade2.model.helper.HashHelper;

public class StudyTradeModel {
	private final static String DB_URL = "jdbc:mysql://localhost/StudyTrade";
	private final static String DB_USERNAME = "server";
	private final static String DB_PASSWORD = "password";
	
	private MyDatabaseConnection DBConnection = new MyDatabaseConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	private StudyTradeUser CurrentUser = null;
	
	public StudyTradeModel() {
		// nothing
	}

	public List<StudyTradeArticle> getSearchResults(String searchstring) {
		List<StudyTradeArticle> result = new ArrayList<>();
		
		try {
			DBConnection.PrepStatements.Statement_FindArticle.setString(1, "%" + searchstring + "%");
			ResultSet rs = DBConnection.PrepStatements.Statement_FindArticle.executeQuery();
			
			while (rs.next()) {
				StudyTradeArticle article = new StudyTradeArticle(
						rs.getInt("id"), 
						rs.getString("name"), 
						rs.getString("place"), 
						rs.getString("description"));

				result.add(article);
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return result;
	}

	public boolean isLoggedIn() {
		return CurrentUser != null;
	}
	
	public StudyTradeUser GetLogedInUser() {
		return CurrentUser;
	}
	
	public boolean logIn(String nickname, String password) {
		String in_pwhash = HashHelper.doSHA1(password);
		
		try {
			DBConnection.PrepStatements.Statement_UserByNickname.setString(1, nickname);
			ResultSet rs = DBConnection.PrepStatements.Statement_UserByNickname.executeQuery();
			
			if (rs.next()) {
				StudyTradeUser user = new StudyTradeUser(
						rs.getString("nickname"), 
						rs.getString("mail"));

				String db_pwhash = rs.getString("passwordhash");
				
				if (db_pwhash.equalsIgnoreCase(in_pwhash)) {
					CurrentUser = user;

					STLog.Log("Login of User >>" + nickname + "<< successfull");
					return true;
				} else {
					STLog.Log("Login failed -- Password mismatch");
					return false;
				}
			} else {
				STLog.Log("Login failed -- Username not found in DB");
				return false; // No user found
			}
		} catch (SQLException e) {
			STLog.log(e);
			return false;
		}
	}

	public void logOff() {
		CurrentUser = null;
	}
}
