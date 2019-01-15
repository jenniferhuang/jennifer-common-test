package com.jennifer.testinterfaces;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 3/23/2017
 */
public class TestInterface {

    public static void main(String[] args) {
        ActionResultInterface actionResultInterface = new AsyncTaskTool();

        System.out.println(actionResultInterface.getClass());
        System.out.println(actionResultInterface.getClass().getCanonicalName());
        System.out.println(actionResultInterface.getClass().getName());
        System.out.println(actionResultInterface.getClass().getName().equals(AsyncTaskTool.class.getCanonicalName()));
        System.out.println(actionResultInterface.getClass().getCanonicalName().equals(AsyncTaskTool.class.getCanonicalName()));


        ActionResultInterface actionResultInterface2 = new ActionResult();
        System.out.println(actionResultInterface.getClass().getName().equals("ActionResult"));



    }






}
