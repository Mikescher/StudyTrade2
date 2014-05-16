package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeMessage;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.ArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.EditArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.ProfilePageViewImpl;
import com.studytrade.studytrade2.view.implementations.UserMessagePageViewImpl;
import com.studytrade.studytrade2.view.interfaces.ProfilePageView;
import com.studytrade.studytrade2.view.interfaces.ProfilePageViewListener;
import com.vaadin.ui.Component;

public class ProfilePagePresenter extends CustomPresenter implements ProfilePageViewListener {
    private ProfilePageView  view;
    
    public ProfilePagePresenter(Studytrade2UI ui, StudyTradeModel m, ProfilePageView  v) {
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
				new ProfilePagePresenter(UI, Model, new ProfilePageViewImpl(Model.getLoggedInUser()));
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
	public void messageClicked(StudyTradeMessage msg) {
		new UserMessagePagePresenter(UI, Model, new UserMessagePageViewImpl(Model.getLoggedInUser(), msg, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfilePagePresenter(UI, Model, new ProfilePageViewImpl(Model.getLoggedInUser()));
			}
		}));
	}

	@Override
	public void articleClicked(StudyTradeArticle article) {
		new ArticlePagePresenter(UI, Model, new ArticlePageViewImpl(Model.getLoggedInUser(), article));
	}

	@Override
	public void editArticle(StudyTradeArticle article) {
		new EditArticlePagePresenter(UI, Model, new EditArticlePageViewImpl(Model.getLoggedInUser(), article));
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
