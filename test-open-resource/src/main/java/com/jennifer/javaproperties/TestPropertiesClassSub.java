package com.jennifer.javaproperties;

import com.jennifer.javaproperties.subproperties.TestPropertiesClassBase;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 12/22/2017
 */
public class TestPropertiesClassSub extends TestPropertiesClass {
    private String test1 = getProperty("test1");
    private String test2 = getProperty("test2");
    private String browser = getProperty("browser");
    private String nameID = getMobileElement("name.id");


    @Test
    public void testGetProperty() {
        System.out.println(nameID);  //override
        System.out.println(nameXpath);//keep super class value
        System.out.println(browser);  //override
        System.out.println(seleniumurl); //keep super class value
        System.out.println(seleniumport); //keep super class value
        System.out.println(test1);  //new
        System.out.println(test2); //new

    }
}
