package com.studytrade.studytrade2.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class StudyTradeMessage {
	private StudyTradeModel Model;
	
	public final int ID;
	
	public final StudyTradeUser Sender;
	public final StudyTradeUser Target;
	public final String MessageHeader;
	public final String MessageText;
	public final Timestamp Timestamp;
	public final boolean MessageRead;
	
	public StudyTradeMessage(StudyTradeModel model, ResultSet rs) throws SQLException {
		this(
			model,
			rs.getInt("message_id"),
			rs.getInt("sender_id"),
			rs.getInt("target_id"),
			rs.getString("message_header"),
			rs.getString("message_text"),
			rs.getTimestamp("message_timestamp"),
			rs.getInt("message_read") != 0
		);
	}

	public StudyTradeMessage(StudyTradeModel model, int id, int sender, int target, String header, String text, Timestamp timestamp, boolean read) {
		this.Model = model;
		
		this.ID = id;
		this.Sender = Model.getUser(sender);
		this.Target = Model.getUser(target);
		this.MessageHeader = header;
		this.MessageText = text;
		this.Timestamp = timestamp;
		this.MessageRead = read;
	}

	public String getDisplayString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		
		return String.format("[%s] %s : >> %s << ", sdf.format(Timestamp), Sender.Nickname, getShortenedHeader());
	}
	
	public String getShortenedHeader() {
		if (MessageHeader.length() > 20) {
			return MessageHeader.substring(0, 17) + "...";
		} else {
			return MessageHeader;
		}
	}
}
