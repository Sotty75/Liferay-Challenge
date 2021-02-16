package com.liferay.test.selenium;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.liferay.test.selenium.Helpers.Browser;
import com.liferay.test.selenium.Helpers.LiferayInputForm;

public class TC_02_Submit_On_Missing_Data 
{
	private Browser browser;
	private LiferayInputForm inputForm;
	
	@Before
    public void startBrowser() 
	{
		browser = new Browser();
	    browser.GotoUrl("https://forms.liferay.com/web/forms/shared/-/form/122548");
	    
	    inputForm = new LiferayInputForm(browser.driver);
    }
	
	@Test
	public void TC_02_01_Submit_On_Missing_Data() 
	{
		//...actions
		inputForm.SubmitForm();
		
		//...asserts
		assertEquals(inputForm.IsNameFieldHiglighted(), true);
		assertEquals(inputForm.IsDateFieldHiglighted(), true);
		assertEquals(inputForm.IsCommentFieldHiglighted(), true);
	}
	
	@Test
	public void TC_02_03_Submit_Without_Date() 
	{
		//...actions
		inputForm.SetName("Roberto Rossi");
		inputForm.SetComment("Lorem Ipsum is simply dummy");
		inputForm.SubmitForm();
		
		//...asserts
		assertEquals(inputForm.IsNameFieldHiglighted(), false);
		assertEquals(inputForm.IsDateFieldHiglighted(), true);
		assertEquals(inputForm.IsCommentFieldHiglighted(), false);
	}
	
	
	@After
    public void tearDown() 
	{
		browser.Quit();
    }
}
