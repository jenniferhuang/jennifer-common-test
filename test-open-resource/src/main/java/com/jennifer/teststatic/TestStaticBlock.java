package com.jennifer.teststatic;

import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 3/9/18.
 */
public class TestStaticBlock {
    static{
        System.setProperty("test1","11111");
    }

    @Test
    public void test(){
        System.out.println(System.getProperty("test1"));

    }
}
