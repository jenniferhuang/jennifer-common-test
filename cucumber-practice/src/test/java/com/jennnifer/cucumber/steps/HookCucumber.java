package com.jennnifer.cucumber.steps;

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
        System.out.println("\nBefore Each Scenario: \"" + scenario.getName() +"\"");
    }

    @After(order = 1)
    public void testAfter(Scenario scenario) {



        System.out.println("\nAfter Each scenario: \""+scenario.getName() +"\"");
    }


    /**
     * backgroup in one feature:  backgroup steps are for all each scenarios's steps
     * execute order:
     * @Before
     * Background: steps
     * Given, When, Then, And, But
     * @After
     *
     * Background understanding, common steps for all scenarios. just need to write once.
     *
     */




    //tag hook

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


    @After(value = "@resetAccount", order = 2)
    public void testAfterTagTest(){
        System.out.println("\n After tag---@resetAccount");
    }

}
