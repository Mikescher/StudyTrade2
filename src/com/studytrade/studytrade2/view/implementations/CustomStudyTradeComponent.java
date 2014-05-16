package com.studytrade.studytrade2.view.implementations;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeDefinitions;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class CustomStudyTradeComponent extends CustomComponent {
	private static final long serialVersionUID = -8192711605278107723L;

	protected final StudyTradeUser User;
	
	protected VerticalLayout mainLayout;
	
	private Layout searchbar;
	
	private TextField edUsername;
	private PasswordField edPassword;
	private Button btnLogin;
	private Button btnRegister;
	private TextField edSearch;
	private NativeSelect cbxSearch;
	private Button btnSearch;
	private Button btnExtendedSearch;
	private Button btnAddArticle;
	
	public CustomStudyTradeComponent(StudyTradeUser usr) {
		this.User = usr;
	}

	protected void Init() {
		VerticalLayout overallMainLayout = new VerticalLayout();
		overallMainLayout.setHeight("100%");
		overallMainLayout.setWidth("100%");
		
		Layout fullLayout = buildFullLayout();
		fullLayout.setPrimaryStyleName("allLayouts");
		String h = String.valueOf(UI.getCurrent().getPage().getBrowserWindowHeight());
		fullLayout.setHeight(h+"px");
		fullLayout.setWidth("90%");
		overallMainLayout.addComponent(fullLayout);
		overallMainLayout.setComponentAlignment(fullLayout, Alignment.TOP_CENTER);
		overallMainLayout.setPrimaryStyleName("overallLayout");
		
		setCompositionRoot(overallMainLayout);
	}

	private Layout buildFullLayout() {
		mainLayout = new VerticalLayout();
		
		if (User == null) { 
			// Not logged in
			
			mainLayout.addComponent(build_user_login_bar_top());
		} else {
			// Logged in
			
			mainLayout.addComponent(build_user_bar_top());
		}

		mainLayout.addComponent(searchbar = build_searchbar_top());

		HorizontalLayout area_center = new HorizontalLayout();
		{
			area_center.setWidth("100%");
			area_center.setHeight("100%");
			mainLayout.addComponent(area_center);

			Layout abstractLayout;

			area_center.addComponent(build_sidebar_left());
			area_center.addComponent(abstractLayout = build_content_center());
			area_center.addComponent(build_sidebar_right());
			area_center.setExpandRatio(abstractLayout, 1.0f);
		}
		
		mainLayout.setExpandRatio(area_center, 1.0f);
		
		return mainLayout;
	}
	
	private Layout build_content_center() {
		VerticalLayout result_layout = new VerticalLayout();
		result_layout.setStyleName("commonpage_main_layout");

		result_layout.setWidth("100%");
		result_layout.setHeight("100%");
		
		result_layout.setMargin(true);
		
		result_layout.addComponent(buildLayout());
		
		return result_layout;
	}
	
	private Layout build_user_login_bar_top() {
		HorizontalLayout result_layout = new HorizontalLayout();
		result_layout.setStyleName("commonpage_user_bar_top");
		result_layout.setWidth("100%");
		result_layout.setHeight("40px");
		
		Resource res = new ThemeResource("img/logo.png");
		Image image = new Image(null, res);
		result_layout.addComponent(image);
		result_layout.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
		
		HorizontalLayout inner_right_layout = new HorizontalLayout();
		{
			inner_right_layout.setHeight("40px");
			

			edUsername = new TextField();
			edUsername.setId("selendebug_CmnPg_ed_username");
			edUsername.setStyleName("customTextfield");
			edUsername.setHeight("17px");
			inner_right_layout.addComponent(edUsername);
			inner_right_layout.setComponentAlignment(edUsername, Alignment.MIDDLE_RIGHT);

			edPassword = new PasswordField();
			edPassword.setId("selendebug_CmnPg_ed_passw");
			edPassword.setHeight("17px");
			edPassword.setStyleName("customTextfield");
			inner_right_layout.addComponent(edPassword);
			inner_right_layout.setComponentAlignment(edPassword, Alignment.MIDDLE_RIGHT);

			btnLogin = new NativeButton();
			btnLogin.setId("selendebug_CmnPg_btn_login");
			btnLogin.setCaption("Login");
			btnLogin.setPrimaryStyleName("btn btn-success");
			inner_right_layout.addComponent(btnLogin);
			inner_right_layout.setComponentAlignment(btnLogin, Alignment.MIDDLE_RIGHT);
			btnLogin.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnLoginClicked(edUsername.getValue(), edPassword.getValue());
				}
			});

			btnRegister = new Button();
			btnRegister.setId("selendebug_CmnPg_btn_register");
			btnRegister.setCaption("Register");
			btnRegister.setPrimaryStyleName("btn btn-success");
			inner_right_layout.addComponent(btnRegister);
			inner_right_layout.setComponentAlignment(btnRegister, Alignment.MIDDLE_RIGHT);
			btnRegister.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnRegisterClicked();
				}
			});

			inner_right_layout.setSizeUndefined();
		}
		
		result_layout.addComponent(inner_right_layout);
		result_layout.setComponentAlignment(inner_right_layout, Alignment.MIDDLE_RIGHT);
		
		return result_layout;
	}
	
	private Layout build_user_bar_top() {
		HorizontalLayout result_layout = new HorizontalLayout();
		result_layout.setStyleName("commonpage_user_bar_top");
		result_layout.setWidth("100%");
		result_layout.setHeight("40px");
		
		Resource res = new ThemeResource("img/logo.png");
		Image image = new Image(null, res);
		result_layout.addComponent(image);
		result_layout.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
		
		HorizontalLayout inner_right_layout = new HorizontalLayout();
		{
			inner_right_layout.setStyleName("commonpage_user_bar_top_inner_right");
			
			btnAddArticle = new Button("Add");
			btnAddArticle.setId("selendebug_CmnPg_btn_addarticle");
			btnAddArticle.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 3673502648449522631L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnAddArticleClicked();
				}
			});
			
			VerticalLayout inner_right_layout_left = new VerticalLayout();
			{
				int msgCount = User.getUnreadMessages().size();
				Button edLoggedInName = new Button(User.Nickname + ((msgCount > 0) ? ("    (" + msgCount + " new message" + (msgCount == 1 ? "" : "s") + ")") : ("")));
				edLoggedInName.setStyleName("link");
				edLoggedInName.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 3673502648449522631L;

					@Override
					public void buttonClick(ClickEvent event) {
						onButtonProfileClicked();
					}
				});
				inner_right_layout_left.addComponent(edLoggedInName);
				inner_right_layout_left.setComponentAlignment(edLoggedInName, Alignment.MIDDLE_RIGHT);

