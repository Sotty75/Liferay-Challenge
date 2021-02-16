package com.liferay.test.selenium.Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LiferaySuccessForm
{
	public WebDriver driver;
	
	private By successPage = By.cssSelector("[class*='ddm-form-success-page'");
	private By titleLocator = By.className("ddm-form-name");
	private By descriptionLocator = By.className("ddm-form-description");
	
	
	public LiferaySuccessForm(WebDriver webdriver)
	{
		driver = webdriver;
	}
	
	public String GetFormTitle()
	{
		new WebDriverWait(driver, 5).until(
		        ExpectedConditions.visibilityOfElementLocated(successPage));
		
		return driver.findElement(titleLocator).getText();
	}
	
	public String GetFormDescription()
	{
		new WebDriverWait(driver, 5).until(
		        ExpectedConditions.visibilityOfElementLocated(successPage));
		
		return driver.findElement(descriptionLocator).getText();
	}
}
