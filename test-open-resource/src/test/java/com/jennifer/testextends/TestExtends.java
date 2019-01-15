package com.jennifer.testextends;

import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 12/17/18.
 */
public class TestExtends {

    @Test
    public void test11(){
        MySupperClass mySupperClass = new MySubClass();
        mySupperClass.waitElement();
    }
}
