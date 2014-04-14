package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.view.CustomStudyTradeComponent;
import com.studytrade.studytrade2.view.interfaces.MainPageView;
import com.studytrade.studytrade2.view.interfaces.MainPageViewListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class MainPageViewImpl extends CustomStudyTradeComponent implements MainPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private List<MainPageViewListener> listeners = new ArrayList<>();

	public MainPageViewImpl() {
		setCompositionRoot(buildMainLayout());
	}
	
	@Override
	public void addListener(MainPageViewListener listener) {
		listeners.add(listener);
	}

	private AbsoluteLayout buildMainLayout() {
		AbsoluteLayout mainLayout;
		AbsoluteLayout commonpage_content_middle;
		AbsoluteLayout commonpage_layout_right;
		AbsoluteLayout commonpage_ads_left;
		AbsoluteLayout commonpage_criteria_left;
		AbsoluteLayout commonpage_categories_left;
		AbsoluteLayout commonpage_searchbar_top;
		AbsoluteLayout commonpage_user_bar_top;

		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setStyleName("commonpage_main_layout");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// commonpage_user_bar_top
		commonpage_user_bar_top = buildCommonpage_user_bar_top();
		mainLayout.addComponent(commonpage_user_bar_top, "top:0.0px;right:0.0px;left:0.0px;");

		// commonpage_searchbar_top
		commonpage_searchbar_top = buildCommonpage_searchbar_top();
		mainLayout.addComponent(commonpage_searchbar_top, "top:20.0px;right:0.0px;left:0.0px;");

		// commonpage_categories_left
		commonpage_categories_left = new AbsoluteLayout();
		commonpage_categories_left.setStyleName("commonpage_categories_left");
		commonpage_categories_left.setImmediate(false);
		commonpage_categories_left.setWidth("200px");
		commonpage_categories_left.setHeight("300px");
		mainLayout.addComponent(commonpage_categories_left, "top:60.0px;left:0.0px;");

		// commonpage_criteria_left
		commonpage_criteria_left = new AbsoluteLayout();
		commonpage_criteria_left.setStyleName("commonpage_criteria_left");
		commonpage_criteria_left.setImmediate(false);
		commonpage_criteria_left.setWidth("200px");
		commonpage_criteria_left.setHeight("250px");
		mainLayout.addComponent(commonpage_criteria_left, "top:360.0px;left:0.0px;");

		// commonpage_ads_left
		commonpage_ads_left = new AbsoluteLayout();
		commonpage_ads_left.setStyleName("commonpage_ads_left");
		commonpage_ads_left.setImmediate(false);
		commonpage_ads_left.setWidth("200px");
		commonpage_ads_left.setHeight("200px");
		mainLayout.addComponent(commonpage_ads_left, "top:610.0px;left:0.0px;");

		// commonpage_layout_right
		commonpage_layout_right = new AbsoluteLayout();
		commonpage_layout_right.setStyleName("commonpage_layout_right");
		commonpage_layout_right.setImmediate(false);
		commonpage_layout_right.setWidth("150px");
		commonpage_layout_right.setHeight("800px");
		mainLayout.addComponent(commonpage_layout_right, "top:60.0px;right:0.0px;");

		// commonpage_content_middle
		commonpage_content_middle = new AbsoluteLayout();
		commonpage_content_middle.setStyleName("commonpage_content_middle");
		commonpage_content_middle.setImmediate(false);
		commonpage_content_middle.setWidth("100.0%");
		commonpage_content_middle.setHeight("488px");
		mainLayout.addComponent(commonpage_content_middle, "top:80.0px;right:20.0%;left:20.0%;");

		return mainLayout;
	}

	private AbsoluteLayout buildCommonpage_user_bar_top() {
		AbsoluteLayout commonpage_user_bar_top;
		Button commonpage_login_button_top;
		PasswordField commonpage_password_top;
		TextField commonpage_username_top;

		// common part: create layout
		commonpage_user_bar_top = new AbsoluteLayout();
		commonpage_user_bar_top.setStyleName("commonpage_user_bar_top");
		commonpage_user_bar_top.setImmediate(false);
		commonpage_user_bar_top.setWidth("100.0%");
		commonpage_user_bar_top.setHeight("20px");

		// commonpage_username_top
		commonpage_username_top = new TextField();
		commonpage_username_top.setStyleName("commonpage_top_small_fields");
		commonpage_username_top.setImmediate(false);
		commonpage_username_top.setWidth("-1px");
		commonpage_username_top.setHeight("-1px");
		commonpage_user_bar_top.addComponent(commonpage_username_top, "top:1.0px;right:238.0px;");

		// commonpage_password_top
		commonpage_password_top = new PasswordField();
		commonpage_password_top.setStyleName("commonpage_top_small_fields");
		commonpage_password_top.setImmediate(false);
		commonpage_password_top.setWidth("-1px");
		commonpage_password_top.setHeight("-1px");
		commonpage_user_bar_top.addComponent(commonpage_password_top, "top:1.0px;right:100.0px;");

		// commonpage_login_button_top
		commonpage_login_button_top = new Button();
		commonpage_login_button_top.setCaption("Button");
		commonpage_login_button_top.setImmediate(true);
		commonpage_login_button_top.setWidth("-1px");
		commonpage_login_button_top.setHeight("-1px");
		commonpage_user_bar_top.addComponent(commonpage_login_button_top, "top:0.0px;right:10.0px;");

		return commonpage_user_bar_top;
	}

	private AbsoluteLayout buildCommonpage_searchbar_top() {
		AbsoluteLayout commonpage_searchbar_top;
		TextField textField_3;
		NativeSelect nativeSelect_3;
		Button button_3;

		// common part: create layout
		commonpage_searchbar_top = new AbsoluteLayout();
		commonpage_searchbar_top.setStyleName("commonpage_searchbar_top");
		commonpage_searchbar_top.setImmediate(false);
		commonpage_searchbar_top.setWidth("100.0%");
		commonpage_searchbar_top.setHeight("40px");

		// button_3
		button_3 = new Button();
		button_3.setCaption("Button");
		button_3.setImmediate(true);
		button_3.setWidth("-1px");
		button_3.setHeight("-1px");
		commonpage_searchbar_top.addComponent(button_3, "top:8.0px;left:525.0px;");

		// nativeSelect_3
		nativeSelect_3 = new NativeSelect();
		nativeSelect_3.setImmediate(false);
		nativeSelect_3.setWidth("-1px");
		nativeSelect_3.setHeight("-1px");
		commonpage_searchbar_top.addComponent(nativeSelect_3, "top:11.0px;left:439.0px;");

		// textField_3
		textField_3 = new TextField();
		textField_3.setImmediate(false);
		textField_3.setWidth("200px");
		textField_3.setHeight("100.0%");
		commonpage_searchbar_top.addComponent(textField_3, "top:0.0px;left:224.0px;");

		return commonpage_searchbar_top;
	}

}
