package com.jennifer.javaproperties;

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
public class TestPropertiesClass {

    static Properties properties = new Properties();


    private Properties mergeProperties(Properties properties,ResourceBundle source, boolean override) {
        if (source != null) {
            Enumeration<String> keys = source.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                if (override) {
                    properties.put(key, source.getString(key));
                }
            }
        }
        return properties;
    }

    private Properties collectProperties() {
        Properties propertiesX = new Properties();
        for (Class cClass = this.getClass(); !cClass.equals(TestPropertiesClass.class); cClass = cClass.getSuperclass()) {
            try {
                 propertiesX = mergeProperties(properties, ResourceBundle.getBundle(cClass.getName()), false);
            } catch (MissingResourceException e) {
                //in case no properties for class found, ignore
            }
        }
        return propertiesX;
    }

    @Test
    public void testBundlue(){
         ResourceBundle source = ResourceBundle.getBundle(this.getClass().getName());
        Enumeration<String> keys = source.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key +" "+ source.getString(key));
            properties.put(key, source.getString(key));
        }
        System.out.println(properties.size());
    }


    public static void main(String[] args) {
        TestPropertiesClass ob = new TestPropertiesClass();
        System.out.println(ob.getClass().getName());
        Properties properties = ob.collectProperties();
        System.out.println(properties.size());
        System.out.println(properties.get("browser"));
    }
}
