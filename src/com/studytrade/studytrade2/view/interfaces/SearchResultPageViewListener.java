package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface SearchResultPageViewListener {
	public void loginClicked(String username, String password);
	public void searchClicked(String searchstring);
	public void logOffClicked();
	
	public void ArticleClicked(StudyTradeArticle article);
}
