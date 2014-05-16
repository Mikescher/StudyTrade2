package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.AddArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.ArticlePageViewImpl;
import com.studytrade.studytrade2.view.interfaces.AddArticlePageView;
import com.studytrade.studytrade2.view.interfaces.AddArticlePageViewListener;
import com.vaadin.ui.Component;

public class AddArticlePagePresenter extends CustomPresenter implements AddArticlePageViewListener {
    private AddArticlePageView  view;
    
    public AddArticlePagePresenter(Studytrade2UI ui, StudyTradeModel m, AddArticlePageView  v) {
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
				new AddArticlePagePresenter(UI, Model, new AddArticlePageViewImpl(Model.getLoggedInUser()));
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
	public void addArticleClicked(String name, Float fprice, int cond, String place, String desc) {
		final StudyTradeArticle starr =  Model.addArticle(name, fprice, cond, place, desc);
		
		if (starr == null) {
			showMessagePageToMainWindow("Internal error while adding article :( ");
		} else {
			showMessagePage("You have succesfully added a article", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new ArticlePagePresenter(UI, Model, new ArticlePageViewImpl(Model.getLoggedInUser(), starr));
				}
			});
		}
		

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
