package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeModel;
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
		if (Model.logIn(username, password)) {
			action.actionPerformed(null);
		} else {
			showMessagePage("Password or Username wrong.", action);
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
		List<String> usrList = Model.getNicknameList();
		
		RegisterPageViewImpl view = new RegisterPageViewImpl(Model.getLogedInUser(), usrList);
		new RegisterPagePresenter(UI, Model, view);
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
