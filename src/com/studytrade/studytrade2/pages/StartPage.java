package com.studytrade.studytrade2.pages;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class StartPage extends Panel {
	
	private TextField searchField = new TextField();
	private Table newsList = new Table();
	private Table newThingsList = new Table();
	private Button btnSearch = new Button();
	
	public StartPage()
	{	
		init();
	}
	
	protected void init()
	{
        initLayout();
        initSearch();
	}
	 
	private void initLayout()
	{
		VerticalLayout vertlayout1 = new VerticalLayout();
		setContent(vertlayout1);
		 
		// Anwendungsverzeichnis wird hier gesucht
		String basepath = VaadinService.getCurrent()
		                   .getBaseDirectory().getAbsolutePath();
		// joa da wird wohl die resource geladen
		FileResource resource = new FileResource(new File(basepath +
		                         "/WEB-INF/Images/StudyTrade.png")); 
		
		//FileResource resource = new FileResource(new File("C:/Users/Armin/workspace/StudyTrade2/WebContent/WEB-INF/Images/StudyTrade.png"));
		// hier wird das Image initalisiert
		Image imageLogo = new Image("Logo", resource);
		imageLogo.setSizeFull();

		vertlayout1.addComponent(imageLogo);
		 
		 
		/* nach der Header kommt hier ein geteilter Bildschirm
		  	auf der einen Seite die zu letzt eingestellte und auf der rechten Seite
		  	Suchfeld und ggb News oder anderes...	*/
		 
		HorizontalSplitPanel horSplitlayout1 = new HorizontalSplitPanel();
		vertlayout1.addComponent(horSplitlayout1);
		
		 
		VerticalLayout leftlayout1 = new VerticalLayout();
		// hier kommt erstmal das zeugs für die Linkeseite hin.....

		
		horSplitlayout1.addComponent(leftlayout1);
		
		leftlayout1.addComponent(newThingsList);
		 
		VerticalLayout rightlayout1 = new VerticalLayout();
		horSplitlayout1.addComponent(rightlayout1);
		

		rightlayout1.addComponent(searchField);
		rightlayout1.addComponent(newsList);
	 }
	 
	 private void initSearch()
	 {
		searchField.setInputPrompt("Suche...");
		searchField.setSizeFull();

	 }

}
