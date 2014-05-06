package com.studytrade.studytrade2.presenter;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.ArticlePageViewImpl;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageView;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageViewListener;
import com.vaadin.ui.Component;

public class SearchResultPagePresenter extends CustomPresenter implements SearchResultPageViewListener {
    private SearchResultPageView  view;
    
    public SearchResultPagePresenter(Studytrade2UI ui, StudyTradeModel m, SearchResultPageView v) {
    	super(ui, m);
    	
        this.view  = v;

        view.addListener(this);
        
        changeView((Component)view);
    }

	@Override
	public void loginClicked(String username, String password) {
		onLoginClicked(username, password);
	}

	@Override
	public void searchClicked(String searchstring) {
		onSearchClicked(searchstring);
	}

	@Override
	public void logOffClicked() {
		onLogOffClicked();
	}

	@Override
	public void registerClicked() {
		onRegisterClicked();
	}

	@Override
	public void ArticleClicked(StudyTradeArticle article) {
		ArticlePageViewImpl view = new ArticlePageViewImpl(Model.GetLogedInUser(), article);
		new ArticlePagePresenter(UI, Model, view);
	}
}
