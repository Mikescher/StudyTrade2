package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;

public interface AddArticlePageViewListener {
	public void loginClicked(String username, String password);
	public void registerClicked();
	public void searchClicked(String searchstring);
	public void advancedSearchClicked();
	public void logOffClicked();
	public void onShowMessage(String msg);
	public void buttonProfileClicked();
	public void onAddArticle();
	public void showArticleClicked(StudyTradeArticle article);
	public void filterArticleByCondClicked(int category);
	public void filterArticleByPlaceClicked(int place);
	
	public void addArticleClicked(String name, Float fprice, int intValue, String place, String desc);
}
