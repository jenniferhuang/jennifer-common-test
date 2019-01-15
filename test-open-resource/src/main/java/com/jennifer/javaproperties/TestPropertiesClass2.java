package com.jennifer.javaproperties;

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
public class TestPropertiesClass2 extends TestPropertiesClassBase{
    private String test1 = getProperty("test1");
    private String test2 = getProperty("test2");













@Test
    public void testGetProperty(){
        System.out.println(test1);
        System.out.println(test2);

    }
}
