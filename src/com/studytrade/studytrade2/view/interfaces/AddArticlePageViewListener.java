package com.studytrade.studytrade2.view.interfaces;

public interface AddArticlePageViewListener {
	public void loginClicked(String username, String password);
	public void registerClicked();
	public void searchClicked(String searchstring);
	public void advancedSearchClicked();
	public void logOffClicked();
	public void onShowMessage(String msg);
	public void buttonProfileClicked();
	public void onAddArticle();
	public void addArticleClicked(String name, Float fprice, int intValue, String place, String desc);
}
