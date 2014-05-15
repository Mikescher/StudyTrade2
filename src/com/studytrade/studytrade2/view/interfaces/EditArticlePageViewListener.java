package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface EditArticlePageViewListener {
	public void loginClicked(String username, String password);
	public void registerClicked();
	public void searchClicked(String searchstring);
	public void advancedSearchClicked();
	public void logOffClicked();
	public void onShowMessage(String msg);
	public void buttonProfileClicked();
	public void onAddArticle();
	
	public void deleteArticle(StudyTradeArticle article);
	public void updateArticle(StudyTradeArticle article, String name, Float fprice, int cond, String place, String desc);
}
