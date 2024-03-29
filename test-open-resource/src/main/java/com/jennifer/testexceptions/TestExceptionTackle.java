package com.jennifer.testexceptions;

import org.testng.annotations.Test;

import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 4/1/2017
 */
public class TestExceptionTackle {

    public void testThrowException(Boolean b) throws Exception {
        if (b)
            System.out.println("execute pass");
        else
            throw new Exception("Throw exception, the value should be true");
    }


    public static void main(String[] args) {
        TestExceptionTackle testExceptionTackle = new TestExceptionTackle();
        try {
            testExceptionTackle.testThrowException(true);
            testExceptionTackle.testThrowException(false);  //testThrowException  is possible to throw exception, I catch it, and do not pass to the next.
        } catch (Exception e) {
            System.out.println("exception, ");
            System.out.println(e.getMessage());
        }
        System.out.println("Exception is catched and tackle, so will not be interupped.");
    }


    @Test
    public void testNoCatch() throws Exception {
        TestExceptionTackle testExceptionTackle = new TestExceptionTackle();
        //Bow I call this method: testThrowException is possible to throw exception,
        // But I want not to tackle.
        // if exception occurs, run this method will be interruped, if other method call this me:testNoCatch(), the exception is passed to it.
        testExceptionTackle.testThrowException(false);
        System.out.println("Will be interupped in the previous step, this step is not reachable.");
    }


    public void testException1(int i) throws Exception {
        if (i == 1) {
            throw new Exception("test exception");
        } else {
            System.out.println(i);
        }
    }

    public void testException2() {
        throw new NoSuchElementException("test sub exception");
    }

    @Test
    public void testCatch() throws Exception {
        try {
            this.testException1(2);
            this.testException2();
        } catch (NoSuchElementException e) {
            System.out.println("caught");
        }
    }


}
