package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.ArticlePageView;
import com.studytrade.studytrade2.view.interfaces.ArticlePageViewListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class ArticlePageViewImpl extends CustomStudyTradeComponent implements ArticlePageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private StudyTradeArticle article;
	
	private List<ArticlePageViewListener> listeners = new ArrayList<>();

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
		mainLayout.addComponent(new Label("Verkaufer: " + article.Owner.Nickname));
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (ArticlePageViewListener l : listeners)
			l.LoginClicked(username, password);
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (ArticlePageViewListener l : listeners)
			l.SearchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (ArticlePageViewListener l : listeners)
			l.LogOffClicked();
	}
}
