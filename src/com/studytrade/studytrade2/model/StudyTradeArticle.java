package com.studytrade.studytrade2.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyTradeArticle {
	public final int ArticleID;
	public final String Name;
	public final BigDecimal Price;
	public final int Condition;
	public final String Place;
	public final String Description;
	
	public final StudyTradeUser Owner;
	
	public StudyTradeArticle(ResultSet rs, StudyTradeUser owner) throws SQLException {
		this(
			rs.getInt("article_id"),
			rs.getString("name"),
			rs.getBigDecimal("price"),
			rs.getInt("condition"),
			rs.getString("place"),
			rs.getString("description"),
			owner
		);
	}
	
	public StudyTradeArticle(int id, String name, BigDecimal price, int condition, String place, String desc, StudyTradeUser owner) {
		this.ArticleID = id;
		this.Price = price;
		this.Condition = condition;
		this.Name = name;
		this.Place = place;
		this.Description = desc;
		this.Owner = owner;
	}

	public String getConditionString() {
		return StudyTradeDefinitions.CONDITIONS[Condition];
	}
}
