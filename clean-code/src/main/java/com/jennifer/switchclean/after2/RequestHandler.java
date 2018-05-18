package com.jennifer.switchclean.after2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jennifer.huang on 5/14/18.
 */
public class RequestHandler {
    private static final Map<RequestActions, Command> commandMap;

    static {
        final Map<RequestActions, Command> commands = new HashMap();
        commands.put(RequestActions.LOGIN, new Command() {
            public void execute() {
                //doLogin();
                System.out.println("test here, login handling.");
            }
        });

        commands.put(RequestActions.LOGOUT, new Command() {
            public void execute() {
                //doLogout();
            }
        });

        commands.put(RequestActions.QUERY, new Command() {
            public void execute() {
                //doQuery();
            }
        });

        commandMap = Collections.unmodifiableMap(commands);
    }


    public void handleRequest(RequestActions action) {
        Command command = commandMap.get(action);
        command.execute();
    }

}
