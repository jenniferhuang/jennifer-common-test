package com.jennifer.javaproperties;

import com.jennifer.javaproperties.subproperties.TestPropertiesClassBase;
import org.testng.annotations.Test;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 12/22/2017
 */
public class TestPropertiesClass extends TestPropertiesClassBase{
    private String browser = getProperty("browser");
    protected String seleniumurl = getProperty("selenium.url");
    protected String seleniumport = getProperty("selenium.port");
    private String nameID = getMobileElement("name.id");
    protected String nameXpath = getMobileElement("name.xpath");






    @Test
    public void testBundlue(){
        Properties properties = new Properties();
         ResourceBundle source = ResourceBundle.getBundle(this.getClass().getName());
        Enumeration<String> keys = source.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key +" "+ source.getString(key));
            properties.put(key, source.getString(key));
        }
        System.out.println(properties.size());
        properties.put("browser","test");
        System.out.println(properties.size());
        System.out.println(properties.get("browser"));
    }








@Test
    public void testGetProperty(){
        System.out.println(nameID);
        System.out.println(nameXpath);
        System.out.println(browser);
        System.out.println(seleniumurl);
        System.out.println(seleniumport);

    }
}
