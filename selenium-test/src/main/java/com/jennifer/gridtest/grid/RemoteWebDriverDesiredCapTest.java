package com.jennifer.gridtest.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RemoteWebDriverDesiredCapTest {
	@Test
	public void myTest() throws MalformedURLException{
		
		
		/**
		 * 1�����ػ������������ڵ�hub    ����̨����IP��10.32.50.26��http://localhost:4444/grid/console �鿴�ڵ����������
		 * java -jar selenium-server-standalone-2.41.0.jar -role hub  ��Ĭ�϶˿�4444��
		 * 2������Ҳ������һ����hub  �νڵ�A   
		 * java -jar selenium-server-standalone-2.41.0.jar -role node   
		 * ��Ĭ�ϵ�ע���ַ��http://localhost:4444/grid/register��nodeĬ�϶˿ڣ�5555��
		 * ���������ã�java -jar selenium-server-standalone-2.41.0.jar -role node -port 5556 -hub http://localhost:4441/grid/register��
		 * 3��������װ��firefox, chrome������ͨ���ôνڵ�A�� ������firefox�����нű��� Ҳ������chrome
		 * FF����Ĭ����webDriver���ˣ�   ��chrome������Ҫ�Լ�ָ���� chromeDriver.exe ������chromeDriver��Ĭ��ʹ�ö˿�9515��
		 *  
		 */
		String hubUrl1 = "http://10.32.36.50:4444/wd/hub";
		String hubUrl2 = "http://localhost:4444/wd/hub";
//		String hubUrl3 = "http://10.32.50.26:5555/wd/hub";
		
		
		//node:java -jar selenium-server-standalone-2.41.0.jar -role node 
		DesiredCapabilities capability = DesiredCapabilities.firefox();
        WebDriver driver = new RemoteWebDriver(new URL(hubUrl2), capability);
        driver.get("http://www.baidu.com");
        wait(driver, 1000L);
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='�ٶ�һ��']")).isDisplayed());
        Assert.assertEquals(driver.getTitle(), "�ٶ�һ�£����֪��");
        driver.quit();
        
        
   
        
        // node: ֱ������chromedriver.exeʱ�� ͨ��http://xxx.xxx.xxx.xxx:9515 �Ϳ���ȡ��chrome��   ������ ����ָ��-Dwebdriver.chrome.driver -port����ʽ
        DesiredCapabilities aDesiredcap =new DesiredCapabilities("chrome","",Platform.VISTA);
        WebDriver wd = new RemoteWebDriver(new URL("http://localhost:9515"), aDesiredcap);  
        wd.get("https://code.google.com/p/selenium/wiki/Grid2");
        System.out.println(wd.getCurrentUrl());
        wd.quit();
        
        
		//node:java -jar selenium-server-standalone-2.41.0.jar -role node -hub http://10.32.50.26:4444/grid/register -Dwebdriver.ie.driver=IEDriverServer.exe -browser browserName=iexplore -port 7776
        DesiredCapabilities iDesiredcap =new DesiredCapabilities("iexplore","",Platform.VISTA);
        WebDriver wdE = new RemoteWebDriver(new URL("http://10.32.50.26:7776/wd/hub"), iDesiredcap);  
        wdE.get("https://code.google.com/p/selenium/wiki/Grid2");
        System.out.println(wdE.getCurrentUrl());
        wdE.quit();
        
        
        
        
		
        
        /**
         * 1��ͬ���ñ����������ڵ�hub
         * 2������һ̨������Ϊ�νڵ����� ��ci-win01 10.32.52.51��
         * ��51�����Ͽ��������νڵ�hub, һ��ר����Ϊfirefox,һ��Ϊchrome
         * java -jar selenium-server-standalone-2.29.0.jar -role node -hub http://10.32.50.26:4444/grid/register -Dwebdriver.firefox.bin=��C:/Program Files/Mozilla Firefox/firefox.exe��  -browser browserName=firefox  (Ĭ��5555)
         * java -jar selenium-server-standalone-2.29.0.jar -role node -hub http://10.32.50.26:4444/grid/register -Dwebdriver.chrome.driver=chromedriver29.exe  -browser browserName=chrome  -port 6666 ������˿ڣ���ֹ������firefox���ظ���
         * 
         */
		
		
		//node: java -jar selenium-server-standalone-2.29.0.jar -role node -hub http://10.32.50.26:4444/grid/register -Dwebdriver.chrome.driver=chromedriver29.exe  -browser browserName=chrome  -port 6666
        DesiredCapabilities chrome =new DesiredCapabilities("chrome","",Platform.XP);
//        WebDriver driverChrome = new RemoteWebDriver(new URL("http://10.32.52.51:6666/wd/hub"), chrome);  
//        driverChrome.get("http://www.cnblogs.com/");
//        driverChrome.quit();
        
        
//        DesiredCapabilities iexplore =new DesiredCapabilities("iexplore","",Platform.XP);
//        WebDriver driverIexplore = new RemoteWebDriver(new URL("http://10.32.52.51:5556"), iexplore);  
//        driverIexplore.get("http://www.cnblogs.com/");
//        driverIexplore.quit();
//        
//        DesiredCapabilities firefox =new DesiredCapabilities("firefox","",Platform.XP);
//        WebDriver driverFirefox = new RemoteWebDriver(new URL("http://10.32.52.51:5555/wd/hub"), firefox);  
//        driverFirefox.get("http://www.cnblogs.com/");
//        driverFirefox.quit();

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
