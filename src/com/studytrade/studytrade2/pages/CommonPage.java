package com.studytrade.studytrade2.pages;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;

public class CommonPage extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,8","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":6} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout commonpage_layout_right;
	@AutoGenerated
	private AbsoluteLayout commonpage_ads_left;
	@AutoGenerated
	private AbsoluteLayout commonpage_criteria_left;
	@AutoGenerated
	private AbsoluteLayout commonpage_categories_left;
	@AutoGenerated
	private AbsoluteLayout commonpage_searchbar_top;
	@AutoGenerated
	private AbsoluteLayout commonpage_user_bar_top;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public CommonPage() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
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
		commonpage_user_bar_top = new AbsoluteLayout();
		commonpage_user_bar_top.setStyleName("commonpage_user_bar_top");
		commonpage_user_bar_top.setImmediate(false);
		commonpage_user_bar_top.setWidth("100.0%");
		commonpage_user_bar_top.setHeight("20px");
		mainLayout.addComponent(commonpage_user_bar_top,
				"top:0.0px;right:0.0px;left:0.0px;");
		
		// commonpage_searchbar_top
		commonpage_searchbar_top = new AbsoluteLayout();
		commonpage_searchbar_top.setStyleName("commonpage_searchbar_top");
		commonpage_searchbar_top.setImmediate(false);
		commonpage_searchbar_top.setWidth("100.0%");
		commonpage_searchbar_top.setHeight("40px");
		mainLayout.addComponent(commonpage_searchbar_top,
				"top:20.0px;right:0.0px;left:0.0px;");
		
		// commonpage_categories_left
		commonpage_categories_left = new AbsoluteLayout();
		commonpage_categories_left.setStyleName("commonpage_categories_left");
		commonpage_categories_left.setImmediate(false);
		commonpage_categories_left.setWidth("200px");
		commonpage_categories_left.setHeight("300px");
		mainLayout.addComponent(commonpage_categories_left,
				"top:60.0px;left:0.0px;");
		
		// commonpage_criteria_left
		commonpage_criteria_left = new AbsoluteLayout();
		commonpage_criteria_left.setStyleName("commonpage_criteria_left");
		commonpage_criteria_left.setImmediate(false);
		commonpage_criteria_left.setWidth("200px");
		commonpage_criteria_left.setHeight("250px");
		mainLayout.addComponent(commonpage_criteria_left,
				"top:360.0px;left:0.0px;");
		
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
		mainLayout.addComponent(commonpage_layout_right,
				"top:60.0px;right:0.0px;");
		
		return mainLayout;
	}

}
