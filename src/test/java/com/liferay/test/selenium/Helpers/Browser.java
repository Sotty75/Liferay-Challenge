package com.liferay.test.selenium.Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Helper class to store the webdriver object
// and access it during the setup / execution / teardown phases of each test
public class Browser 
{
	public WebDriver driver;
	
	public Browser()
	{
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	}
	
	public void GotoUrl(String url)
	{
		driver.get(url);
	}
	
	public void Quit()
	{
		driver.close();
		driver.quit();
	}
}
