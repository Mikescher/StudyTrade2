package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.UserPageView;
import com.studytrade.studytrade2.view.interfaces.UserPageViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UserPageViewImpl extends CustomStudyTradeComponent implements UserPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private List<UserPageViewListener> listeners = new ArrayList<>();

	private TextField edMsgHeader;
	private TextArea memoMsgText;
	private Button btnSendMessage;

	private final StudyTradeUser displayUser;

	public UserPageViewImpl(StudyTradeUser usr, StudyTradeUser dispUser) {
		super(usr);

		this.displayUser = dispUser;

		Init();
	}

	@Override
	public void addListener(UserPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();

		mainLayout.setWidth("100%");

		mainLayout.addComponent(new Label("Userpage from " + displayUser.Nickname));

		mainLayout.addComponent(new Label("Name: " + displayUser.Forename + " " + displayUser.Lastname));
		mainLayout.addComponent(new Label("City: " + displayUser.City));
		mainLayout.addComponent(new Label("Studydirection: " + displayUser.Studydirection));
		mainLayout.addComponent(new Label("University: " + displayUser.University));

		mainLayout.addComponent(new Label("Send Message to User:"));

		mainLayout.addComponent(new HorizontalLayout(new Label("Header"), edMsgHeader = new TextField()));
		mainLayout.addComponent(new HorizontalLayout(new Label("Text"), memoMsgText = new TextArea()));
		mainLayout.addComponent(btnSendMessage = new Button("Send"));

		btnSendMessage.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 2421638485684843164L;

			@Override
			public void buttonClick(ClickEvent event) {
				onBtnSendMessageClicked();
			}
		});

		return mainLayout;
	}

	private void onBtnSendMessageClicked() {
		for (UserPageViewListener l : listeners)
			l.sendUserMessageClickded(User, displayUser, edMsgHeader.getValue(), memoMsgText.getValue());
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (UserPageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (UserPageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (UserPageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (UserPageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (UserPageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (UserPageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (UserPageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public StudyTradeUser getDisplayUser() {
		return displayUser;
	}

	@Override
	public void onAfterInit() {
		forceLoggedIn();
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (UserPageViewListener l : listeners)
			l.onAddArticle();
	}
}
