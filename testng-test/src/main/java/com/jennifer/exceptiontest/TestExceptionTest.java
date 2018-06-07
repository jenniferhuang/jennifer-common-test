package com.jennifer.exceptiontest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 6/5/18.
 */
public class TestExceptionTest {


    @Test
    public void test() throws Exception {
        throw new Exception("Exception Occurs!");
    }


    @AfterMethod
    public void afterTest() {
        System.out.println("still execute??????????");

    }
}
