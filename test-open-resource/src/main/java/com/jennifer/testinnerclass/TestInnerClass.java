package com.jennifer.testinnerclass;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.jennifer.huang on 1/29/18.
 *
 * 详细看得不够debug 里对象装载内容。
 *
 *
 *
 * 匿名类的类型表达式：  TestInnerClass$1
 * 匿名类的对象表达式：  TestInnerClass$1@1109
 *
 * 内部类的对象表达式：   HashMap$Node@1122
 *
 * 正常类的对象表达式： ArrayList@1119,   HashMap@1113
 *
 */
public class TestInnerClass {
    private Map<String, List<EventHandler>> handlers = new HashMap<String, List<EventHandler>>();


    private EventHandler<String> stepStartedHandler = new EventHandler<String>() {   //TestInnerClass$1   handlers 装进去的对象类型表达式。 匿名类new EventHandler<String>() {
        @Override
        public void receive(String event) {
            System.out.println("test11111"+event);
        }
    };

    private EventHandler<String> stepStartedHandler2 = new EventHandler<String>() {  ////TestInnerClass$2    handlers 装进去的对象类型表达式
        @Override
        public void receive(String event) {
            System.out.println("test2222"+event);
        }
    };

    class InnerClass{
        public String test1 = "test111";
        public String test2 = "test222";
    }


    private InnerClass class1 = new InnerClass();
    private InnerClass class2 = new InnerClass();


    @Test
    public void TestObject(){
        List<EventHandler> list = new ArrayList<EventHandler>();
        list.add(stepStartedHandler);
        list.add(stepStartedHandler2);
        handlers.put("stepsStartedHandler", list);
        System.out.println(handlers.get("stepsStartedHandler").size());



        Map<String, List<EventHandler>> handlers2 = new HashMap<String, List<EventHandler>>();
        TestInnerClass testInnerClass1 = new TestInnerClass();
        TestInnerClass testInnerClass2 = new TestInnerClass();
        List<EventHandler> list2 = new ArrayList<EventHandler>();
        list2.add(testInnerClass1.stepStartedHandler);
        list2.add(testInnerClass2.stepStartedHandler);


        handlers2.put("stepsStartedHandler2", list2);
    }
}
