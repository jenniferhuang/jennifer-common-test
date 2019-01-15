package com.jennifer.workflows;

import sun.java2d.cmm.Profile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by jennifer.huang on 10/23/18.
 */
public class WorkFlowFactory {

    private static Map<String, INavigator> pathMap = new ConcurrentHashMap();
    private static volatile WorkFlowFactory instance = null;

    private WorkFlowFactory() {
        pathMap.put(PathConstant.myProfilePath1, ProfileFlow.profileFlowA);
        pathMap.put(PathConstant.myProfilePath2, ProfileFlow.profileFlowB);
        pathMap.put(PathConstant.conversationPath1, ConversationFlow.conversationFlowA);
        pathMap.put(PathConstant.conversationPath2, ConversationFlow.conversationFlowB);
    }


    public static WorkFlowFactory getInstance() {
        if (instance == null) {
            synchronized (WorkFlowFactory.class) {
                if (instance == null) {
                    instance = new WorkFlowFactory();
                }
            }
        }
        return instance;
    }

    public INavigator getWorkFlow(String path) {
        return pathMap.get(path);
    }

}
