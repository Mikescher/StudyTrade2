package com.studytrade.studytrade2.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudyTradeUser {
	private StudyTradeModel Model;
	
	public final int ID;
	
	public final String Nickname;
	public final String Email;
	public final String Forename;
	public final String Lastname;
	public final String City;
	public final String University;
	public final String Studydirection;
	public final String Passwordhash;
	public final boolean Activated;
	
	public StudyTradeUser(StudyTradeModel model, ResultSet rs) throws SQLException {
		this(
			model,
			rs.getInt("user_id"),
			rs.getString("nickname"),
			rs.getString("mail"),
			rs.getString("forename"),
			rs.getString("lastname"),
			rs.getString("city"),
			rs.getString("university"),
			rs.getString("studydirection"),
			rs.getString("passwordhash"),
			rs.getInt("activated") != 0
		);
	}

	public StudyTradeUser(StudyTradeModel model, int id, String nickname, String mail, String forename, String lastname, String city, String university, String studydirection, String pwhash, boolean activated) {
		this.Model = model;

		this.ID = id;
		this.Passwordhash = pwhash;
		this.Nickname = nickname;
		this.Email = mail;
		this.Forename = forename;
		this.Lastname = lastname;
		this.City = city;
		this.University = university;
		this.Studydirection = studydirection;
		this.Activated = activated;
	}
	
	public List<StudyTradeMessage> getSendMessages() {
		return Model.getMessagesBySender(this, false);
	}
	
	public List<StudyTradeMessage> getRecievedMessages() {
		return Model.getMessagesByTarget(this, false);
	}
	
	public List<StudyTradeMessage> getUnreadMessages() {
		return Model.getMessagesByTarget(this, true);
	}
}
