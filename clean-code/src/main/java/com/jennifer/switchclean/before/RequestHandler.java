package com.jennifer.switchclean.before;

/**
 * Created by jennifer.huang on 5/14/18.
 */
public class RequestHandler {

    public void handleRequest(RequestActions action) {
        switch (action) {
            case LOGIN:
//                doLogin();
                break;
            case LOGOUT:
//                doLogout();
                break;
            case QUERY:
//                doQuery();
                break;
        }
    }


}
