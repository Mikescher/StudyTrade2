package com.studytrade.studytrade2.view.implementations;

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
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class CustomStudyTradeComponent extends CustomComponent{
	private static final long serialVersionUID = -8192711605278107723L;

	private StudyTradeUser User;
	
	protected VerticalLayout mainLayout;
	
	protected TextField edUsername;
	protected PasswordField edPassword;
	protected Button btnLogin;
	protected TextField edSearch;
	protected NativeSelect cbxSearch;
	protected Button btnSearch;
	
	public CustomStudyTradeComponent(StudyTradeUser usr) {
		this.User = usr;
	}

	protected void Init() {
		setHeight("100%");
		setWidth("100%");
		
		setCompositionRoot(buildFullLayout());
	}

	private Layout buildFullLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		if (User == null) { 
			// Not logged in
			
			mainLayout.addComponent(build_user_login_bar_top());
		} else {
			// Logged in
			
			mainLayout.addComponent(build_user_bar_top());
		}

		mainLayout.addComponent(build_searchbar_top());

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
		
		result_layout.addComponent(buildLayout());
		
		return result_layout;
	}
	
	private Layout build_user_login_bar_top() {
		VerticalLayout result_layout = new VerticalLayout();
		result_layout.setStyleName("commonpage_searchbar_top");
		result_layout.setWidth("100%");
		result_layout.setHeight("40px");
		
		HorizontalLayout inner_right_layout = new HorizontalLayout();
		{
			inner_right_layout.setHeight("40px");

			edUsername = new TextField();
			edUsername.setHeight("17px");
			inner_right_layout.addComponent(edUsername);
			inner_right_layout.setComponentAlignment(edUsername, Alignment.MIDDLE_RIGHT);

			edPassword = new PasswordField();
			edPassword.setHeight("17px");
			inner_right_layout.addComponent(edPassword);
			inner_right_layout.setComponentAlignment(edPassword, Alignment.MIDDLE_RIGHT);

			btnLogin = new Button();
			btnLogin.setCaption("Login");
			inner_right_layout.addComponent(btnLogin);
			inner_right_layout.setComponentAlignment(btnLogin, Alignment.MIDDLE_RIGHT);
			btnLogin.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnLoginClicked();
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
		result_layout.setStyleName("commonpage_searchbar_top");
		result_layout.setWidth("100%");
		
		Resource res = new ThemeResource("img/logo.png");
		Image image = new Image(null, res);
		result_layout.addComponent(image);
		result_layout.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
		
		HorizontalLayout inner_right_layout = new HorizontalLayout();
		{
			VerticalLayout inner_right_layout_left = new VerticalLayout();
			{
				Label edLoggedInName = new Label(User.Nickname);
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
		result_layout.setStyleName("commonpage_user_bar_top");
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
			inner_right_layout.addComponent(edSearch);
			inner_right_layout.setComponentAlignment(edSearch, Alignment.MIDDLE_RIGHT);

			cbxSearch = new NativeSelect();
			inner_right_layout.addComponent(cbxSearch);
			inner_right_layout.setComponentAlignment(cbxSearch, Alignment.MIDDLE_RIGHT);

			btnSearch = new Button();
			btnSearch.setCaption("Search");
			inner_right_layout.addComponent(btnSearch);
			inner_right_layout.setComponentAlignment(btnSearch, Alignment.MIDDLE_RIGHT);
			btnSearch.addClickListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					onBtnSearchClicked();
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
		result_layout.setWidth("400px");
		
		Layout pnlCategory = new VerticalLayout();
		pnlCategory.setStyleName("commonpage_categories_left");
		pnlCategory.setHeight("100px");
		result_layout.addComponent(pnlCategory);
		
		Layout pnlCriteria = new VerticalLayout();
		pnlCriteria.setStyleName("commonpage_criteria_left");
		pnlCriteria.setHeight("100px");
		result_layout.addComponent(pnlCriteria);
		
		Layout pnlAds = new VerticalLayout();
		pnlAds.setStyleName("commonpage_ads_left");
		pnlAds.setHeight("100px");
		result_layout.addComponent(pnlAds);
		
		return result_layout;
	}
	
	private Layout build_sidebar_right() {
		VerticalLayout result_layout = new VerticalLayout();

		result_layout.setStyleName("commonpage_layout_right");
		result_layout.setHeight("300px");
		result_layout.setWidth("200px");
		
		return result_layout;
	}
	
	protected abstract Layout buildLayout();
	protected abstract void onBtnLoginClicked();
	protected abstract void onBtnSearchClicked();
	protected abstract void onBtnLogOffClicked();
}
