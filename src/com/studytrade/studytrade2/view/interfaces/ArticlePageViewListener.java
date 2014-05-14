package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeUser;

public interface ArticlePageViewListener {
	public void loginClicked(String username, String password);
	public void registerClicked();
	public void searchClicked(String searchstring);
	public void advancedSearchClicked();
	public void logOffClicked();
	public void onShowMessage(String msg);
	public void buttonProfileClicked();
	public void onAddArticle();
	
	public void onSellerClicked(StudyTradeUser seller);
}
