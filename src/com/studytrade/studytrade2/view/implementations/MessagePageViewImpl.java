package com.studytrade.studytrade2.view.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.MessagePageView;
import com.studytrade.studytrade2.view.interfaces.MessagePageViewListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

public class MessagePageViewImpl extends CustomStudyTradeComponent implements MessagePageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private Label label_1;
	private Button button_1;
	
	private List<MessagePageViewListener> listeners = new ArrayList<>();

	private String text;
	private ActionListener pageEvent;
	
	public MessagePageViewImpl(StudyTradeUser usr, String txt, ActionListener okAction) {
		super(usr);
		
		this.text = txt;
		this.pageEvent = okAction;
		
		Init();
	}
	
	@Override
	public void addListener(MessagePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		
		mainLayout.setWidth("100%");
		
		// label_1
		label_1 = new Label();
		label_1.setValue(text);
		mainLayout.addComponent(label_1, "top:40.0px;left:79.0px;");
		
		// button_1
		button_1 = new Button();
		button_1.setCaption("OK");
		button_1.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 4578944120001881810L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageEvent.actionPerformed(new ActionEvent(event, 0, ""));
			}
		});
		mainLayout.addComponent(button_1, "top:60.0px;left:95.0px;");
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (MessagePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (MessagePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (MessagePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (MessagePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	public String getMessage() {
		return text;
	}

	@Override
	public ActionListener getAction() {
		return pageEvent;
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (MessagePageViewListener l : listeners)
			l.advancedSearchClicked();
	}
}
