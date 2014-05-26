package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageView;
import com.studytrade.studytrade2.view.interfaces.SearchResultPageViewListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class SearchResultPageViewImpl extends CustomStudyTradeComponent implements SearchResultPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private List<SearchResultPageViewListener> listeners = new ArrayList<>();

	private List<StudyTradeArticle> Articles;
	private String searchStr;
	
	public SearchResultPageViewImpl(StudyTradeUser usr, String searchstr, List<StudyTradeArticle> articles) {
		super(usr);
		
		this.Articles = articles;
		this.searchStr = searchstr;
		
		Init();
		
		setSearchString(searchstr);
	}

	@Override
	public void addListener(SearchResultPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.addComponent(new Label("<b><u>SearchResults (" + Articles.size() + "):</u></b>", ContentMode.HTML));
		
		for (final StudyTradeArticle article : Articles) {
			Panel p = new Panel();
			
			p.setId("selendebug_serepg_pnl_result");
			
			VerticalLayout l = new VerticalLayout();

			l.addComponent(new Label("<b>" + article.Name+"</b>", ContentMode.HTML));
			l.addComponent(new Label("<i>" + article.Description+"</i>", ContentMode.HTML));
			l.addComponent(new Label("at: " + article.Place, ContentMode.HTML));
			
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
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (SearchResultPageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onArticleClicked(StudyTradeArticle a) {
		for (SearchResultPageViewListener l : listeners)
			l.ArticleClicked(a);
	}

	@Override
	public String getSearchString() {
		return searchStr;
	}

	@Override
	public List<StudyTradeArticle> getArticles() {
		return Articles;
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (SearchResultPageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		// NOP
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (SearchResultPageViewListener l : listeners)
			l.onAddArticle();
	}

	@Override
	protected void onFilterCategorieClicked(int cat) {
		for (SearchResultPageViewListener l : listeners)
			l.filterArticleByCondClicked(cat);
	}

	@Override
	protected void onFilterPlacesClicked(int plc) {
		for (SearchResultPageViewListener l : listeners)
			l.filterArticleByPlaceClicked(plc);
	}
}
