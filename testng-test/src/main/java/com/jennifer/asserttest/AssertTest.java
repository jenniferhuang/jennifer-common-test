package com.jennifer.asserttest;

import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 5/5/2017
 */
public class AssertTest {


    @BeforeMethod
    public void befreMethod(){
        System.out.println("before Mehtod");
    }









    @Test
    public void testOrigianlAssert(){
        System.out.println("before assert test");
        Assert.assertEquals(1, 2);
        System.out.println("after assert test");
    }

    @Test
    public void testSoftAssert(){
        System.out.println("before assert test");
        softAssert(1, 2);
        System.out.println("after assert test");
    }


    @Test
    public void testHardAssert(){
        System.out.println("before assert test");
        hardAssert(softAssert(1, 2));
        System.out.println("after assert test");
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

    private static void hardAssert(boolean result){
        if(!result) {
            throw new TestException("Assertion error");
        }

    }











    @AfterMethod
    public void afterMethod(){
        System.out.println("after Mehtod");
    }
}
