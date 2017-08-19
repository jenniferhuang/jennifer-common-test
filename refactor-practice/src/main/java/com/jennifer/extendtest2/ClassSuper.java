package com.jennifer.extendtest2;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/18/2017
 */
public abstract class ClassSuper {
    String s2="common2";
    String s3="common3";


    public abstract void method1(String app);

    public void method2(String app){
        method1(app);
        System.out.println(s2);
    }

    public void method3(String app){
        method1(app);
        System.out.println(s3);
    }



}
