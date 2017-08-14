package com.jennnifer.cucumber;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/14/2017
 */
public class HookCucumber {

    @Before
    public void testBefore(Scenario scenario){
        System.out.println("Before");
        System.out.println(scenario.getName());
    }



    @After
    public void testAfter(){
        System.out.println("After");
    }

}
