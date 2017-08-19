package com.jennifer.javaproperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/19/2017
 */
public class PropertyReaderUtil {
    private PropertyReaderUtil(){loadProperties();}

    private static PropertyReaderUtil instance = null;

    public static PropertyReaderUtil getInstance(){
        if (instance == null) {
            synchronized (PropertyReaderUtil.class) {
                if (instance == null) {
                    instance = new PropertyReaderUtil();
                }
            }
        }
        return instance;
    }


    Properties properties = new Properties();
    InputStream inputStream = null;

//    public PropertyReaderUtil() {
//        loadProperties();
//    }

    private void loadProperties() {
        try {
            System.out.println("execute times");
            inputStream = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String readProperty(String key) {
        return null==System.getProperty(key)?properties.getProperty(key):System.getProperty(key);
    }

}
