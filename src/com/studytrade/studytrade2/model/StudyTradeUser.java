package com.studytrade.studytrade2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyTradeUser {
	public final int ID;
	
	public final String Nickname;
	public final String Email;
	public final String Forename;
	public final String Lastname;
	public final String City;
	public final String University;
	public final String Studydirection;
	
	public StudyTradeUser(ResultSet rs) throws SQLException {
		this(
			rs.getInt("user_id"),
			rs.getString("nickname"),
			rs.getString("mail"),
			rs.getString("forename"),
			rs.getString("lastname"),
			rs.getString("city"),
			rs.getString("university"),
			rs.getString("studydirection")
		);
	}

	public StudyTradeUser(int id, String nickname, String mail, String forename, String lastname, String city, String university, String studydirection) {
		this.ID = id;
		this.Nickname = nickname;
		this.Email = mail;
		this.Forename = forename;
		this.Lastname = lastname;
		this.City = city;
		this.University = university;
		this.Studydirection = studydirection;
	}
}
