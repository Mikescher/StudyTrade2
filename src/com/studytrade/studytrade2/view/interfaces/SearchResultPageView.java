package com.studytrade.studytrade2.view.interfaces;

import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface SearchResultPageView {
	public void addListener(SearchResultPageViewListener listener);
	public void onAfterInit();
	
	public String getSearchString();
	public List<StudyTradeArticle> getArticles();
}
