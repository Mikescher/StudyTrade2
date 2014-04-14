package com.studytrade.studytrade2.view.implementations;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class CustomStudyTradeComponent extends CustomComponent{
	private static final long serialVersionUID = -8192711605278107723L;

	protected VerticalLayout mainLayout;
	
	protected TextField edUsername;
	protected PasswordField edPassword;
	protected Button btnLogin;
	protected TextField edSearch;
	protected NativeSelect cbxSearch;
	protected Button btnSearch;
	
	public CustomStudyTradeComponent() {
		// NOP
	}
	
	protected void Init() {
		setCompositionRoot(buildFullLayout());
		
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				onBtnSearchClicked();
			}
		});
		
		btnLogin.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				onBtnLoginClicked();
			}
		});
	}
	
	private Layout buildFullLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("commonpage_main_layout");
		mainLayout.setWidth("100%");


		mainLayout.addComponent(build_user_bar_top());

		mainLayout.addComponent(build_searchbar_top());

		
		HorizontalLayout area_center = new HorizontalLayout();
		area_center.setWidth(100, Unit.PERCENTAGE);
		mainLayout.addComponent(area_center);
		
		
		area_center.addComponent(build_sidebar_left());
		area_center.addComponent(buildLayout());
		area_center.addComponent(build_sidebar_right());

		return mainLayout;
	}

	private Layout build_user_bar_top() {
		VerticalLayout result_layout = new VerticalLayout();
		result_layout.setStyleName("commonpage_searchbar_top");
		result_layout.setWidth("100%");
		result_layout.setHeight("40px");
		
		HorizontalLayout inner_right_layout = new HorizontalLayout();
		
		inner_right_layout = new HorizontalLayout();
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
		btnLogin.setCaption("Button");
		inner_right_layout.addComponent(btnLogin);
		inner_right_layout.setComponentAlignment(btnLogin, Alignment.MIDDLE_RIGHT);

		inner_right_layout.setSizeUndefined();
		
		
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
		
		inner_right_layout = new HorizontalLayout();
		inner_right_layout.setHeight("40px");

		inner_right_layout.setWidth("100.0%");
		inner_right_layout.setHeight("40px");
		inner_right_layout.addComponent(new Label("&nbsp;", ContentMode.HTML));
		
		edSearch = new TextField();
		edSearch.setWidth("200px");
		edPassword.setHeight(17, Unit.PIXELS);
		inner_right_layout.addComponent(edSearch);
		inner_right_layout.setComponentAlignment(edSearch, Alignment.MIDDLE_RIGHT);

		cbxSearch = new NativeSelect();
		inner_right_layout.addComponent(cbxSearch);
		inner_right_layout.setComponentAlignment(cbxSearch, Alignment.MIDDLE_RIGHT);

		btnSearch = new Button();
		btnSearch.setCaption("Search");
		inner_right_layout.addComponent(btnSearch);
		inner_right_layout.setComponentAlignment(btnSearch, Alignment.MIDDLE_RIGHT);

		inner_right_layout.setSizeUndefined();
		
		
		result_layout.addComponent(inner_right_layout);
		result_layout.setComponentAlignment(inner_right_layout, Alignment.MIDDLE_RIGHT);
		
		return result_layout;
	}

	private Layout build_sidebar_left() {
		VerticalLayout result_layout;
		
		result_layout = new VerticalLayout();
		
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
		VerticalLayout result_layout;
		
		result_layout = new VerticalLayout();
		result_layout.setStyleName("commonpage_layout_right");
		result_layout.setHeight("300px");
		
		return result_layout;
	}
	
	protected abstract Layout buildLayout();
	protected abstract void onBtnLoginClicked();
	protected abstract void onBtnSearchClicked();
}
