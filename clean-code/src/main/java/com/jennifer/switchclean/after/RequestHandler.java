package com.jennifer.switchclean.after;

import java.util.Map;

/**
 * Created by com.jennifer.huang
 */
public class RequestHandler {


    public void handleRequest(RequestActions requestAction) {
        Command command = CommandFactory.getCommand(requestAction);
        command.execute();
    }

}
