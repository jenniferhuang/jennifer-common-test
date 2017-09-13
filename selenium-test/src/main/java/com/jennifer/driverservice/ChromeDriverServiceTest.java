package com.jennifer.driverservice;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/21/2017
 */
public class ChromeDriverServiceTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() {
        setEnvironment();
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }


    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }


    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }


    private static void setEnvironment() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "/additions/chrome-driver/chromedriver.exe");
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir")+"\\selenium-test";
        System.setProperty("webdriver.chrome.driver",path+"/additions/chrome-driver/chromedriver.exe");
        System.out.println("abc: "+System.getProperty("webdriver.chrome.driver"));
    }
}
