package com.jennifer.switchclean.after;

import java.util.Map;

/**
 * Created by jennifer.huang on 5/14/18.
 */
public class RequestHandler {

    private Map<Integer, Command> commandMap; // injected in, or obtained from a factory

    public void handleRequest(int action) {
        Command command = commandMap.get(action);
        command.execute();
    }

}
