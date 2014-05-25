package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import logging.STLog;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.factories.PageFactory;
import com.studytrade.studytrade2.model.LoginProblem;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeDefinitions;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.vaadin.ui.Component;

public abstract class CustomPresenter {
	public final Studytrade2UI UI;
	public final StudyTradeModel Model;
	
	public CustomPresenter(Studytrade2UI parent, StudyTradeModel m) {
		this.UI = parent;
		this.Model = m;
	}

	protected void changeView(Component c) {
		UI.setContent(c);
	}
	
	protected void onLoginClicked(String username, String password, ActionListener action) {
		LoginProblem result = Model.logIn(username, password);
		
		switch (result) {
		case NO_PROBLEM:
			action.actionPerformed(null);
			break;
		case WRONG_PWD:
			showMessagePage("Wrong password", action);
			break;
		case WRONG_USN:
			showMessagePage("Username not found", action);
			break;
		case NOT_ACTIVATED:
			showMessagePage("Profile not activated", action);
			break;
		case UNKNOWN:
			STLog.Log("INTERNAL LOGIN EXCEPTION [" + username + ", " + password + "]");
			showMessagePage("Internal Error", action);
			break;
		}
	}

	protected void onSearchClicked(String searchstring) {
		PageFactory.createSearchResultPage(this, searchstring, Model.getSearchResults(searchstring));
	}

	protected void onLogOffClicked() {
		Model.logOff();

		PageFactory.createMainPage(this);
	}
	
	protected void onRegisterClicked() {
		PageFactory.createRegisterPage(this, Model.getNicknameList());
	}

	protected void onAdvancedSearchClicked() {
		PageFactory.createAdvancedSearchPage(this);
	}

	protected void onButtonProfileClicked() {
		PageFactory.createProfilePage(this);
	}
	
	protected void showMessagePage(String msg, ActionListener ac) {
		PageFactory.createMessagePage(this, msg, ac);
	}
	
	protected void showMessagePageToMainWindow(String msg) {
		showMessagePage(msg, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PageFactory.createMainPage(CustomPresenter.this);
			}
		});
	}

	protected void onButtonAddArticleClicked() {
		PageFactory.createAddArticlePage(this);
	}
	
	protected void onButtonShowArticleClicked(StudyTradeArticle article) {
		PageFactory.createArticlePage(this, article);
	}

	protected void onButtonFilterCondClicked(int category) {
		List<StudyTradeArticle> results = Model.getSearchResults("");
		results = Model.filterSearchResults("", null, null, "", "", StudyTradeDefinitions.CONDITIONS[category], results);
		
		PageFactory.createSearchResultPage(this, "", results);
	}
	
	protected void onButtonFilterPlaceClicked(int place) {
		List<StudyTradeArticle> results = Model.getSearchResults("");
		results = Model.filterSearchResults("", null, null, "", StudyTradeDefinitions.PLACES[place], "", results);
		
		PageFactory.createSearchResultPage(this, "", results);
	}
}
