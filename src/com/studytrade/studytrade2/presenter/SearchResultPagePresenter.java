package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.factories.PageFactory;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
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
        
        view.onAfterInit();
    }

	@Override
	public void loginClicked(String username, String password) {
		onLoginClicked(username, password, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PageFactory.createSearchResultPage(SearchResultPagePresenter.this, view.getSearchString(), view.getArticles());
			}
		});
	}

	@Override
	public void searchClicked(String searchstring) {
		onSearchClicked(searchstring);
	}

	@Override
	public void advancedSearchClicked() {
		onAdvancedSearchClicked();
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
	public void buttonProfileClicked() {
		onButtonProfileClicked();
	}

	@Override
	public void onAddArticle() {
		onButtonAddArticleClicked();
	}

	@Override
	public void onShowMessage(String msg) {
		showMessagePageToMainWindow(msg);
	}

	@Override
	public void ArticleClicked(StudyTradeArticle article) {
		PageFactory.createArticlePage(this, article);
	}
	
	@Override
	public void filterArticleByCondClicked(int category) {
		onButtonFilterCondClicked(category);
	}

	@Override
	public void filterArticleByPlaceClicked(int place) {
		onButtonFilterPlaceClicked(place);
	}
}
