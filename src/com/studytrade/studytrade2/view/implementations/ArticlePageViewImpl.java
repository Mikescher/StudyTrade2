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
	protected void onBtnloginClicked(String username, String password) {
		for (ArticlePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnsearchClicked(String searchstring) {
		for (ArticlePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnlogOffClicked() {
		for (ArticlePageViewListener l : listeners)
			l.logOffClicked();
	}
}
