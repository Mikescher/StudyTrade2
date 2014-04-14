package com.studytrade.studytrade2.presenter;

import java.util.List;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.SearchResultPageViewImpl;
import com.vaadin.ui.Component;

public abstract class CustomPresenter {
	protected final Studytrade2UI UI;
	protected final StudyTradeModel Model;
	
	public CustomPresenter(Studytrade2UI parent, StudyTradeModel m) {
		this.UI = parent;
		this.Model = m;
	}

	protected void ChangeView(Component c) {
		UI.setContent(c);
	}
	
	protected void OnLoginClicked(String username, String password) {
		// TODO Auto-generated method stub
	}

	protected void OnSearchClicked(String searchstring) {
		List<StudyTradeArticle> results = Model.GetSearchResults(searchstring);
		
		SearchResultPageViewImpl view = new SearchResultPageViewImpl(results);
		
		new SearchResultPagePresenter(UI, Model, view);
		
		ChangeView(view);
	}
}
