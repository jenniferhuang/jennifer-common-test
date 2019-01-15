package com.jennifer.exceptionusuage;

/**
 * Created by com.jennifer.huang on 5/28/18.
 */
public class BadCustomExeptionUsing extends Exception {


    BadCustomExeptionUsing() {
    }

    BadCustomExeptionUsing(String message) {
        super(message);
    }

    BadCustomExeptionUsing(String message, Throwable cause) {
        super(message, cause);
    }

    BadCustomExeptionUsing(Throwable cause) {
        super(cause);
    }

    BadCustomExeptionUsing(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
