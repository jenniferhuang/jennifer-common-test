package com.jennifer.switchclean.after2;

import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 5/17/18.
 */
public class RequestClient {



    @Test
    public void login222(){
        RequestHandler  requestHandler = new RequestHandler();
        requestHandler.handleRequest(RequestActions.LOGIN);
    }

}
