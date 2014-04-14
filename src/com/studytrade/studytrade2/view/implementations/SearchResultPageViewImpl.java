package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageView;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageViewListener;
import com.vaadin.client.ui.layout.Margins;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class SearchResultPageViewImpl extends CustomStudyTradeComponent implements SearchResultPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private Label label_1;
	private Button button_1;
	
	private List<SearchResultPageViewListener> listeners = new ArrayList<>();

	private List<StudyTradeArticle> Articles;
	
	public SearchResultPageViewImpl(List<StudyTradeArticle> articles) {
		this.Articles = articles;
		
		Init();
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
		
		for (StudyTradeArticle article : Articles) {
			VerticalLayout l = new VerticalLayout();

			l.addComponent(new Label("ID:" + article.ArticleID));
			l.addComponent(new Label(article.Name));
			l.addComponent(new Label(article.Description));
			l.addComponent(new Label(article.Place));
			
			mainLayout.addComponent(l);
		}
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.LoginClicked(edUsername.getValue(), edPassword.getValue());
	}

	@Override
	protected void onBtnSearchClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.SearchClicked(edSearch.getValue());
	}
}
