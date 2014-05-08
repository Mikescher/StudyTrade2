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
	public PreparedStatement Statement_UserByID;
	public PreparedStatement Statement_FindArticle;
	public PreparedStatement Statement_ListNicknames;
	public PreparedStatement Statement_InsertNewUser;
	public PreparedStatement Statement_CreateMessage;
	public PreparedStatement Statement_MessagesBySender;
	public PreparedStatement Statement_MessagesByTarget;
	public PreparedStatement Statement_UnreadMessagesBySender;
	public PreparedStatement Statement_UnreadMessagesByTarget;
	
	public StudyTradeStatements(Connection conn) {
		prepare(conn);
	}

	private void prepare(Connection c) {
		try {
			Statement_UserByNickname = c.prepareStatement(loadResource("/Statement_UserByNickname.sql"));
			Statement_UserByID = c.prepareStatement(loadResource("/Statement_UserByID.sql"));
			Statement_FindArticle = c.prepareStatement(loadResource("/Statement_FindArticle.sql"));
			Statement_ListNicknames = c.prepareStatement(loadResource("/Statement_ListNicknames.sql"));
			Statement_InsertNewUser = c.prepareStatement(loadResource("/Statement_InsertNewUser.sql"));
			Statement_CreateMessage = c.prepareStatement(loadResource("/Statement_CreateMessage.sql"));
			Statement_MessagesBySender = c.prepareStatement(loadResource("/Statement_MessagesBySender.sql"));
			Statement_MessagesByTarget = c.prepareStatement(loadResource("/Statement_MessagesByTarget.sql"));
			Statement_UnreadMessagesBySender = c.prepareStatement(loadResource("/Statement_UnreadMessagesBySender.sql"));
			Statement_UnreadMessagesByTarget = c.prepareStatement(loadResource("/Statement_UnreadMessagesByTarget.sql"));
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
