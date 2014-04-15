package com.studytrade.studytrade2.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import logging.STLog;

public class StudyTradeStatements {
	public PreparedStatement Statement_UserByNickname;
	public PreparedStatement Statement_FindArticle;
	
	public StudyTradeStatements(Connection conn) {
		prepare(conn);
	}

	private void prepare(Connection c) {
		try {
			Statement_UserByNickname = c.prepareStatement("SELECT * FROM users WHERE nickname LIKE ? LIMIT 1");
			Statement_FindArticle = c.prepareStatement("SELECT * FROM articles");
		} catch (SQLException e) {
			STLog.log(e);
		}
	}
} 
