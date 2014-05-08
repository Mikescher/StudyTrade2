package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.AdvancedSearchPageViewImpl;
import com.studytrade.studytrade2.view.implementations.SearchResultPageViewImpl;
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
				new AdvancedSearchPagePresenter(UI, Model, new AdvancedSearchPageViewImpl(Model.getLogedInUser()));
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
	public void advancedSearch(String name, String direction, Float minPrice, Float maxPrice, String description, String place, String condition) {
		List<StudyTradeArticle> results = Model.getSearchResults(name);
		
		List<StudyTradeArticle> tmp = new ArrayList<>();
		
		if (! direction.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.Owner.Studydirection.toLowerCase().equals(direction.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (minPrice != null && maxPrice != null) {
			for (StudyTradeArticle sta : results) {
				if (sta.Price.compareTo(new BigDecimal(minPrice)) > 0 && sta.Price.compareTo(new BigDecimal(maxPrice)) < 0) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (! description.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.Description.toLowerCase().contains(description.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (! place.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.Place.toLowerCase().equals(place.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		if (! condition.isEmpty()) {
			for (StudyTradeArticle sta : results) {
				if (sta.getConditionString().toLowerCase().equals(condition.toLowerCase())) {
					tmp.add(sta);
				}
			}
			
			results = tmp;
		}
		
		SearchResultPageViewImpl view = new SearchResultPageViewImpl(Model.getLogedInUser(), name, results);
		new SearchResultPagePresenter(UI, Model, view);
	}
}
