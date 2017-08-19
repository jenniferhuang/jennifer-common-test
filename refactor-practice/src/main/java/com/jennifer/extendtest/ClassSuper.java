package com.jennifer.extendtest;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/18/2017
 */
public abstract class ClassSuper {
    String s="common";


    public abstract void method1();

    public void method2(){
        method1();
        System.out.println(s);
    }


}
