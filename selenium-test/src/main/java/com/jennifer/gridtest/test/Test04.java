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


public class Test04{
	private final String hubUrl1 = "http://10.32.36.50:4447/wd/hub";
	
	@Test
	public void accessBlog() throws InterruptedException, MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities(BrowserType.CHROME, "", Platform.VISTA);
		RemoteWebDriver webDriver= new RemoteWebDriver(new URL(hubUrl1),cap);
		webDriver.get("http://www.cnblogs.com/");
		Thread.sleep(1000L);
		Assert.assertEquals(webDriver.getTitle(), "����԰ - �����ߵ����ϼ�԰");
	    webDriver.findElement(By.xpath("//a[@href='http://home.cnblogs.com/']")).click();
	    Thread.sleep(1000L);
	    Assert.assertEquals(webDriver.findElement(By.id("feed_recent")).getText(), "���¶�̬");
		Thread.sleep(5000L);
		System.out.println("ThreadName&Id: "+Thread.currentThread().getName() + Thread.currentThread().getId()+
				"  Method: "+Thread.currentThread().getStackTrace()[1].getClassName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+ " done");
		webDriver.quit();
	
	}
}
	
