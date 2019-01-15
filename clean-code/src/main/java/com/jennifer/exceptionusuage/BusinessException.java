package com.jennifer.exceptionusuage;

/**
 * Created by com.jennifer.huang on 5/29/18.
 */
public class BusinessException extends Exception{
    private final ErrorCode code;


    private BusinessException(ErrorCode code) {
        this.code = code;
    }

    private BusinessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    private BusinessException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    private BusinessException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    private BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
