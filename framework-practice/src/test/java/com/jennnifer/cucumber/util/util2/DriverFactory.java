package com.jennnifer.cucumber.util.util2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 9/13/2017
 */
public class DriverFactory {
    static WebDriver createInstance(){
        System.setProperty("webdriver.chrome.driver", "D:\\ProgramFiles\\selenium\\chromedriver.exe");
        return new ChromeDriver();
    }


}