//				Label edLoggedInMail = new Label(User.Email);
//				inner_right_layout_left.addComponent(edLoggedInMail);
//				inner_right_layout_left.setComponentAlignment(edLoggedInMail, Alignment.MIDDLE_RIGHT);
				
				Button btnLogOff = new Button("Log Off");
				inner_right_layout_left.addComponent(btnLogOff);
				btnLogOff.setStyleName("link");
				inner_right_layout_left.setComponentAlignment(btnLogOff, Alignment.MIDDLE_RIGHT);
				btnLogOff.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						onBtnLogOffClicked();
					}
				});

				inner_right_layout_left.setSizeUndefined();
			}
		
			VerticalLayout inner_right_layout_right = new VerticalLayout();
			{
				inner_right_layout_right.addComponent(new Image(null, new ThemeResource("img/default_avatar.png")));

				inner_right_layout_right.setSizeUndefined();
			}
			
			inner_right_layout.addComponent(btnAddArticle);
			inner_right_layout.setComponentAlignment(btnAddArticle, Alignment.MIDDLE_RIGHT);
			
			inner_right_layout.addComponent(inner_right_layout_left);
			inner_right_layout.setComponentAlignment(inner_right_layout_left, Alignment.MIDDLE_RIGHT);
			
			inner_right_layout.addComponent(inner_right_layout_right);
			inner_right_layout.setComponentAlignment(inner_right_layout_right, Alignment.MIDDLE_RIGHT);
			
			inner_right_layout.setSizeUndefined();
		}

		result_layout.addComponent(inner_right_layout);
		result_layout.setComponentAlignment(inner_right_layout, Alignment.MIDDLE_RIGHT);
		
		return result_layout;
	}

	private Layout build_searchbar_top() {
		VerticalLayout result_layout = new VerticalLayout();
		result_layout.setStyleName("commonpage_searchbar_top");
		result_layout.setWidth("100%");
		result_layout.setHeight("40px");
		
		HorizontalLayout inner_right_layout = new HorizontalLayout();
		{
			inner_right_layout.setHeight("40px");

			inner_right_layout.setWidth("100.0%");
			inner_right_layout.setHeight("40px");
			inner_right_layout.addComponent(new Label("&nbsp;", ContentMode.HTML));

			edSearch = new TextField();
			edSearch.setWidth("200px");
			edSearch.setId("selendebug_CmnPg_ed_search");
			inner_right_layout.addComponent(edSearch);
			inner_right_layout.setComponentAlignment(edSearch, Alignment.MIDDLE_RIGHT);

			cbxSearch = new NativeSelect();
			inner_right_layout.addComponent(cbxSearch);
			inner_right_layout.setComponentAlignment(cbxSearch, Alignment.MIDDLE_RIGHT);

			btnSearch = new Button();
			btnSearch.setCaption("Search");
			btnSearch.setId("selendebug_CmnPg_btn_search");
			btnSearch.setPrimaryStyleName("btn btn-primary");
			inner_right_layout.addComponent(btnSearch);
			inner_right_layout.setComponentAlignment(btnSearch, Alignment.MIDDLE_RIGHT);
			btnSearch.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnSearchClicked(edSearch.getValue());
				}
			});
			
			btnExtendedSearch = new Button();
			btnExtendedSearch.setCaption("Advanced");
			btnExtendedSearch.setId("selendebug_CmnPg_btn_advsearch");
			inner_right_layout.addComponent(btnExtendedSearch);
			btnExtendedSearch.setPrimaryStyleName("btn btn-primary");
			inner_right_layout.setComponentAlignment(btnExtendedSearch, Alignment.MIDDLE_RIGHT);
			btnExtendedSearch.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnAdvancedSearchClicked();
				}
			});

			inner_right_layout.setSizeUndefined();
		}
		
		result_layout.addComponent(inner_right_layout);
		result_layout.setComponentAlignment(inner_right_layout, Alignment.MIDDLE_RIGHT);
		
		return result_layout;
	}

	private Layout build_sidebar_left() {
		VerticalLayout result_layout;
		
		result_layout = new VerticalLayout();
		result_layout.setWidth("200px");
		
		Layout pnlCategory = new VerticalLayout();
		pnlCategory.setStyleName("commonpage_categories_left");

		pnlCategory.addComponent(new Label("Categories:"));
		for (int i = 0; i < StudyTradeDefinitions.CONDITIONS.length; i++) {
			final int cat = i;
			Button btnCond = new Button("[" + StudyTradeDefinitions.CONDITIONS[cat] + "]");
			btnCond.setStyleName("link");
			btnCond.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onFilterCategorieClicked(cat);
				}
			});
			pnlCategory.addComponent(btnCond);
		}

		pnlCategory.setHeight("200px");
		result_layout.addComponent(pnlCategory);

		Layout pnlCriteria = new VerticalLayout();
		pnlCriteria.setStyleName("commonpage_criteria_left");
		pnlCriteria.setHeight("200px");
		result_layout.addComponent(pnlCriteria);

		pnlCriteria.addComponent(new Label("Places:"));
		for (int i = 0; i < StudyTradeDefinitions.PLACES.length; i++) {
			final int plc = i;
			Button btnPlc = new Button(StudyTradeDefinitions.PLACES[plc]);
			btnPlc.setStyleName("link");
			btnPlc.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onFilterPlacesClicked(plc);
				}
			});
			pnlCriteria.addComponent(btnPlc);
		}

		Layout pnlAds = new VerticalLayout();
		pnlAds.setStyleName("commonpage_ads_left");
		pnlAds.setHeight("200px");
		result_layout.addComponent(pnlAds);
		
		return result_layout;
	}

	private Layout build_sidebar_right() {
		VerticalLayout result_layout = new VerticalLayout();

		result_layout.setStyleName("commonpage_layout_right");
		result_layout.setHeight("600px");
		result_layout.setWidth("200px");
		
		if (User != null) {
			result_layout.addComponent(new Label("Your Articles:"));
			for (final StudyTradeArticle artc : User.getArticles()) {
				Button btnPlc = new Button(artc.Name);
				btnPlc.setStyleName("link");
				btnPlc.addClickListener(new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						onArticleClicked(artc);
					}
				});
				result_layout.addComponent(btnPlc);
			}
		}
		
		return result_layout;
	}

	protected void hideSearchbar() {
		searchbar.setVisible(false);
	}
	
	protected void setSearchString(String searchstr) {
		edSearch.setValue(searchstr);
	}
	
	protected void forceLoggedIn() {
		if (User == null) {
			onShowMessage("You must be logged in to display this page");
		}
	}
	
	protected abstract void onButtonProfileClicked();
	protected abstract Layout buildLayout();
	protected abstract void onBtnLoginClicked(String username, String password);
	protected abstract void onBtnRegisterClicked();
	protected abstract void onBtnSearchClicked(String searchstring);
	protected abstract void onBtnAdvancedSearchClicked();
	protected abstract void onBtnLogOffClicked();
	protected abstract void onBtnAddArticleClicked();
	protected abstract void onShowMessage(String msg);
	protected abstract void onArticleClicked(StudyTradeArticle artc);
	protected abstract void onFilterCategorieClicked(int cat);
	protected abstract void onFilterPlacesClicked(int plc);
}
