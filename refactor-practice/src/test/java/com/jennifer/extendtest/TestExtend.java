package com.jennifer.extendtest;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/18/2017
 */
public class TestExtend {


    @org.junit.Test
    public void test(){
        ClassSuper  c1 = new ClassSub1();
        ClassSuper  c2 = new ClassSub2();

        c1.method2();

        c2.method2();
    }


}
