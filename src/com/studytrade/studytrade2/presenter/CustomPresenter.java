package com.studytrade.studytrade2.presenter;

import java.util.List;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.MainPageViewImpl;
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
	
	protected void onLoginClicked(String username, String password) {
		if (Model.logIn(username, password)) {
			//TODO Show (better) User Logged In Page
			MainPageViewImpl view = new MainPageViewImpl(Model.GetLogedInUser());
			new MainPagePresenter(UI, Model, view);
		} else {
			//TODO Show Wrong PW Page
		}
	}

	protected void onSearchClicked(String searchstring) {
		List<StudyTradeArticle> results = Model.getSearchResults(searchstring);
		
		SearchResultPageViewImpl view = new SearchResultPageViewImpl(Model.GetLogedInUser(), searchstring, results);
		new SearchResultPagePresenter(UI, Model, view);
	}

	protected void onLogOffClicked() {
		Model.logOff();

		MainPageViewImpl view = new MainPageViewImpl(Model.GetLogedInUser());
		new MainPagePresenter(UI, Model, view);
	}
	
	protected void onRegisterClicked() {
		List<String> usrList = Model.getNicknameList();
		
		RegisterPageViewImpl view = new RegisterPageViewImpl(Model.GetLogedInUser(), usrList);
		new RegisterPagePresenter(UI, Model, view);
	}
}
