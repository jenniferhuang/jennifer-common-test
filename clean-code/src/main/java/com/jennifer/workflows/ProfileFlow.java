package com.jennifer.workflows;

import java.util.Map;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public abstract class ProfileFlow implements INavigator {


    static class A extends ProfileFlow {


        @Override
        public void execute(String... parameters) {
//            PathConstant.myProfilePath1;  //execute this.
            System.out.println("execute:" + PathConstant.myProfilePath1);

        }
    }

    static class B extends ProfileFlow {

        @Override
        public void execute(String... parameters) {
//            PathConstant.myProfilePath2; //execute this
            System.out.println("execute:" + PathConstant.myProfilePath2);

        }
    }


    public static ProfileFlow profileFlowA = new ProfileFlow.A();
    public static ProfileFlow profileFlowB = new ProfileFlow.B();


//    @Override
//    public void execute(String path, String... parameters) {
//
//        if (path.equals(PathConstant.myProfilePath1)) {
//            System.out.println("profile flow:");
//            WorkFlowFactory.getWorkFlow(PathConstant.conversationPath1).execute(PathConstant.conversationPath1);
//            System.out.println("-c");
//
//        } else if (path.equals(PathConstant.myProfilePath2)) {
//            System.out.println(("profile flow:" + PathConstant.myProfilePath2));
//        }
//
//    }

//    @Override
//    public void registerMap(Map map) {
//        map.put(PathConstant.myProfilePath1, A);
//        map.put(PathConstant.myProfilePath2, B);
//
//    }


}
