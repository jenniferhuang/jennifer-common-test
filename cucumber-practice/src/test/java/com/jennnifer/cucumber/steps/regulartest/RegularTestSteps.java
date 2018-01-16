package com.jennnifer.cucumber.steps.regulartest;

import com.google.common.base.Strings;
import com.jennifer.util.TestConst;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
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
        System.out.println("\nLogin with:" +accountType +" "+number+" "+password);

    }



//
//    @When("^there is (\\d+) pieces? of the message$")
//    public void verifyQuantity(int count) {  //When you put part of a regular expression in parentheses, captured strings become step definition parameters
//        System.out.println(count);
//    }

    //pieces?  match:  piece or pieces
    @When("^there (?:is|are) (\\d+) pieces? of the message$")
    public void verifyQuantity2(int count) {
        System.out.println("message amount: "+count);
    }



    @When("^sign in (?:with|without) parameter:?(.*)$")
    public void randomeParameter(String args) {
        if(!Strings.isNullOrEmpty(args)){
            System.out.println("with parameter"+args);
        }else{
            System.out.println("without parameter");
        }

    }




    /**
     * $用法
     *
     *
     */
    @Then("^I select $BrandName for Desktop$" )
     public void  parameterInExpression() {
        System.out.println("\nI select");
    }

    @Then("^I have \\$(\\d+) in my account$")   //  \\$match $ itself
    public void parameter$InExpression(int money) {
        System.out.println("\nI have $ "+money +" in my account");
    }








    @Given("^run the backround first before scenario$" )
    public void backgroundSteps(){
        System.out.println("\n=====background-steps.=======\n");
    }


    @When("^test sign in (?:with|without) parameter:?\"([^\"]*)\"$")
    public void randomeParameters(String args) {
        System.out.println("test parameter with double quotation marks\n");
        System.out.println(args);

    }


    @When("^\\[Entry Point\\] Sign In With User: \"([^\"]*)\" And Go To \"([^\"]*)\" (?:With|Without) Parameter:?\"([^\"]*)\"$")
public void testmulti(String a, String b, String c){

    }

    @When("^\\[Entry Point\\] Sign In With User: \"([^\"]*)\" And Go To \"([^\"]*)\"$")
    public void testmulti2(String a, String b){

    }

    @When("^\\[Entry Point\\] Sign In With User: \"([^\"]*)\" And Go To \"([^\"]*)\" (?:With|Without) Parameter$")
    public void testmulti3(String a, String b){

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
