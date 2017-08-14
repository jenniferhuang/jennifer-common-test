package com.jennifer.chrometest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 7/25/2017
 */
public class TestVersionExample {

    public static void main(String[] args) {
        DesiredCapabilities desireCap = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", "D:\\ProgramFiles\\selenium-otherVersions\\chromedriver.exe");
        WebDriver dr = new ChromeDriver(desireCap);
        dr.get("http://www.baidu.com/");
        dr.findElement(By.id("kw1")).sendKeys("selenium");
        dr.findElement(By.id("su1")).click();
        System.out.println("title:" + dr.getTitle());

    }



    @Test
    public void test_2_22() throws InterruptedException {  //selenium 2.53.1
        DesiredCapabilities desireCap = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", "D:\\ProgramFiles\\selenium-otherVersions\\chromedriver.exe"); //Driver 2.22
        WebDriver dr = new ChromeDriver(desireCap);
        dr.get("http://www.baidu.com/");
        dr.findElement(By.id("kw")).sendKeys("selenium");
        dr.findElement(By.id("su")).click();
        System.out.println("title:" + dr.getTitle());
        dr.quit();
    }

    //org.openqa.selenium.remote.UnreachableBrowserException: Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure.

    @Test
    public void test_2_29() throws InterruptedException {  //selenium 2.53.1
        DesiredCapabilities desireCap = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", "D:\\ProgramFiles\\selenium\\chromedriver.exe"); //Driver 2.29
        WebDriver dr = new ChromeDriver(desireCap);
        dr.get("http://www.baidu.com/");
        dr.findElement(By.id("kw")).sendKeys("selenium");
        dr.findElement(By.id("su")).click();
        System.out.println("title:" + dr.getTitle());
        dr.quit();

    }


}
