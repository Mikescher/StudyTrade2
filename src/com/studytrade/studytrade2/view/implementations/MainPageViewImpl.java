package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.view.interfaces.MainPageView;
import com.studytrade.studytrade2.view.interfaces.MainPageViewListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class MainPageViewImpl extends CustomStudyTradeComponent implements MainPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private Label label_1;
	private Button button_1;
	
	private List<MainPageViewListener> listeners = new ArrayList<>();

	public MainPageViewImpl() {
		Init();
	}
	
	@Override
	public void addListener(MainPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		
		// button_1
		button_1 = new Button();
		button_1.setCaption("HELLLOOOOO");
		button_1.setImmediate(false);
		button_1.setWidth("-1px");
		button_1.setHeight("-1px");
		mainLayout.addComponent(button_1, "top:60.0px;left:95.0px;");
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("I am the MainPage");
		mainLayout.addComponent(label_1, "top:40.0px;left:79.0px;");
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked() {
		for (MainPageViewListener l : listeners)
			l.LoginClicked(edUsername.getValue(), edPassword.getValue());
	}

	@Override
	protected void onBtnSearchClicked() {
		for (MainPageViewListener l : listeners)
			l.SearchClicked(edSearch.getValue());
	}
}
