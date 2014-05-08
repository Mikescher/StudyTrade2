package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.implementations.ArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.UserPageViewImpl;
import com.studytrade.studytrade2.view.interfaces.ArticlePageView;
import com.studytrade.studytrade2.view.interfaces.ArticlePageViewListener;
import com.vaadin.ui.Component;

public class ArticlePagePresenter extends CustomPresenter implements ArticlePageViewListener {
    private ArticlePageView  view;
    
    public ArticlePagePresenter(Studytrade2UI ui, StudyTradeModel m, ArticlePageView  v) {
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
				new ArticlePagePresenter(UI, Model, new ArticlePageViewImpl(Model.getLogedInUser(), view.getArticle()));
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
	public void onSellerClicked(StudyTradeUser seller) {
		new UserPagePresenter(UI, Model, new UserPageViewImpl(Model.getLogedInUser(), seller));
	}
}
