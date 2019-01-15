package com.jennifer.teststatic;

import org.testng.annotations.Test;

/**
 * Created by com.jennifer.huang on 3/9/18.
 */
public class TestStaticBlock2 {
    public static String testValue = System.getProperty("test1");


    @Test
    public void test(){
        System.out.println(System.getProperty("test1"));

    }
}
