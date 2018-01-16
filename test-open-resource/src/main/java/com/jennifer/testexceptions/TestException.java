package com.jennifer.testexceptions;

import org.testng.annotations.Test;

import java.util.concurrent.TimeoutException;

public class TestException {



    //checked Exception unchecked Exception:  https://beginnersbook.com/2013/04/java-throws/

    //Checked Exception handling ways:  1.try..catch  2. continue throws


    /**
     * checked exception
     * @throws TimeoutException
     */

    @Test
    public void testThrow() throws TimeoutException { //1. just throw and no hanlde, so this test is failed
        String s="a";
        if(s.equals("b")) return;
        throw  new TimeoutException(this.getClass().getSimpleName() + " not loaded"); //throw exception, code require you to tackle it, try.. catch, or continue throw
    }


    @Test
    public void testTryCatchHandle() {
        String s="a";
        if(s.equals("b")) return;
        try {
            throw  new TimeoutException(this.getClass().getSimpleName() + " not loaded"); //throw exception, code require you to tackle it, try.. catch, or continue throw
        } catch (TimeoutException e) {
            e.printStackTrace();  //2. catch tackle -> known issue, so this test is passed
        }

    }

    @Test
    public void testTryAndThrow() throws TimeoutException { //prior to use TimeoutException
        String s="a";
        if(s.equals("b")) return;
        try {
            throw  new TimeoutException(this.getClass().getSimpleName() + " not loaded");
        } catch (TimeoutException e) {
            e.printStackTrace();  //catch tackle -> known issue, so this test is passed
        }

    }


    /**
     * unchecked exceptions
     */

    @Test
    public void testExceptionUnchecked1() {
        String s="a";
        if(s.equals("b")) return;
        throw  new RuntimeException(this.getClass().getSimpleName() + " not loaded");
    }

    @Test
    public void testExceptionUnchecked2() throws RuntimeException{
        String s="a";
        if(s.equals("b")) return;
        throw  new RuntimeException(this.getClass().getSimpleName() + " not loaded");
    }




    //




    /**
     * When to self define Exception , extends the exception?
     */
}
