package com.jennnifer.cucumber.steps.regulartest;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.deps.difflib.DiffRow;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/14/2017
 */
public class HookCucumber {

    @Before
    public void testBefore(Scenario scenario) {  //Scenario
        System.out.println("\nBefore - Each scenario: " + scenario.getName());
    }


    @After
    public void testAfter() {
        System.out.println("\nAfter -- Each scenario");
    }




    @Before("@regulartest")
    public void testBeforeTagFeature(){
        System.out.println("\n Before tag -- @regulartest");
    }

    @Before("@Test2")
    public void testBeforeTag(){
        System.out.println("\n Before tag -- @Test2");
    }

    @After("@Test2")
    public void testAfterTag(){
        System.out.println("\n After tag---@Test2");
    }

}
