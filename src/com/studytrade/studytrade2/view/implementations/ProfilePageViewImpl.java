package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeMessage;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.ProfilePageView;
import com.studytrade.studytrade2.view.interfaces.ProfilePageViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class ProfilePageViewImpl extends CustomStudyTradeComponent implements ProfilePageView {
	private static final long serialVersionUID = -2103599367448946610L;
	
	private List<ProfilePageViewListener> listeners = new ArrayList<>();

	private Button btnEditProfile;
	
	public ProfilePageViewImpl(StudyTradeUser usr) {
		super(usr);
		
		Init();
	}
	
	@Override
	public void addListener(ProfilePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setWidth("100%");

		mainLayout.addComponent(new Label("Hello " + User.Forename + " " + User.Lastname + " (aka " + User.Nickname + ")"));
		
		mainLayout.addComponent(new Label("Name: " + User.Forename + " " + User.Lastname));
		mainLayout.addComponent(new Label("City: " + User.City));
		mainLayout.addComponent(new Label("Studydirection: " + User.Studydirection));
		mainLayout.addComponent(new Label("University: " + User.University));
		
		mainLayout.addComponent(btnEditProfile = new Button("edit Profile"));
		btnEditProfile.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4097815283684555932L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Add Edit Profile Page
			}
		});
		
		List<StudyTradeMessage> msg_unread = User.getUnreadMessages();
		List<StudyTradeMessage> msg_send = User.getSendMessages();
		List<StudyTradeMessage> msg_recieved = User.getRecievedMessages();
		
		mainLayout.addComponent(new Label("Unread Messages (" + msg_unread.size() + "):"));
		for (StudyTradeMessage msg : msg_unread) {
			Button btnLink = new Button(msg.getDisplayString());
			mainLayout.addComponent(btnLink);
			btnLink.setStyleName("link");
			btnLink.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Open Msg
				}
			});
		}
		
		
		mainLayout.addComponent(new Label("Send Messages (" + msg_send.size() + "):"));
		for (StudyTradeMessage msg : msg_send) {
			Button btnLink = new Button(msg.getDisplayString());
			mainLayout.addComponent(btnLink);
			btnLink.setStyleName("link");
			btnLink.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Open Msg
				}
			});
		}
		
		
		mainLayout.addComponent(new Label("Recieved Messages (" + msg_recieved.size() + "):"));
		for (StudyTradeMessage msg : msg_recieved) {
			Button btnLink = new Button(msg.getDisplayString());
			mainLayout.addComponent(btnLink);
			btnLink.setStyleName("link");
			btnLink.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Open Msg
				}
			});
		}
		
		
		return mainLayout;
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (ProfilePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (ProfilePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (ProfilePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (ProfilePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (ProfilePageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (ProfilePageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (ProfilePageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		forceLoggedIn();
	}
}
