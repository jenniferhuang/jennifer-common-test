package com.jennnifer.cucumber.tests;

import com.jennnifer.cucumber.util.DriverManager;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 9/28/2017
 */
public class ThreadLocalDiffDriverTest {


    @Test
    public void testMethod1() {
        invokeBrowser("http://www.ndtv.com");
    }

    @Test
    public void testMethod2() {
        invokeBrowser("http://www.facebook.com");

    }

    private void invokeBrowser(String url) {
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + DriverManager.getWebDriver().hashCode());
        DriverManager.getWebDriver().get(url);
    }
}
