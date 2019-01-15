package com.jennifer.javaproperties.android;

import com.jennifer.javaproperties.ITestPropertiesSameClass;
import com.jennifer.javaproperties.subproperties.TestPropertiesClassBase;
import org.testng.annotations.Test;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 12/22/2017
 */
public class TestPropertiesSameClass extends TestPropertiesClassBase implements ITestPropertiesSameClass {
    private String browser = getProperty("browser");
    protected String seleniumurl = getProperty("selenium.url");
    protected String seleniumport = getProperty("selenium.port");
    private String nameID = getMobileElement("name.id");
    protected String nameXpath = getMobileElement("name.xpath");


    @Test
    public void testGetProperty() {
        System.out.println(nameID);
        System.out.println(nameXpath);
        System.out.println(browser);
        System.out.println(seleniumurl);
        System.out.println(seleniumport);

    }
}
