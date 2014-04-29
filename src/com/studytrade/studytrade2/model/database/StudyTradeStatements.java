package com.studytrade.studytrade2.model.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			Statement_UserByNickname = c.prepareStatement(loadResource("/Statement_UserByNickname.sql"));
			Statement_FindArticle = c.prepareStatement(loadResource("/Statement_FindArticle.sql"));
		} catch (SQLException e) {
			STLog.log(e);
		}
	}
	
	private String loadResource(String rpath) {
		try {
			StringBuffer sb = new StringBuffer();
	
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(rpath), "UTF-8"));
			for (int c = br.read(); c != -1; c = br.read()) 
				sb.append((char)c);
	
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}
}
