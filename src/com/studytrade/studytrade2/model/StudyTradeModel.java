package com.studytrade.studytrade2.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logging.STLog;

import com.studytrade.studytrade2.model.database.MyDatabaseConnection;
import com.studytrade.studytrade2.model.helper.HashHelper;

public class StudyTradeModel {
	private final static String DB_URL = "jdbc:mysql://localhost/studytrade";
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
						rs, new StudyTradeUser(rs)
				);

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
	
	public StudyTradeUser getLogedInUser() {
		return CurrentUser;
	}
	
	public LoginProblem logIn(String nickname, String password) {
		String in_pwhash = HashHelper.doSHA1(password);
		
		try {
			DBConnection.PrepStatements.Statement_UserByNickname.setString(1, nickname);
			ResultSet rs = DBConnection.PrepStatements.Statement_UserByNickname.executeQuery();
			
			if (rs.next()) {
				StudyTradeUser user = new StudyTradeUser(rs);

				String db_pwhash = rs.getString("passwordhash");
				
				if (db_pwhash.equalsIgnoreCase(in_pwhash)) {
					if (user.Activated) {
						CurrentUser = user;

						STLog.Log("Login of User >>" + nickname + "<< successfull");
						return LoginProblem.NO_PROBLEM;
					} else {
						return LoginProblem.NOT_ACTIVATED;
					}
				} else {
					STLog.Log("Login failed -- Password mismatch");
					return LoginProblem.WRONG_PWD;
				}
			} else {
				STLog.Log("Login failed -- Username not found in DB");
				return LoginProblem.WRONG_USN; // No user found
			}
		} catch (SQLException e) {
			STLog.log(e);
			return LoginProblem.UNKNOWN;
		}
	}

	public boolean register(String forename, String lastname, String nickname, String place, String university, String studydirection, String email, String password) {
		try {
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(1, forename);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(2, lastname);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(3, nickname);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(4, place);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(5, university);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(6, studydirection);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(7, email);
			DBConnection.PrepStatements.Statement_InsertNewUser.setString(8, HashHelper.doSHA1(password));
			DBConnection.PrepStatements.Statement_InsertNewUser.setInt(9, 0);
			
			return DBConnection.PrepStatements.Statement_InsertNewUser.execute();
		} catch (SQLException e) {
			STLog.log(e);
			return false;
		}
	}
	
	public void logOff() {
		CurrentUser = null;
	}

	public List<String> getNicknameList() {
		List<String> result = new ArrayList<>();
		
		try {
			ResultSet rs = DBConnection.PrepStatements.Statement_ListNicknames.executeQuery();
			
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return result;
	}
	
	public boolean createMessage(StudyTradeUser sender, StudyTradeUser target, String header, String text) {
		try {
			DBConnection.PrepStatements.Statement_CreateMessage.setInt(1, sender.ID);
			DBConnection.PrepStatements.Statement_CreateMessage.setInt(2, target.ID);
			DBConnection.PrepStatements.Statement_CreateMessage.setString(3, header);
			DBConnection.PrepStatements.Statement_CreateMessage.setString(4, text);
			
			return DBConnection.PrepStatements.Statement_CreateMessage.execute();
		} catch (SQLException e) {
			STLog.log(e);
			return false;
		}
	}
}
