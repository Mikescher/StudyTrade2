package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logging.STLog;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.LoginProblem;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.AdvancedSearchPageViewImpl;
import com.studytrade.studytrade2.view.implementations.MainPageViewImpl;
import com.studytrade.studytrade2.view.implementations.MessagePageViewImpl;
import com.studytrade.studytrade2.view.implementations.RegisterPageViewImpl;
import com.studytrade.studytrade2.view.implementations.SearchResultPageViewImpl;
import com.vaadin.ui.Component;

public abstract class CustomPresenter {
	protected final Studytrade2UI UI;
	protected final StudyTradeModel Model;
	
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
		new SearchResultPagePresenter(UI, Model, new SearchResultPageViewImpl(Model.getLogedInUser(), searchstring, Model.getSearchResults(searchstring)));
	}

	protected void onLogOffClicked() {
		Model.logOff();

		new MainPagePresenter(UI, Model, new MainPageViewImpl(Model.getLogedInUser()));
	}
	
	protected void onRegisterClicked() {
		new RegisterPagePresenter(UI, Model, new RegisterPageViewImpl(Model.getLogedInUser(),  Model.getNicknameList()));
	}

	protected void onAdvancedSearchClicked() {
		new AdvancedSearchPagePresenter(UI, Model, new AdvancedSearchPageViewImpl(Model.getLogedInUser()));
	}

	protected void onButtonProfileClicked() {
		//TODO Show Profile
	}
	
	protected void showMessagePage(String msg, ActionListener ac) {
		new MessagePagePresenter(UI, Model, new MessagePageViewImpl(Model.getLogedInUser(), msg, ac));
	}
	
	protected void showMessagePageToMainWindow(String msg) {
		showMessagePage(msg, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPagePresenter(UI, Model, new MainPageViewImpl(Model.getLogedInUser()));
			}
		});
	}
}
