package com.jennifer.gridtest.grid;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GridTest1 {
	/**
	 * @throws MalformedURLException
	 * Example:
	 * Start a hub: java -jar selenium-server-standalone-2.41.0.jar -role hub
	 * Start a node(chrome):  java -jar selenium-server-standalone-2.41.0.jar -role node -browser browserName=chrome
	 * Start a node:  java -jar selenium-server-standalone-2.44.0.jar -role node -hub http://10.32.36.50:4444/grid/register
	 * 
	 * Specify IE/chrome driver path when start node: 
	 * java -jar selenium-server-standalone-2.41.0.jar -role node -Dwebdriver.chrome.driver=chromedriver.exe -Dwebdriver.ie.driver=IEDriverServer.exe
	 * 
	 * Hub Console:
	 * http://localhost:4441/grid/console
	 * 	 * 
	 */
	
	@Test
	public void myTest() throws MalformedURLException{
		
		String hubUrl1 = "http://10.32.36.50:4441/wd/hub";
		
		
		
		//node:java -jar selenium-server-standalone-2.41.0.jar -role node 
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		
        WebDriver driver = new RemoteWebDriver(new URL(hubUrl1), capability);
        driver.get("http://www.baidu.com");
        wait(driver, 1000L);
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='�ٶ�һ��']")).isDisplayed());
        Assert.assertEquals(driver.getTitle(), "�ٶ�һ�£����֪��");
        driver.quit();

    }
	
	private void wait(Object object, long timeout){
		synchronized (object) {
			try {
				object.wait(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread()+" wait done!");
		}
	}

}
