package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface EditArticlePageView {
	public void addListener(EditArticlePageViewListener listener);
	public void onAfterInit();
	
	public StudyTradeArticle getArticle();
}
