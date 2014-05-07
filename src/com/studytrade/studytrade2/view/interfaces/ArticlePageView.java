package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface ArticlePageView {
	public void addListener(ArticlePageViewListener listener);
	
	public StudyTradeArticle getArticle();
}
