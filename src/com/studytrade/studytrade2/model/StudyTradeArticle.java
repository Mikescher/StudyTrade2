package com.studytrade.studytrade2.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyTradeArticle {
	private StudyTradeModel Model;
	
	public final int ArticleID;
	public final String Name;
	public final BigDecimal Price;
	public final int Condition;
	public final String Place;
	public final String Description;
	
	public final StudyTradeUser Owner;
	
	public StudyTradeArticle(StudyTradeModel model, ResultSet rs) throws SQLException {
		this(
			model,
			rs.getInt("article_id"),
			rs.getInt("seller_id"),
			rs.getString("name"),
			rs.getBigDecimal("price"),
			rs.getInt("condition"),
			rs.getString("place"),
			rs.getString("description")
		);
	}
	
	public StudyTradeArticle(StudyTradeModel model, int id, int owner, String name, BigDecimal price, int condition, String place, String desc) {
		this.Model = model;
		
		this.ArticleID = id;
		this.Price = price;
		this.Condition = condition;
		this.Name = name;
		this.Place = place;
		this.Description = desc;
		this.Owner = Model.getUser(owner);
	}

	public String getConditionString() {
		return StudyTradeDefinitions.CONDITIONS[Condition];
	}
}
