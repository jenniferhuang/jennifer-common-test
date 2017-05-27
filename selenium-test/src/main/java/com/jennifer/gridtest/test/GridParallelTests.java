package com.jennifer.gridtest.test;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GridParallelTests {
	private WebDriver dr;
	DesiredCapabilities desireCap;
	
	@Parameters({ "browser", "nodeUrl"})
	@BeforeMethod
	public void setUp(String browser, String nodeUrl) {
		if (browser.equals("ie")){
			desireCap = DesiredCapabilities.internetExplorer();
		}
		else if (browser.equals("ff"))
			desireCap = DesiredCapabilities.firefox();
		else if (browser.equals("chrome"))
			desireCap = DesiredCapabilities.chrome();
		else
			System.out.println("browser parameter wrong , can only be :ie�� ff��chrome");
		
		String url = nodeUrl + "/wd/hub";
		URL urlInstance = null;
		try {
			urlInstance = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		dr = new RemoteWebDriver(urlInstance, desireCap);
	}

	@Test
	public void test() {
		dr.get("http://www.baidu.com/");
		dr.findElement(By.id("kw1")).sendKeys("selenium");
		dr.findElement(By.id("su1")).click();
		System.out.println("title:" + dr.getTitle());
	}
	@AfterMethod
	public void quit() {
		dr.close();
	}
}
