package com.jennifer.testlifecycle;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 2/10/2017
 */
public class InitClass {
    static {
        System.out.println("初始化InitClass");
    }
    public static String a = null;
    public static void method(){}
}
