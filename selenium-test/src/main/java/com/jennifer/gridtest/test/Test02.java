package com.jennifer.gridtest.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Test02{
	private final String hubUrl1 = "http://10.32.36.50:4447/wd/hub";
	
	@Test
	public void testBaidu_1() throws InterruptedException, MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.FIREFOX, "", Platform.VISTA);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("http://www.baidu.com");
	    Assert.assertTrue(webDriver.findElement(By.xpath("//input[@value='�ٶ�һ��']")).isDisplayed());
	    Assert.assertEquals(webDriver.getTitle(), "�ٶ�һ�£����֪��");
	    webDriver.findElement(By.id("kw")).sendKeys("selenium Grid");
	    webDriver.findElement(By.id("su")).click();
	    Thread.sleep(1000L);
	    Assert.assertEquals(webDriver.getTitle().toLowerCase(), "selenium grid_�ٶ�����","The title is "+webDriver.getTitle());
		Thread.sleep(5000L);
		System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
	
	}
	
	
	@Test
	public void testBaidu_2() throws InterruptedException, MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.CHROME, "", Platform.VISTA);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("http://www.baidu.com");
		Assert.assertTrue(webDriver.findElement(By.xpath("//input[@value='�ٶ�һ��']")).isDisplayed());
		Assert.assertEquals(webDriver.getTitle(), "�ٶ�һ�£����֪��");
		webDriver.findElement(By.id("kw")).sendKeys("selenium Grid2");
		webDriver.findElement(By.id("su")).click();
		Thread.sleep(1000L);
		Assert.assertEquals(webDriver.getTitle().toLowerCase(), "selenium grid2_�ٶ�����","The title is "+webDriver.getTitle());
		Thread.sleep(5000L);
		System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
		
	}
}
	
