package com.studytrade.studytrade2.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
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
	
	public MyDatabaseConnection DBConnection = new MyDatabaseConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
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
				StudyTradeArticle article = new StudyTradeArticle(this, rs);

				result.add(article);
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return result;
	}
	
	public List<StudyTradeArticle> filterSearchResults(String direction, Float minPrice, Float maxPrice, String description, String place, String condition, List<StudyTradeArticle> results) {
		List<StudyTradeArticle> tmp = new ArrayList<>();
		
		if (! direction.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.Owner.Studydirection.toLowerCase().equals(direction.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (minPrice != null && maxPrice != null) {
			for (StudyTradeArticle sta : results) {
				if (sta.Price.compareTo(new BigDecimal(minPrice)) > 0 && sta.Price.compareTo(new BigDecimal(maxPrice)) < 0) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (! description.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.Description.toLowerCase().contains(description.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (! place.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.Place.toLowerCase().equals(place.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (! condition.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.getConditionString().toLowerCase().equals(condition.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		return results;
	}

	public boolean isLoggedIn() {
		return CurrentUser != null;
	}
	
	public StudyTradeUser getLoggedInUser() {
		return CurrentUser;
	}
	
	public StudyTradeUser getUser(String nickname) {
		try {
			DBConnection.PrepStatements.Statement_UserByNickname.setString(1, nickname);
			ResultSet rs = DBConnection.PrepStatements.Statement_UserByNickname.executeQuery();
			
			if (rs.next()) {
				return new StudyTradeUser(this, rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
	}
	
	public StudyTradeUser getUser(int userid) {
		try {
			DBConnection.PrepStatements.Statement_UserByID.setInt(1, userid);
			ResultSet rs = DBConnection.PrepStatements.Statement_UserByID.executeQuery();
			
			if (rs.next()) {
				return new StudyTradeUser(this, rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
	}
	
	public LoginProblem logIn(String nickname, String password) {
		String in_pwhash = HashHelper.doSHA1(password);
		
		StudyTradeUser user = getUser(nickname);

		if (user == null) {
			STLog.Log("Login failed -- Username not found in DB");
			return LoginProblem.WRONG_USN; // No user found (or internal err)
		}
		
		if (user.Passwordhash.equalsIgnoreCase(in_pwhash)) {
			if (user.Activated) {
				CurrentUser = user;

				STLog.Log("Login of User >>" + nickname + "<< successfull");
				return LoginProblem.NO_PROBLEM;
			} else {
				STLog.Log("Login of User >>" + nickname + "<< failed: Not activated");
				return LoginProblem.NOT_ACTIVATED;
			}
		} else {
			STLog.Log("Login failed -- Password mismatch");
			return LoginProblem.WRONG_PWD;
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
	
	public List<StudyTradeMessage> getMessagesBySender(StudyTradeUser sender, boolean unread) {
		List<StudyTradeMessage> result = new ArrayList<>();
		
		@SuppressWarnings("resource")
		PreparedStatement prepStmt = unread ? DBConnection.PrepStatements.Statement_UnreadMessagesBySender : DBConnection.PrepStatements.Statement_MessagesBySender;
		
		try {
			prepStmt.setInt(1, sender.ID);
			
			ResultSet rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				result.add(new StudyTradeMessage(this, rs));
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return result;
	}
	
	public List<StudyTradeMessage> getMessagesByTarget(StudyTradeUser target, boolean unread) {
		List<StudyTradeMessage> result = new ArrayList<>();
		
		@SuppressWarnings("resource")
		PreparedStatement prepStmt = unread ? DBConnection.PrepStatements.Statement_UnreadMessagesByTarget : DBConnection.PrepStatements.Statement_MessagesByTarget;
		
		try {
			prepStmt.setInt(1, target.ID);
			
			ResultSet rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				result.add(new StudyTradeMessage(this, rs));
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return result;
	}

	public StudyTradeArticle addArticle(String name, Float price, int cond, String place, String desc) {
		try {
			DBConnection.PrepStatements.Statement_CreateArticle.setString(1, name);
			DBConnection.PrepStatements.Statement_CreateArticle.setBigDecimal(2, new BigDecimal(price));
			DBConnection.PrepStatements.Statement_CreateArticle.setInt(3, cond);
			DBConnection.PrepStatements.Statement_CreateArticle.setString(4, place);
			DBConnection.PrepStatements.Statement_CreateArticle.setInt(5, getLoggedInUser().ID);
			DBConnection.PrepStatements.Statement_CreateArticle.setString(6, ""); // Pictures
			DBConnection.PrepStatements.Statement_CreateArticle.setString(7, desc);
			
			DBConnection.PrepStatements.Statement_CreateArticle.execute();
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		try {
			ResultSet rs = DBConnection.PrepStatements.Statement_NewestArticle.executeQuery();
			
			if (rs.next()) {
				return new StudyTradeArticle(this, rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
	}
	
	public List<StudyTradeArticle> getArticlesByOwner(StudyTradeUser usr) {
		List<StudyTradeArticle> result = new ArrayList<>();

		try {
			DBConnection.PrepStatements.Statement_ArticleByOwner.setInt(1, usr.ID);
			
			ResultSet rs = DBConnection.PrepStatements.Statement_ArticleByOwner.executeQuery();
			
			while (rs.next()) {
				result.add(new StudyTradeArticle(this, rs));
			}
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return result;
	}

	public boolean deleteArticle(StudyTradeArticle article) {
		try {
			DBConnection.PrepStatements.Statement_DeleteArticle.setInt(1, article.ArticleID);
			
			DBConnection.PrepStatements.Statement_DeleteArticle.execute();
		} catch (SQLException e) {
			STLog.log(e);
			return false;
		}
		return true;
	}

	public StudyTradeArticle updateArticle(StudyTradeArticle article, String name, Float fprice, int cond, String place, String desc) {
		StudyTradeArticle na = new StudyTradeArticle(this, article.ArticleID, article.Owner.ID, name, new BigDecimal(fprice), cond, place, desc);
		
		try {
			DBConnection.PrepStatements.Statement_UpdateArticle.setString(1, na.Name);
			DBConnection.PrepStatements.Statement_UpdateArticle.setBigDecimal(2, na.Price);
			DBConnection.PrepStatements.Statement_UpdateArticle.setInt(3, na.Condition);
			DBConnection.PrepStatements.Statement_UpdateArticle.setString(4, na.Place);
			DBConnection.PrepStatements.Statement_UpdateArticle.setInt(5, na.Owner.ID);
			DBConnection.PrepStatements.Statement_UpdateArticle.setString(6, ""); // Pictures
			DBConnection.PrepStatements.Statement_UpdateArticle.setString(7, na.Description);
			DBConnection.PrepStatements.Statement_UpdateArticle.setInt(8, na.ArticleID);
			
			DBConnection.PrepStatements.Statement_UpdateArticle.execute();
		} catch (SQLException e) {
			STLog.log(e);
			return null;
		}
		
		return na;
	}
}
