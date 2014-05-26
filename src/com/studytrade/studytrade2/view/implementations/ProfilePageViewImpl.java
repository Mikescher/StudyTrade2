package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeMessage;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.ProfilePageView;
import com.studytrade.studytrade2.view.interfaces.ProfilePageViewListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
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
		
		//#######################################

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
				onEditProfileClicked();
			}
		});
		
		//#######################################
		
		List<StudyTradeMessage> msg_unread = User.getUnreadMessages();
		List<StudyTradeMessage> msg_send = User.getSendMessages();
		List<StudyTradeMessage> msg_recieved = User.getRecievedMessages();
		
		mainLayout.addComponent(new Label("Unread Messages (" + msg_unread.size() + "):"));
		for (final StudyTradeMessage msg : msg_unread) {
			Button btnLink = new Button(msg.getDisplayString());
			mainLayout.addComponent(btnLink);
			btnLink.setStyleName("link");
			btnLink.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onMessageClicked(msg);
				}
			});
		}
		
		//#######################################
		
		mainLayout.addComponent(new Label("Articles:"));
		
		for (final StudyTradeArticle article : User.getArticles()) {
			Panel p = new Panel();
			
			VerticalLayout l = new VerticalLayout();

			l.addComponent(new Label("ID:" + article.ArticleID));
			l.addComponent(new Label(article.Name));
			l.addComponent(new Label(article.Description));
			l.addComponent(new Label(article.Place));
			
			p.setContent(l);
			
			HorizontalLayout hl = new HorizontalLayout();
			
			Button btnArticleShow = new Button("Show");
			btnArticleShow.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 2646188737118750380L;
				
				@Override
				public void buttonClick(ClickEvent event) {
					onArticleClicked(article);
				}
			});
			hl.addComponent(btnArticleShow);
			
			Button btnArticleEdit = new Button("Edit");
			btnArticleEdit.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 2646188737118750380L;
				
				@Override
				public void buttonClick(ClickEvent event) {
					onEditArticleClicked(article);
				}
			});
			
			hl.addComponent(btnArticleEdit);
			
			l.addComponent(hl);
			
			mainLayout.addComponent(p);
		}
		
		//#######################################
		
		mainLayout.addComponent(new Label("Send Messages (" + msg_send.size() + "):"));
		for (final StudyTradeMessage msg : msg_send) {
			Button btnLink = new Button(msg.getDisplayString());
			mainLayout.addComponent(btnLink);
			btnLink.setStyleName("link");
			btnLink.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onMessageClicked(msg);
				}
			});
		}
		
		//#######################################
		
		mainLayout.addComponent(new Label("Recieved Messages (" + msg_recieved.size() + "):"));
		for (final StudyTradeMessage msg : msg_recieved) {
			Button btnLink = new Button(msg.getDisplayString());
			mainLayout.addComponent(btnLink);
			btnLink.setStyleName("link");
			btnLink.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onMessageClicked(msg);
				}
			});
		}
				
		return mainLayout;
	}

	protected void onEditProfileClicked() {
		for (ProfilePageViewListener l : listeners)
			l.editProfile();
	}

	private void onEditArticleClicked(StudyTradeArticle article) {
		for (ProfilePageViewListener l : listeners)
			l.editArticle(article);
	}

	@Override
	protected void onArticleClicked(StudyTradeArticle a) {
		for (ProfilePageViewListener l : listeners)
			l.articleClicked(a);
	}

	private void onMessageClicked(StudyTradeMessage m) {
		for (ProfilePageViewListener l : listeners)
			l.messageClicked(m);
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

	@Override
	protected void onBtnAddArticleClicked() {
		for (ProfilePageViewListener l : listeners)
			l.onAddArticle();
	}

	@Override
	protected void onFilterCategorieClicked(int cat) {
		for (ProfilePageViewListener l : listeners)
			l.filterArticleByCondClicked(cat);
	}

	@Override
	protected void onFilterPlacesClicked(int plc) {
		for (ProfilePageViewListener l : listeners)
			l.filterArticleByPlaceClicked(plc);
	}
}
