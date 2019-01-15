package com.jennifer.testexceptions;

/**
 * Created by com.jennifer.huang on 3/16/18.
 */
public class CustomExceptionUnchecked extends RuntimeException{
    public CustomExceptionUnchecked(String message) {
        super(message);
    }
}
