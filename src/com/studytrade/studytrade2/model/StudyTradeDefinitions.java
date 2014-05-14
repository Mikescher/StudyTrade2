package com.studytrade.studytrade2.model;

import com.vaadin.ui.NativeSelect;

public class StudyTradeDefinitions {

	public static String[] PLACES = 
	{
		"DH Karlsruhe",
		"DH Freiburg",
		"Uni Offenburg",
		"Other",
	};
	
	public static String[] CONDITIONS = 
	{
		"OVP",
		"New",
		"Like New",
		"Used",
		"Slightly damaged",
		"Heavily damaged",
		"Defect",
		"Totally broken",
	};
	
	public static void addSelectItems_Places(NativeSelect sel) {
		for (String p : PLACES) {
			sel.setItemCaption(sel.addItem(p), p);
		}
	}
	
	public static void addSelectItems_Condition(NativeSelect sel) {
		for (String p : CONDITIONS) {
			sel.setItemCaption(sel.addItem(p), p);
		}
	}
	
	public static int getConditionIndex(String cond) {
		for (int i = 0; i < CONDITIONS.length; i++) {
			if (CONDITIONS[i].equalsIgnoreCase(cond)) {
				return i;
			}
		}
		return 0;
	}
}
