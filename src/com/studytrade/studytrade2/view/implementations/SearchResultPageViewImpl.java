package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageView;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageViewListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class SearchResultPageViewImpl extends CustomStudyTradeComponent implements SearchResultPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private List<SearchResultPageViewListener> listeners = new ArrayList<>();

	private List<StudyTradeArticle> Articles;
	
	public SearchResultPageViewImpl(StudyTradeUser usr, String searchstr, List<StudyTradeArticle> articles) {
		super(usr);
		
		this.Articles = articles;
		
		Init();
		
		setSearchString(searchstr);
	}

	@Override
	public void addListener(SearchResultPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		// common part: create layout
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		
		for (final StudyTradeArticle article : Articles) {
			Panel p = new Panel();
			
			VerticalLayout l = new VerticalLayout();

			l.addComponent(new Label("ID:" + article.ArticleID));
			l.addComponent(new Label(article.Name));
			l.addComponent(new Label(article.Description));
			l.addComponent(new Label(article.Place));
			
			p.setContent(l);
			
			p.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 5665965853296644399L;

				@Override
				public void click(ClickEvent event) {
					onArticleClicked(article);
				}
			});
			
			mainLayout.addComponent(p);
		}
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (SearchResultPageViewListener l : listeners)
			l.LoginClicked(username, password);
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (SearchResultPageViewListener l : listeners)
			l.SearchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.LogOffClicked();
	}

	protected void onArticleClicked(StudyTradeArticle a) {
		for (SearchResultPageViewListener l : listeners)
			l.ArticleClicked(a);
	}
}
