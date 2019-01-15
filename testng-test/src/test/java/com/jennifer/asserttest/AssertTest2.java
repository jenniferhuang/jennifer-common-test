package com.jennifer.asserttest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 5/5/2017
 */
public class AssertTest2 {


    @BeforeMethod
    public void befreMethod(){
        System.out.println("before Mehtod");
    }







//    mvn test -Dtest=MyTest2#verifyData1 -Ddataproviderthreadcount=3

    @Test
    public void testOriginalAssert(){
        System.out.println("before assert test--Original");
        Assert.assertEquals(1, 2);
        System.out.println("after assert test");
    }

    @Test
    public void testSoftAssert(){
        System.out.println("before assert test--Soft, catch");
        softAssert(1, 2);
        System.out.println("after assert test  --No tag as fail");
    }





    private static Boolean softAssert(Object actual, Object expected){
        try{
            Assert.assertEquals(actual,expected);
            return true;
        }catch(Error e){
            System.out.println("test fail expected: "+ expected + " Actual: "+actual);

            return false;
        }
    }













    @AfterMethod
    public void afterMethod(){
        System.out.println("after Mehtod");
    }
}
