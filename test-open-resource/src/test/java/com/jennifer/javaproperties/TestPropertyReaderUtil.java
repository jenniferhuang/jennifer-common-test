package com.jennifer.javaproperties;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
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


    /**
     * D:\repository\MySelf\jennifer-common-test\test-open-resource> mvn test -Dtest=TestPropertyReaderUtil
     *
     *
     *mvn test -Dtest=TestPropertyReaderUtil
     *
     *
     */

}
