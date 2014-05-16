package com.studytrade.studytrade2.view.implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeMessage;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.UserMessagePageView;
import com.studytrade.studytrade2.view.interfaces.UserMessagePageViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UserMessagePageViewImpl extends CustomStudyTradeComponent implements UserMessagePageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private List<UserMessagePageViewListener> listeners = new ArrayList<>();

	private final StudyTradeMessage Message;
	private ActionListener pageEvent;

	public UserMessagePageViewImpl(StudyTradeUser usr, StudyTradeMessage msg, ActionListener evt) {
		super(usr);

		this.Message = msg;
		this.pageEvent = evt;

		if (Message.Target.ID == User.ID) {
			Message.markAsRead();
		}

		Init();
	}

	@Override
	public void addListener(UserMessagePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();

		mainLayout.setWidth("100%");

		Button btnSender;
		Button btnTarget;
		
		mainLayout.addComponent(new HorizontalLayout(
				new Label("Message from "),
				btnSender = new Button(Message.Sender.Nickname),
				new Label(" to "),
				btnTarget = new Button(Message.Target.Nickname),
				new Label(" (" + Message.getFormattedDate() + ")")
		));
		
		btnSender.setStyleName("link");
		btnTarget.setStyleName("link");
		
		btnSender.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7888183547296977508L;

			@Override
			public void buttonClick(ClickEvent event) {
				onSenderClicked();
			}
		});
		
		btnTarget.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7888183547296977508L;

			@Override
			public void buttonClick(ClickEvent event) {
				onTargetClicked();
			}
		});

		TextField edHeader = new TextField();
		edHeader.setValue(Message.MessageHeader);
		TextArea memoText = new TextArea();
		memoText.setValue(Message.MessageText);

		mainLayout.addComponent(edHeader);
		mainLayout.addComponent(memoText);

		Button btnOK = new Button("OK");
		mainLayout.addComponent(btnOK);
		btnOK.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 7888183547296977508L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageEvent.actionPerformed(new ActionEvent(this, 0, null));
			}
		});

		return mainLayout;
	}

	private void onSenderClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.userClicked(Message.Sender);
	}

	private void onTargetClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.userClicked(Message.Target);
	}
	
	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (UserMessagePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (UserMessagePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (UserMessagePageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		// NOP
	}

	@Override
	public StudyTradeMessage getMessage() {
		return Message;
	}

	@Override
	public ActionListener getOKEvent() {
		return pageEvent;
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (UserMessagePageViewListener l : listeners)
			l.onAddArticle();
	}

	@Override
	protected void onArticleClicked(StudyTradeArticle artc) {
		for (UserMessagePageViewListener l : listeners)
			l.showArticleClicked(artc);
	}

	@Override
	protected void onFilterCategorieClicked(int cat) {
		for (UserMessagePageViewListener l : listeners)
			l.filterArticleByCondClicked(cat);
	}

	@Override
	protected void onFilterPlacesClicked(int plc) {
		for (UserMessagePageViewListener l : listeners)
			l.filterArticleByPlaceClicked(plc);
	}
}
