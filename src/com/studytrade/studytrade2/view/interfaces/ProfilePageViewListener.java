package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeMessage;

public interface ProfilePageViewListener {
	public void loginClicked(String username, String password);
	public void registerClicked();
	public void searchClicked(String searchstring);
	public void advancedSearchClicked();
	public void logOffClicked();
	public void onShowMessage(String msg);
	public void buttonProfileClicked();
	public void onAddArticle();
	public void filterArticleByCondClicked(int category);
	public void filterArticleByPlaceClicked(int place);
	
	public void messageClicked(StudyTradeMessage msg);
	public void articleClicked(StudyTradeArticle article);
	public void editArticle(StudyTradeArticle article);
	public void editProfile();
}
