package com.studytrade.studytrade2.pages;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.ui.VerticalLayout;

public class SearchResultPage extends CommonPage {

	private static final long serialVersionUID = 7390944448735384539L;

	public SearchResultPage(ResultSet rs) throws SQLException {
		super();

		VerticalLayout layout = new VerticalLayout();
		while (rs.next()) {
			ArticleObject article = new ArticleObject();
			article.article_object_ArticleName.setValue("test");
			article.article_object_Price.setValue(rs.getString(2));
			article.article_object_Condition.setValue(rs.getString(3));
			article.article_object_Place.setValue(rs.getString(4));
			layout.addComponent(article);
		}

		// commonpage_content_middle.addComponent(layout);
	}

}