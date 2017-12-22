package com.jennnifer.cucumber.util.util2;

import org.openqa.selenium.WebDriver;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 9/13/2017
 */
public class DriverManager {


    //http://docs.oracle.com/javase/6/docs/api/java/lang/ThreadLocal.html
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver){
        webDriver.set(driver);
    }

}
