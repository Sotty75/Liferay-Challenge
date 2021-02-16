package com.liferay.test.selenium;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.liferay.test.selenium.Helpers.Browser;
import com.liferay.test.selenium.Helpers.LiferayInputForm;
import com.liferay.test.selenium.Helpers.LiferaySuccessForm;

public class TC_03_Mandatory_Fields_Editing 
{
	private Browser browser;
	private LiferayInputForm inputForm;
	private LiferaySuccessForm successForm;
	
	@Before
    public void startBrowser() 
	{
		browser = new Browser();
	    browser.GotoUrl("https://forms.liferay.com/web/forms/shared/-/form/122548");
	    
	    inputForm = new LiferayInputForm(browser.driver);
	    successForm = new LiferaySuccessForm(browser.driver);
    }
	
	@Test
	public void TC_03_01_Editing_After_Validation() 
	{
		//..actions
		inputForm.SubmitForm();
		
		//...check mandatory fields are highlighted
		assertEquals(inputForm.IsNameFieldHiglighted(), true);
		assertEquals(inputForm.IsDateFieldHiglighted(), true);
		assertEquals(inputForm.IsCommentFieldHiglighted(), true);
		
		//...type valid input data
		inputForm.SetName("Roberto Rossi");
		inputForm.SetDate(1975, 03, 22);
		inputForm.SetComment("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's     standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		inputForm.SubmitForm();
		
		//...asserts
		assertEquals(successForm.GetFormTitle(), "Information sent");
		assertEquals(successForm.GetFormDescription(), "Information sent successfully!");
	}
	
	@After
    public void tearDown() 
	{
		browser.Quit();
    }
}
