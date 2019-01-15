package com.jennifer.workflows;

import java.util.Map;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public abstract class ConversationFlow implements INavigator {

    public static ConversationFlow conversationFlowA = new ConversationFlow.ConversationA();
    public static ConversationFlow conversationFlowB = new ConversationFlow.ConversationB();



    static class ConversationA extends ConversationFlow {

        @Override
        public void execute(String... parameters) {
            System.out.println("execute: " + PathConstant.conversationPath1);

        }
    }

    static class ConversationB extends ConversationFlow {

        @Override
        public void execute(String... parameters) {
            System.out.println("execute: " + PathConstant.conversationPath2);


        }
    }


//    @Override
//    public void execute(String... parameters) {
////        if (path.equals(PathConstant.conversationPath1)) {
////            System.out.println("conversation flow:" + PathConstant.conversationPath1);
////        } else if (path.equals(PathConstant.myProfilePath2)) {
////            System.out.println("conversation flow:" + PathConstant.conversationPath2);
////        }
//
//    }

//    @Override
//    public void registerMap(Map map) {
//        map.put(PathConstant.conversationPath1, this);
//        map.put(PathConstant.conversationPath2, this);
//
//    }
}
