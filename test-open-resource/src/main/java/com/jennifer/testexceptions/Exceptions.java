package com.jennifer.testexceptions;

/**
 * Created by com.jennifer.huang on 6/8/18.
 */
public class Exceptions {

    public void checkedException() throws Throwable {
        System.out.println("test1");
    }

    public void checkedException2() throws ClassNotFoundException {
        System.out.println("test2");
    }


    public void testCheckedException() throws Throwable {
        checkedException();
    }
    public void testCheckedException2() throws ClassNotFoundException {
        checkedException2();
    }
}
