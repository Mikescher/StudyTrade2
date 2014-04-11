package com.studytrade.studytrade2.presenter;

import java.sql.ResultSet;

import com.studytrade.studytrade2.model.ArticleManagement;

public class Searching {
	private String searchstring;

	public ResultSet SearchTheArticle(String string) {
		searchstring = string;

		ResultSet rs = AnalyzeString(searchstring);
		return rs;
	}

	public ResultSet AnalyzeString(String string) {
		// TODO String analysieren !!
		ArticleManagement article = new ArticleManagement();
		ResultSet rs = article.ArticleSearch(searchstring, "", "", "", "");
		return rs;

	}

}
