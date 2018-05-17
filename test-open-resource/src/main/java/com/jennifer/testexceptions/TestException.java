package com.jennifer.testexceptions;

import org.testng.annotations.Test;

import java.util.concurrent.TimeoutException;

public class TestException {


    //checked Exception unchecked Exception:  https://beginnersbook.com/2013/04/java-throws/

    //Checked Exception handling ways:  1.try..catch (1.1 changed to unchecked and like 'print';   1.2  changed to unchecked and throw unchecked exception.) 2. continue throws


    /**
     * checked exception
     *
     * @throws TimeoutException
     */

    @Test
    public void testThrow() throws TimeoutException { //1. just throw and no hanlde, so this test is failed
        String s = "a";
        if (s.equals("b")) return;
        throw new TimeoutException(this.getClass().getSimpleName() + " not loaded"); //throw exception, code require you to tackle it, try.. catch, or continue throw
    }


    @Test
    public void testTryCatchHandle() {
        String s = "a";
        if (s.equals("b")) return;
        try {
            throw new TimeoutException(this.getClass().getSimpleName() + " not loaded"); //throw exception, code require you to tackle it, try.. catch, or continue throw
        } catch (TimeoutException e) {
            e.printStackTrace();  //2. catch tackle -> known issue, so this test is passed
        }

    }

    @Test
    public void testTryAndThrow() throws TimeoutException { //prior to use TimeoutException
        String s = "a";
        if (s.equals("b")) return;
        try {
            throw new TimeoutException(this.getClass().getSimpleName() + " not loaded");
        } catch (TimeoutException e) {
            e.printStackTrace();  //catch tackle -> known issue, so this test is passed
        }

    }


    /**
     * unchecked exceptions
     */

    @Test
    public void testExceptionUnchecked1() {
        String s = "a";
        if (s.equals("b")) return;
        throw new RuntimeException(this.getClass().getSimpleName() + " not loaded");
    }

    @Test
    public void testExceptionUnchecked2() throws RuntimeException {
        String s = "a";
        if (s.equals("b")) return;
        throw new RuntimeException(this.getClass().getSimpleName() + " not loaded");
    }


    private void runTimeExceptionThrow(int i) {
        if (i == 1) {
            throw new RuntimeException("i can't not ==1");
        } else {
            System.out.println(i);
        }
    }

    @Test
    public void testRunTimeException() {
        int i = 2;
        runTimeExceptionThrow(i);
        i = 1;
        try {
            runTimeExceptionThrow(i);
        } catch (RuntimeException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }


    /**
     * @param i
     * @throws Exception
     */

    private void customThrow(int i) throws Exception {
        Exception e = null;
        if (i == 1) {
            e = new CustomExceptionUnchecked("i can't not ==1");
        } else if (i == 2) {
            e = new Exception("i can't not ==2");
        } else {
            System.out.println(i);
            return;
        }
        throw e;
    }


    @Test
    public void testKindsException() throws Exception {  //my service tackle with this Exception;
        for (int i = 0; i < 3; i++) {
            try {
                customThrow(i);
            } catch (CustomExceptionUnchecked e) {
                System.out.println("I will skip this exception and: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("I will throw out this exception.");
                throw e;
            }

        }

    }

}


//


/**
 * When to self define Exception , extends the exception?
 * <p>
 * <p>
 * <p>
 * <p>
 * http://niehan.blog.techweb.com.cn/archives/259.html
 * <p>
 * Client  tackle??:    recover? no way?
 * <p>
 * <p>
 * "而C++和C#都是根本没有Checked exception，它们所有的异常都是unchecked。 --> 自己处理掉"
 * <p>
 * unchecked exception都是RuntimeException的子类
 * <p>
 * http://niehan.blog.techweb.com.cn/archives/259.html
 * <p>
 * Client  tackle??:    recover? no way?
 * <p>
 * <p>
 * "而C++和C#都是根本没有Checked exception，它们所有的异常都是unchecked。 --> 自己处理掉"
 * <p>
 * unchecked exception都是RuntimeException的子类
 */


/**
 * http://niehan.blog.techweb.com.cn/archives/259.html
 *
 * Client  tackle??:    recover? no way?
 *
 *
 * "而C++和C#都是根本没有Checked exception，它们所有的异常都是unchecked。 --> 自己处理掉"
 *
 * unchecked exception都是RuntimeException的子类
 */
