package com.studytrade.studytrade2.view.interfaces;

public interface RegisterPageViewListener {
	public void loginClicked(String username, String password);
	public void registerClicked();
	public void searchClicked(String searchstring);
	public void logOffClicked();

	public void doRegisterClicked(String forename, String lastname, String nickname, String place, String university, String studydirection, String email, String password);
}
