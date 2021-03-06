package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.MainPageView;
import com.studytrade.studytrade2.view.interfaces.MainPageViewListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class MainPageViewImpl extends CustomStudyTradeComponent implements MainPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private Label label_1;
//	private Button button_1;
	
	private List<MainPageViewListener> listeners = new ArrayList<>();

	public MainPageViewImpl(StudyTradeUser usr) {
		super(usr);
		
		Init();
	}
	
	@Override
	public void addListener(MainPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		
		mainLayout.setWidth("100%");
		
//		// button_1
//		button_1 = new Button();
//		button_1.setCaption("HELLLOOOOO");
//		mainLayout.addComponent(button_1, "top:60.0px;left:95.0px;");
		
		// label_1
		label_1 = new Label();
		label_1.setValue("Welcome to StudyTrade");
		mainLayout.addComponent(label_1, "top:40.0px;left:79.0px;");
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (MainPageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (MainPageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (MainPageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (MainPageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (MainPageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (MainPageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (MainPageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		// NOP
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (MainPageViewListener l : listeners)
			l.onAddArticle();
	}

	@Override
	protected void onArticleClicked(StudyTradeArticle artc) {
		for (MainPageViewListener l : listeners)
			l.showArticleClicked(artc);
	}

	@Override
	protected void onFilterCategorieClicked(int cat) {
		for (MainPageViewListener l : listeners)
			l.filterArticleByCondClicked(cat);
	}

	@Override
	protected void onFilterPlacesClicked(int plc) {
		for (MainPageViewListener l : listeners)
			l.filterArticleByPlaceClicked(plc);
	}
}
