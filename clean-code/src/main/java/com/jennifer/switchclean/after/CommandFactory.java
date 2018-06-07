package com.jennifer.switchclean.after;

/**
 * Created by jennifer.huang
 */
public class CommandFactory {

    public static Command getCommand(RequestActions requestActions) {
        switch (requestActions) {
            case LOGIN:
                return new LoginCommand();
            case LOGOUT:
                return new LogoutCommand();
            case QUERY:
                return new QueryCommand();
        }
        throw new RuntimeException("Your Request Action: " + requestActions.name() + " is not available now.");


    }
}
