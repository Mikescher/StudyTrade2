package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface SearchResultPageViewListener {
	public void LoginClicked(String username, String password);
	public void SearchClicked(String searchstring);
	public void LogOffClicked();
	
	public void ArticleClicked(StudyTradeArticle article);
}
