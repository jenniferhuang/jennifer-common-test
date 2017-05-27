package com.jennifer.gridtest.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class Test01{
	private final String hubUrl1 = "http://10.32.36.50:4447/wd/hub";
	
	@Test
	public void testLogin01() throws InterruptedException, MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.FIREFOX, "", Platform.ANY);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("http://tm2-ci-scs02/");
		Thread.sleep(1000L);
		webDriver.findElement(By.id("login-form-username-field-LoginName")).sendKeys("18664100004");
		webDriver.findElement(By.id("login-form-pin-field-PIN")).sendKeys("101");
		webDriver.findElement(By.id("login-form-password-field-Password")).sendKeys("Test!123");
		webDriver.findElement(By.xpath("//*[contains(@id,'login-form-logIn-content')]")).click();
		Thread.sleep(5000L);
		System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
	}
	
	
	
	@Test
	public void testLogin02() throws MalformedURLException, InterruptedException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.CHROME, "", Platform.ANY);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("http://tm2-ci-scs01/");
		Thread.sleep(1500L);
		webDriver.findElement(By.xpath("//div[@id='login-form-username-field']/input")).sendKeys("18442000006");
		webDriver.findElement(By.id("login-form-pin-field-PIN")).sendKeys("101");
		webDriver.findElement(By.id("login-form-password-field-Password")).sendKeys("Test!123");
		webDriver.findElement(By.xpath("//*[contains(@id,'login-form-logIn-content')]")).click();
		Thread.sleep(5000L);
		System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
		
	}

}
