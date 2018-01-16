package com.jennifer.withnoserver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 1/14/18.
 */

/**
 * withnoserver means:
 * starting chromedriver server locally
 * instead of starting the selenium standalone server
 *
 *
 * http://www.seleniumhq.org/docs/03_webdriver.jsp

 */
public class TestLocalDriver {



    @Test
    public void testlocal(){
        DesiredCapabilities desireCap = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir")+"/serverlog/chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/additions/chrome-driver/chromedriver_mac64");
        WebDriver dr = new ChromeDriver(desireCap);
        dr.get("http://www.baidu.com/");
        dr.findElement(By.id("kw")).isDisplayed();
        dr.findElement(By.id("kw")).sendKeys("selenium");
        dr.findElement(By.id("su")).click();
        System.out.println("title:" + dr.getTitle());
        dr.quit();
    }
}
