package com.studytrade.studytrade2.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyTradeMessage {
	public final int ID;
	
	public final StudyTradeUser Sender;
	public final StudyTradeUser Target;
	public final String MessageHeader;
	public final String MessageText;
	public final Date Timestamp;
	public final boolean MessageRead;
	
	public StudyTradeMessage(StudyTradeModel model, ResultSet rs) throws SQLException {
		this(
			model,
			rs.getInt("message_id"),
			rs.getInt("sender_id"),
			rs.getInt("target_id"),
			rs.getString("message_header"),
			rs.getString("message_text"),
			rs.getDate("message_timestamp"),
			rs.getInt("message_read") != 0
		);
	}

	public StudyTradeMessage(StudyTradeModel model, int id, int sender, int target, String header, String text, Date timestamp, boolean read) {
		this.ID = id;
		this.Sender = model.getUser(sender);
		this.Target = model.getUser(target);
		this.MessageHeader = header;
		this.MessageText = text;
		this.Timestamp = timestamp;
		this.MessageRead = read;
	}
}
