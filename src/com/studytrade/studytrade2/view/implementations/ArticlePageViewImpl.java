package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.ArticlePageView;
import com.studytrade.studytrade2.view.interfaces.ArticlePageViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class ArticlePageViewImpl extends CustomStudyTradeComponent implements ArticlePageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private StudyTradeArticle article;
	
	private List<ArticlePageViewListener> listeners = new ArrayList<>();

	private Button btnSeller;
	
	public ArticlePageViewImpl(StudyTradeUser usr, StudyTradeArticle article) {
		super(usr);
		
		this.article = article;
		
		Init();
	}
	
	@Override
	public void addListener(ArticlePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setWidth("100%");
		
		mainLayout.addComponent(new Label("Name: " + article.Name));
		mainLayout.addComponent(new Label("Description:\r\n" + article.Description));
		mainLayout.addComponent(new Label("Ort: " + article.Place));
		mainLayout.addComponent(new Label("Condition: " + article.Condition));
		mainLayout.addComponent(new Label("Price: " + article.Price));
		
		mainLayout.addComponent(new HorizontalLayout(new Label("Seller: "), btnSeller = new Button(article.Owner.Nickname)));
		btnSeller.setStyleName("link");
		btnSeller.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				onLinkSellerClicked();
			}
		});
		
		return mainLayout;
	}

	protected void onLinkSellerClicked() {
		for (ArticlePageViewListener l : listeners)
			l.onSellerClicked(article.Owner);
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (ArticlePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (ArticlePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (ArticlePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (ArticlePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	public StudyTradeArticle getArticle() {
		return article;
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (ArticlePageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (ArticlePageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (ArticlePageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		// NOP
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (ArticlePageViewListener l : listeners)
			l.onAddArticle();
	}
}
