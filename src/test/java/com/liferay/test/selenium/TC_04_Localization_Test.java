package com.liferay.test.selenium;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.liferay.test.selenium.Helpers.Browser;
import com.liferay.test.selenium.Helpers.LiferayInputForm;
import com.liferay.test.selenium.Helpers.LiferaySuccessForm;

public class TC_04_Localization_Test 
{
	private Browser browser;
	private LiferayInputForm inputForm;

	@Before
    public void startBrowser() 
	{
		browser = new Browser();
	    browser.GotoUrl("https://forms.liferay.com/web/forms/shared/-/form/122548");
	    
	    inputForm = new LiferayInputForm(browser.driver);
	    new LiferaySuccessForm(browser.driver);
    }
	
	@Test
	public void TC_04_01_Check_Form_In_Portuguese() 
	{
		//...actions
		inputForm.SwitchToPortuguese();
		
		//...asserts
		assertEquals(inputForm.GetFormTitle(), "Este � um Liferay Forms");
		assertEquals(inputForm.GetFormDescription(), "E aqui temos a descri��o do nosso forms");
		assertEquals(inputForm.GetPageTitle(), "Est� � a primeira p�gina de nosso forms.");
		assertEquals(inputForm.GetPageDescription(), "Vamos festejar o rock.");
		assertEquals(inputForm.GetSubmitText(), "Submeter");
	}
	
	@After
    public void tearDown() 
	{
		browser.Quit();
    }
}
