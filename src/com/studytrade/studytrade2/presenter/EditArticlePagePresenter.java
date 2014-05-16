package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.ArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.EditArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.ProfilePageViewImpl;
import com.studytrade.studytrade2.view.interfaces.EditArticlePageView;
import com.studytrade.studytrade2.view.interfaces.EditArticlePageViewListener;
import com.vaadin.ui.Component;

public class EditArticlePagePresenter extends CustomPresenter implements EditArticlePageViewListener {
    private EditArticlePageView  view;
    
    public EditArticlePagePresenter(Studytrade2UI ui, StudyTradeModel m, EditArticlePageView  v) {
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
				new EditArticlePagePresenter(UI, Model, new EditArticlePageViewImpl(Model.getLoggedInUser(), view.getArticle()));
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
	public void deleteArticle(StudyTradeArticle article) {
		Model.deleteArticle(article);
		
		showMessagePage("Article deleted", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfilePagePresenter(UI, Model, new ProfilePageViewImpl(Model.getLoggedInUser()));
			}
		});
	}

	@Override
	public void updateArticle(StudyTradeArticle article, String name, Float fprice, int cond, String place, String desc) {
		final StudyTradeArticle newarticle = Model.updateArticle(article, name, fprice, cond, place, desc);
		
		showMessagePage("Article updated", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ArticlePagePresenter(UI, Model, new ArticlePageViewImpl(Model.getLoggedInUser(), newarticle));
			}
		});
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
