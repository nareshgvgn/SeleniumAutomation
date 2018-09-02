package com.selenium.test;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class JunitTestBySteps
{

	public static void main(String[] args)
	{
		String exePath = "C:\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setBrowserName("chrome");
	    WebDriver webdriver = new ChromeDriver(capabilities);
	    webdriver.get("https://www.google.co.in/");
	    webdriver.findElement(By.id("lst-ib")).sendKeys("admin");
	}

}
