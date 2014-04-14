package com.studytrade.studytrade2.model;

public class StudyTradeArticle {

	public final int ArticleID;
	public final String Name;
	public final String Place;
	public final String Description;
	
	public StudyTradeArticle(int id, String name, String place, String desc) {
		this.ArticleID = id;
		this.Name = name;
		this.Place = place;
		this.Description = desc;
	}

}
