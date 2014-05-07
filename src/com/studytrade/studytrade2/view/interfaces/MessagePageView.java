package com.studytrade.studytrade2.view.interfaces;

import java.awt.event.ActionListener;

public interface MessagePageView {
	public void addListener(MessagePageViewListener listener);
	
	public String getMessage();
	public ActionListener getAction();
}
