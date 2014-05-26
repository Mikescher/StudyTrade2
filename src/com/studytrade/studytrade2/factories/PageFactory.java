 package com.studytrade.studytrade2.factories;

import java.awt.event.ActionListener;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeMessage;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.presenter.AddArticlePagePresenter;
import com.studytrade.studytrade2.presenter.AdvancedSearchPagePresenter;
import com.studytrade.studytrade2.presenter.ArticlePagePresenter;
import com.studytrade.studytrade2.presenter.CustomPresenter;
import com.studytrade.studytrade2.presenter.EditArticlePagePresenter;
import com.studytrade.studytrade2.presenter.MainPagePresenter;
import com.studytrade.studytrade2.presenter.MessagePagePresenter;
import com.studytrade.studytrade2.presenter.ProfilePagePresenter;
import com.studytrade.studytrade2.presenter.RegisterPagePresenter;
import com.studytrade.studytrade2.presenter.SearchResultPagePresenter;
import com.studytrade.studytrade2.presenter.UserMessagePagePresenter;
import com.studytrade.studytrade2.presenter.UserPagePresenter;
import com.studytrade.studytrade2.presenter.EditProfilePagePresenter;
import com.studytrade.studytrade2.view.implementations.AddArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.AdvancedSearchPageViewImpl;
import com.studytrade.studytrade2.view.implementations.ArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.EditArticlePageViewImpl;
import com.studytrade.studytrade2.view.implementations.MainPageViewImpl;
import com.studytrade.studytrade2.view.implementations.MessagePageViewImpl;
import com.studytrade.studytrade2.view.implementations.ProfilePageViewImpl;
import com.studytrade.studytrade2.view.implementations.RegisterPageViewImpl;
import com.studytrade.studytrade2.view.implementations.SearchResultPageViewImpl;
import com.studytrade.studytrade2.view.implementations.UserMessagePageViewImpl;
import com.studytrade.studytrade2.view.implementations.UserPageViewImpl;
import com.studytrade.studytrade2.view.implementations.EditProfilePageViewImpl;

public class PageFactory {

	private PageFactory() { /**/ }

	public static AddArticlePagePresenter createAddArticlePage(CustomPresenter sender) {
		return new AddArticlePagePresenter(sender.UI, sender.Model, new AddArticlePageViewImpl(sender.Model.getLoggedInUser()));
	}

	public static AdvancedSearchPagePresenter createAdvancedSearchPage(CustomPresenter sender) {
		return new AdvancedSearchPagePresenter(sender.UI, sender.Model, new AdvancedSearchPageViewImpl(sender.Model.getLoggedInUser()));
	}

	public static ArticlePagePresenter createArticlePage(CustomPresenter sender, StudyTradeArticle article) {
		return new ArticlePagePresenter(sender.UI, sender.Model, new ArticlePageViewImpl(sender.Model.getLoggedInUser(), article));
	}

	public static EditArticlePagePresenter createEditArticlePage(CustomPresenter sender, StudyTradeArticle article) {
		return new EditArticlePagePresenter(sender.UI, sender.Model, new EditArticlePageViewImpl(sender.Model.getLoggedInUser(), article));
	}

	public static MainPagePresenter createMainPage(CustomPresenter sender) {
		return new MainPagePresenter(sender.UI, sender.Model, new MainPageViewImpl(sender.Model.getLoggedInUser()));
	}

	public static MessagePagePresenter createMessagePage(CustomPresenter sender, String text, ActionListener action) {
		return new MessagePagePresenter(sender.UI, sender.Model, new MessagePageViewImpl(sender.Model.getLoggedInUser(), text, action));
	}

	public static ProfilePagePresenter createProfilePage(CustomPresenter sender) {
		return new ProfilePagePresenter(sender.UI, sender.Model, new ProfilePageViewImpl(sender.Model.getLoggedInUser()));
	}

	public static RegisterPagePresenter createRegisterPage(CustomPresenter sender, List<String> usrnames) {
		return new RegisterPagePresenter(sender.UI, sender.Model, new RegisterPageViewImpl(sender.Model.getLoggedInUser(), usrnames));
	}

	public static SearchResultPagePresenter createSearchResultPage(CustomPresenter sender, String text, List<StudyTradeArticle> results) {
		return new SearchResultPagePresenter(sender.UI, sender.Model, new SearchResultPageViewImpl(sender.Model.getLoggedInUser(), text, results));
	}

	public static UserMessagePagePresenter createUserMessagePage(CustomPresenter sender, StudyTradeMessage text, ActionListener action) {
		return new UserMessagePagePresenter(sender.UI, sender.Model, new UserMessagePageViewImpl(sender.Model.getLoggedInUser(), text, action));
	}

	public static UserPagePresenter createUserPage(CustomPresenter sender, StudyTradeUser usr) {
		return new UserPagePresenter(sender.UI, sender.Model, new UserPageViewImpl(sender.Model.getLoggedInUser(), usr));
	}

	public static EditProfilePagePresenter createEditProfilePage(CustomPresenter sender) {
		return new EditProfilePagePresenter(sender.UI, sender.Model, new EditProfilePageViewImpl(sender.Model.getLoggedInUser()));
	}
}
