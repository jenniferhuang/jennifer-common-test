package com.jennnifer.cucumber.steps.regulartest;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/4/2017
 */
public class RegularTestSteps {


//    @When("^I login as a user, accountType: (.*), number: (.*), password: (.*)$")
    @When("^I login as a user, accountType: (.*), number: (.*), password: (.*) data$")
    public void login(String accountType, String number, String password) {
        System.out.println("Login with:" +accountType +" "+number+" "+password);

    }
//
//    @When("^there is (\\d+) pieces? of the message$")
//    public void verifyQuantity(int count) {  //When you put part of a regular expression in parentheses, captured strings become step definition parameters
//        System.out.println(count);
//    }


    @When("^there (?:is|are) (\\d+) pieces? of the message$")
    public void verifyQuantity2(int count) {
        System.out.println();
        System.out.println(count);
    }


    /**
     * $用法
     *
     *
     */
    @Then("^I select $BrandName for Desktop$" )
     public void  parameterInExpression() {
        System.out.println("I select");
    }

    @Then("^I have \\$(\\d+) in my account$")   //\\$match $ itself
    public void parameter$InExpression(int money) {
        System.out.println("I have $ "+money +" in my account");
    }














    /**
     * Sometimes, you have to use parentheses to get a regular expression to work, but you don’t want to capture the match.
     *
     * http://agileforall.com/just-enough-regular-expressions-for-cucumber/
     *
     * Search keyword: cucumber parentheses need to as parameter
     *
     * It's import to descibe you problem in english  and search the google[english website]
     *
     */



    /**
     *  *
     * <> parameters in feature;
     * () capture match the parameter in the step.
     */


}
