package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.factories.PageFactory;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.interfaces.AdvancedSearchPageView;
import com.studytrade.studytrade2.view.interfaces.AdvancedSearchPageViewListener;
import com.vaadin.ui.Component;

public class AdvancedSearchPagePresenter extends CustomPresenter implements AdvancedSearchPageViewListener {
    private AdvancedSearchPageView  view;
    
    public AdvancedSearchPagePresenter(Studytrade2UI ui, StudyTradeModel m, AdvancedSearchPageView  v) {
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
				PageFactory.createAdvancedSearchPage(AdvancedSearchPagePresenter.this);
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
	public void onShowMessage(String msg) {
		showMessagePageToMainWindow(msg);
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
	public void advancedSearch(String name, String direction, Float minPrice, Float maxPrice, String description, String place, String condition) {
		List<StudyTradeArticle> results = Model.getSearchResults(name);
		results = Model.filterSearchResults(direction, minPrice, maxPrice, description, place, condition, results);
		
		PageFactory.createSearchResultPage(this, name, results);
	}

	@Override
	public void showArticleClicked(StudyTradeArticle article) {
		onButtonShowArticleClicked(article);
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
