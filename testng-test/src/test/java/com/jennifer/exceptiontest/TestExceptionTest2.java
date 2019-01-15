package com.jennifer.exceptiontest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by com.jennifer.huang on 6/5/18.
 */
public class TestExceptionTest2 {

    @Test
    public void test(){

        try {
            throw new Exception("Exception Occurs!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @AfterMethod
    public void afterTest() {
        System.out.println("still execute??????????");

    }




}
