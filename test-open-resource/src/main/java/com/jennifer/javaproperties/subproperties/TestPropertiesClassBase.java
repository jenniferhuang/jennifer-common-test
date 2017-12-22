package com.jennifer.javaproperties.subproperties;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 12/22/2017
 */
public class TestPropertiesClassBase {
    private Properties properties = new Properties();


    private void collectProperties() {
        if (properties.isEmpty()) {
            for (Class cClass = this.getClass(); !cClass.equals(TestPropertiesClassBase.class); cClass = cClass.getSuperclass()) {
                ResourceBundle source = ResourceBundle.getBundle(cClass.getName());
                Enumeration<String> keys = source.getKeys();
                while (keys.hasMoreElements()) {
                    String key = keys.nextElement();
                    System.out.println(key + " " + source.getString(key));
                    properties.put(key, source.getString(key));
                }
            }
        }
    }


    public String getProperty(String key) {
         collectProperties();
        return (String) properties.get(key);

    }

    public String getMobileElement(String key) {
        collectProperties();
        key.endsWith()
        if(properties.containsKey(key+".id")){
            return "getById "+properties.get(key+".id");
        }else if (properties.containsKey(key+".xpath")){
            return "getByXpath "+properties.get(key+".xpath");
        }
        return null;
    }
}
