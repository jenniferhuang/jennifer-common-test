package com.jennifer.exceptionusuage;

import java.util.List;

/**
 * Created by com.jennifer.huang
 */


//Think about this is a Helper class , and will be called in test scripts.

public class ExceptionBadCodes {

    //Scenario 1:
    public void consumeAndForgetAllExceptions() {
        try {
            //some code that throws exceptions
        } catch (Exception ex) {
            ex.printStackTrace(); //eat out all exceptions
        }
    }

    //Scenario 2:
    public void tryAllInException() {
        try {
            //code1 that has no checked Exception
            //code2 that will throw exception
            //code3 that has no checked Exception,    but it has unchecked exception like "IndexOutOfBoundsException", how it goes?
        } catch (Exception ex) {
            //handle the exception from code2.

            //but  when code1, code3 throws Exceptions?
        }
    }






    //Scenario 3:
    public void someMethod() throws Exception {  //Avoid throwing an unspecific Exception.
        //no codes will throw exception;
        //new a Exception
    }


    //Scenario 4:
    public void howAboutThrowCustomException() throws BadCustomExeptionUsing {
        try {
            //code1 that has no checked Exception
            //code2 that will throw exception,
            //code3 that has no checked Exception,    but it has unchecked exception, how it goes?
        } catch (Exception ex) {  //same problem with "Scenario 2"
            throw new BadCustomExeptionUsing(ex);  //so all exception are from code2  ???
        }
    }


    //Scenario 5:
    public void howAboutThrowCustomException2() throws BadCustomExeptionUsing {
        try {
            //code2 that will throw exception,
        } catch (Exception ex) {  //same problem with "Scenario 2"
            throw new BadCustomExeptionUsing("API Exception"); // Consuming exception, lose the stack trace and message of the original exception which will make it difficult to analyze the exceptional event that caused your exception.
        }
    }


}
