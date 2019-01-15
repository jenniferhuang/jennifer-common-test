package com.jennifer.extendtest2;

import com.jennifer.extendtest.*;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 8/18/2017
 */


public class TestExtend2 {

    @org.junit.Test
    public void test(){  //Test folder --> The same package with com.com.jennifer.extendtest2.ClassSuper, so it need't import it.
        String app = "widget";
        ClassSuper c = null;
        if(app.equals("widget")){
            c = new ClassSub1();
        }
        c.method2(app);

    }


    @org.junit.Test
    public void test1(){
        com.jennifer.extendtest.ClassSuper c1 = new com.jennifer.extendtest.ClassSub1();
        com.jennifer.extendtest.ClassSuper c2 = new com.jennifer.extendtest.ClassSub2();
        c1.method2();
        c2.method2();
    }


}
