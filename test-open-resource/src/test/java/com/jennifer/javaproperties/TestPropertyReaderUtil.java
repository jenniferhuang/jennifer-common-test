package com.jennifer.javaproperties;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 8/19/2017
 */
public class TestPropertyReaderUtil {
    @org.junit.Test
    public void test() {
        PropertyReaderUtil testReadProperty = PropertyReaderUtil.getInstance();
        System.out.println("result: " + testReadProperty.readProperty("selenium.browsertype"));
        System.out.println("result: " + testReadProperty.hashCode());
    }

    @org.junit.Test
    public void test2() {
        PropertyReaderUtil testReadProperty = PropertyReaderUtil.getInstance();
        System.out.println("result2: " + testReadProperty.readProperty("selenium.browsertype"));
        System.out.println("result2: " + testReadProperty.hashCode());
    }

    @org.junit.Test
    public void test3(){
        try {
            InputStream fis = PropertyReaderUtil.getInstance().inputStream;
            int i;
            char c;
            System.out.println("test+++");
            while ((i = fis.read()) != -1) {
                // converts integer to character
                c = (char) i;
                System.out.print("test++++"+c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * D:\repository\MySelf\com.jennifer-common-test\test-open-resource> mvn test -Dtest=TestPropertyReaderUtil
     *
     *
     *mvn test -Dtest=TestPropertyReaderUtil
     *
     *
     */

}
