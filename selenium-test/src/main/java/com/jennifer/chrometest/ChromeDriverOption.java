package com.jennifer.chrometest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 5/26/2017
 */
public class ChromeDriverOption {


    public void test() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        // Add ChromeDriver-specific capabilities through ChromeOptions.
        ChromeOptions options = new ChromeOptions();
        List arguments = new ArrayList();
        arguments.add("--disable-user-media-security");
        arguments.add("--use-fake-ui-for-media-stream");
        arguments.add("--use-fake-device-for-media-stream");
        arguments.add("--disable-web-security");
        arguments.add("--reduce-security-for-testing");
        options.addArguments(arguments);


        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);

    }
}
