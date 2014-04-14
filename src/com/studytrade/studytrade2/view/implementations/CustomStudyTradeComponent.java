package com.studytrade.studytrade2.view.implementations;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class CustomStudyTradeComponent extends CustomComponent {
	private static final long serialVersionUID = -8192711605278107723L;

	protected VerticalLayout mainLayout;
	
	protected TextField username_top;
	protected PasswordField password_top;
	protected Button btnLogin;
	protected TextField edSearch;
	protected NativeSelect cbxSearch;
	protected Button btnSearch;
	
	public CustomStudyTradeComponent() {
		setCompositionRoot(buildFullLayout());
	}
	
	private Layout buildFullLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("commonpage_main_layout");
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");


		mainLayout.addComponent(build_user_bar_top());

		mainLayout.addComponent(build_searchbar_top());

		
		HorizontalLayout area_center = new HorizontalLayout();
		mainLayout.addComponent(area_center);
		
		area_center.addComponent(build_sidebar_left());
		area_center.addComponent(buildLayout());
		area_center.addComponent(build_sidebar_right());

		return mainLayout;
	}

	private Layout build_user_bar_top() {
		HorizontalLayout user_bar_top;
		
		user_bar_top = new HorizontalLayout();
		user_bar_top.setStyleName("commonpage_user_bar_top");
		user_bar_top.setWidth("100.0%");
		user_bar_top.setHeight("40px");

		username_top = new TextField();
		username_top.setStyleName("commonpage_top_small_fields");
		username_top.setSizeUndefined();
		user_bar_top.addComponent(username_top);
		user_bar_top.setComponentAlignment(username_top, Alignment.TOP_RIGHT);

		password_top = new PasswordField();
		password_top.setStyleName("commonpage_top_small_fields");
		password_top.setSizeUndefined();
		user_bar_top.addComponent(password_top);
		user_bar_top.setComponentAlignment(password_top, Alignment.TOP_RIGHT);

		btnLogin = new Button();
		btnLogin.setCaption("Button");
		btnLogin.setSizeUndefined();
		user_bar_top.addComponent(btnLogin);
		user_bar_top.setComponentAlignment(btnLogin, Alignment.TOP_RIGHT);

		return user_bar_top;
	}

	private Layout build_searchbar_top() {
		HorizontalLayout searchbar_top;

		searchbar_top = new HorizontalLayout();
		searchbar_top.setStyleName("commonpage_searchbar_top");
		searchbar_top.setWidth("100.0%");
		searchbar_top.setHeight("40px");

		btnSearch = new Button();
		btnSearch.setCaption("Search");
		searchbar_top.addComponent(btnSearch);
		searchbar_top.setComponentAlignment(btnSearch, Alignment.TOP_RIGHT);

		cbxSearch = new NativeSelect();
		searchbar_top.addComponent(cbxSearch);
		searchbar_top.setComponentAlignment(cbxSearch, Alignment.TOP_RIGHT);

		edSearch = new TextField();
		edSearch.setWidth("200px");
		edSearch.setHeight("100.0%");
		searchbar_top.addComponent(edSearch);
		searchbar_top.setComponentAlignment(edSearch, Alignment.TOP_RIGHT);

		return searchbar_top;
	}

	private Layout build_sidebar_left() {
		VerticalLayout sidebar_left;
		
		sidebar_left = new VerticalLayout();
		
		Panel pnlCategory = new Panel();
		pnlCategory.setStyleName("commonpage_categories_left");
		sidebar_left.addComponent(pnlCategory);
		
		Panel pnlCriteria = new Panel();
		pnlCategory.setStyleName("commonpage_criteria_left");
		sidebar_left.addComponent(pnlCriteria);
		
		Panel pnlAds = new Panel();
		pnlCategory.setStyleName("commonpage_ads_left");
		sidebar_left.addComponent(pnlAds);
		
		return sidebar_left;
	}
	
	private Layout build_sidebar_right() {
		VerticalLayout sidebar_right;
		
		sidebar_right = new VerticalLayout();
		sidebar_right.setStyleName("commonpage_layout_right");
		
		return sidebar_right;
	}
	
	protected abstract Layout buildLayout();
}
