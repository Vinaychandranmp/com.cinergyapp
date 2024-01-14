package com.cinergyapp.page;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TicketBookingPages 
{
	public WebDriver driver;
	Properties properties=new Properties();
	@FindBy(xpath="//select[@id='location_select']//option[contains(text(),'Charlotte, NC')]")
	WebElement location_charlotte;
	
	public void initialize(String browser, String url) 
	{
		if (browser.equals("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(url);
	
	}
	public void select_location()
	{
		location_charlotte.click();
	}
}
