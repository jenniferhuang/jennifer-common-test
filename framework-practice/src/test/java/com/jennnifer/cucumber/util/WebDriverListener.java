package com.jennnifer.cucumber.util;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 9/13/2017
 */
public class WebDriverListener implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()){
            WebDriver webDriver = DriverFactory.createInstance();
            DriverManager.setWebDriver(webDriver);
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()){
            WebDriver driver = DriverManager.getWebDriver();
            if(driver!=null){
                driver.quit();
                DriverManager.setWebDriver(null);
            }
        }
    }
}
