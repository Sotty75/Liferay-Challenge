package com.liferay.test.selenium.Helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LiferayInputForm 
{
	public WebDriver driver;
	
	// Input form locators
	private By nameLocator = By.cssSelector("[data-field-name*='WhatIsYourName']");
	private By dateLocator = By.cssSelector("[data-field-name*='WhatIsTheDateOfYourBirth']");
	private By commentLocator = By.cssSelector("[data-field-name*='WhyDidYouJoinTheTestingArea']");
	private By submitLocator = By.cssSelector("[type='submit']");
	
	// Labels locators
	private By titleLocator = By.className("ddm-form-name");
	private By descriptionLocator = By.className("ddm-form-description");
	private By pageTitleLocator = By.className("lfr-ddm-form-page-title");
	private By pageDescriptionLocator = By.className("lfr-ddm-form-page-description");
	
	// Language control locators
	private By languageButtonLocator = By.className("dropdown-toggle");
	private By portugueseLanguageLink = By.cssSelector("[id*='portugues_2d_brasil']");
	private By englishLanguageLink = By.cssSelector("[id*='english_2d_united_2d_states']");
	
	// Calendar control locators
	private By calendarYear = By.cssSelector("[name='year']");
	private By calendarMonth = By.cssSelector("[name='month']");
	
	// Page model constructors, gets the WebDriver instance as input
	public LiferayInputForm(WebDriver webdriver)
	{
		driver = webdriver;
	}
	
	public String GetFormTitle()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement currentElement = wait.until(driver->driver.findElement(titleLocator)); 
		return currentElement.getText();
	}
	
	public String GetFormDescription()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement currentElement = wait.until(driver->driver.findElement(descriptionLocator)); 
		return currentElement.getText();
	}

	public String GetPageTitle()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement currentElement = wait.until(driver->driver.findElement(pageTitleLocator)); 
		return currentElement.getText();
	}
	
	public String GetPageDescription()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement currentElement = wait.until(driver->driver.findElement(pageDescriptionLocator)); 
		return currentElement.getText();
	}
	
	public void SetName(String value)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement parentDiv = wait.until(driver->driver.findElement(nameLocator));
	    WebElement inputElement = parentDiv.findElement(By.tagName("input"));
	    inputElement.sendKeys(value);
	    
	    //..leave element
	    inputElement.sendKeys(Keys.TAB);
		wait.until(new ExpectedCondition<Boolean>() {
 		    public Boolean apply(WebDriver driver) {
 		    	WebElement parentDiv = driver.findElement(nameLocator);
 		    	String currentClass = parentDiv.getAttribute("class");
 		    	if(currentClass.contains("has-error")) 
 		        	return false;
 		        else
 		            return true;
 		    }
 		});
	    
	    return;
	}
	
	public void SetDate(int birthYear, int birthMonth, int birthDay)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		//...open the calendar control
		WebElement parentDiv = wait.until(driver->driver.findElement(dateLocator)); 
		WebElement calendarButton = parentDiv.findElement(By.tagName("button"));
		calendarButton.click();
		
		//...select the target year
		WebElement year = wait.until(driver->driver.findElement(calendarYear));
		year.click();
		Select yearOptions = new Select(year);
		yearOptions.selectByVisibleText(String.valueOf(birthYear));
		year.click();
		
		//...select the target month
		WebElement month = wait.until(driver->driver.findElement(calendarMonth));
		month.click();
		Select monthOptions= new Select(month);
		monthOptions.selectByValue(String.valueOf(birthMonth-1));
		month.click();
		
		//...build the target day string we use to find the target day
		String targetDayString = String.valueOf(birthYear) + " " +
				String.valueOf(birthMonth - 1) + " " +
				String.valueOf(birthDay);
		
		//...find the target day element
		WebElement targetDay = driver.findElement(By.cssSelector("[arialabel='" + targetDayString +"']"));
		targetDay.click();
		
		wait.until(new ExpectedCondition<Boolean>() {
 		    public Boolean apply(WebDriver driver) {
 		    	WebElement parentDiv = driver.findElement(dateLocator);
 		    	String currentClass = parentDiv.getAttribute("class");
 		    	if(currentClass.contains("has-error")) 
 		        	return false;
 		        else
 		            return true;
 		    }
 		});
	}
	
	public void SetComment(String value)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement parentDiv = wait.until(driver->driver.findElement(commentLocator));
		WebElement inputElement = parentDiv.findElement(By.tagName("textarea"));
		inputElement.sendKeys(value);

	    //..leave element
	    inputElement.sendKeys(Keys.TAB);
		wait.until(new ExpectedCondition<Boolean>() {
 		    public Boolean apply(WebDriver driver) {
 		    	WebElement parentDiv = driver.findElement(commentLocator);
 		    	String currentClass = parentDiv.getAttribute("class");
 		    	if(currentClass.contains("has-error")) 
 		        	return false;
 		        else
 		            return true;
 		    }
 		});

	    
		return;
	}
	
	public void SwitchToPortuguese()
	{
		driver.findElement(languageButtonLocator).click();
		driver.findElement(portugueseLanguageLink).click();
		
		new WebDriverWait(driver, 5).until(
		        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='pt-br']")));
		
		return;
	}
	
	public void SwitchToEnglish()
	{
		driver.findElement(languageButtonLocator).click();
		driver.findElement(englishLanguageLink).click();
		
		new WebDriverWait(driver, 5).until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='en-us']")));
		
		return;
	}
	
	public void SubmitForm()
	{
		new WebDriverWait(driver, 5).until(
		        ExpectedConditions.elementToBeClickable(submitLocator));
		
		driver.findElement(submitLocator).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);

		// ...the has-error class is not removed immediately, so we need to wait till
		// it gets removed from the three fields in order to be able to submit the form
		wait.until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver driver) {
		    	WebElement parentDiv = driver.findElement(nameLocator);
		    	String nameClass = parentDiv.getAttribute("class");
		    	parentDiv = driver.findElement(dateLocator);
		    	String dateClass = parentDiv.getAttribute("class");
		    	parentDiv = driver.findElement(commentLocator);
		    	String commentClass = parentDiv.getAttribute("class");
		    	
		        if(nameClass.contains("has-error") || 
		        		dateClass.contains("has-error") || 
		        		commentClass.contains("has-error")) 
		            return false;
		        else
		            return true;
		    }
		});
		
		driver.findElement(submitLocator).click();
		
	}
	
	public String GetSubmitText()
	{
		return driver.findElement(submitLocator).getText();
	}
	
	public Boolean IsNameFieldHiglighted()
	{
		WebDriverWait wait = new WebDriverWait(driver,2);

		Boolean hasError = false;
		
		try 
		{
			hasError = wait.until(new ExpectedCondition<Boolean>() {
			    public Boolean apply(WebDriver driver) {
			    	WebElement parentDiv = driver.findElement(nameLocator);
			    	String classValue = parentDiv.getAttribute("class");
			        if(classValue.contains("has-error")) 
			            return true;
			        else
			            return false;
			    }
			});
		}
		catch (TimeoutException e)
		{
			//...catch the timeout exception
		}
		
		return hasError;
		
	}
	
	public Boolean IsDateFieldHiglighted()
	{
		WebDriverWait wait = new WebDriverWait(driver,2);

		Boolean hasError = false;
		
		try 
		{
			hasError =  wait.until(new ExpectedCondition<Boolean>() {
			    public Boolean apply(WebDriver driver) {
			    	WebElement parentDiv = driver.findElement(dateLocator);
			    	String classValue = parentDiv.getAttribute("class");
			        if(classValue.contains("has-error")) 
			            return true;
			        else
			            return false;
			    }
			});
		}
		catch (TimeoutException e)
		{
			//...catch the timeout exception
		}
		
		return hasError;
	}
	
	public Boolean IsCommentFieldHiglighted()
	{
		WebDriverWait wait = new WebDriverWait(driver,2);

       Boolean hasError = false;
		
		try 
		{
			hasError =  wait.until(new ExpectedCondition<Boolean>() {
			    public Boolean apply(WebDriver driver) {
			    	WebElement parentDiv = driver.findElement(commentLocator);
			    	String classValue = parentDiv.getAttribute("class");
			        if(classValue.contains("has-error")) 
			            return true;
			        else
			            return false;
			    }
			});
		}
		catch (TimeoutException e)
		{
			//...catch the timeout exception
		}
		
		return hasError;
	}
}
