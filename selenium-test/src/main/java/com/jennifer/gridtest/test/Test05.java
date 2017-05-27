package com.jennifer.gridtest.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class Test05{
	private final String hubUrl1 = "http://10.32.36.50:4447/wd/hub";
	
	@Test
	public void testFive_1() throws InterruptedException, MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.FIREFOX, "", Platform.VISTA);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("https://facebook.com");
        WebElement textBox = webDriver.findElement(By.xpath("//input[@name='firstname']"));
        Thread.sleep(1000L);
        textBox.click();
        textBox.sendKeys("Test Five-1!");
        Thread.sleep(5000L);
        System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
	
	}
	
	@Test
	public void testFive_2() throws InterruptedException, MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.FIREFOX, "", Platform.VISTA);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("https://facebook.com");
		WebElement textBox = webDriver.findElement(By.xpath("//input[@name='firstname']"));
		Thread.sleep(1000L);
		textBox.click();
		textBox.sendKeys("Test Five-2!");
		Thread.sleep(5000L);
		System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
	}
}
	
