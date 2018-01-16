package com.jennnifer.cucumber.steps.multistepdata;

import com.jennnifer.cucumber.domain.AccountDetail;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/15/2017
 */
public class MultiStepData {


    @When("^I check account infos as the following:$")
    public void checkAccounts(List<AccountDetail> accountDetails) {
        for(AccountDetail accountDetail: accountDetails){
            System.out.println("There is account: " +accountDetail.account_type +" "+accountDetail.rc_username+" "+accountDetail.rc_password);
        }

    }


    @Then("^we have the following account:\"([^\"]*)\"$") //automatically generate step definition
    public void weHaveTheFollowingAccount(List<String> accounts) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        for(String temp: accounts){
            System.out.println("logined accounts: "+temp);
        }

    }

    @Given("^I login as a user with accountType:\"([^\"]*)\" and username:\"([^\"]*)\" and password:\"([^\"]*)\"$")  //automatically generate step definition
    public void iLoginAsAUserWithAccountTypeAndUsernameAndPassword(String arg0, String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        System.out.println("logined with:  " +arg0 +" "+arg1+" "+arg2);

    }

    @Given("^a blog post named \"([^\"]*)\" with Markdown body$")
    public void aBlogPostNamedWithMarkdownBody(String arg0,String arg1) throws Throwable {
        System.out.println("a blog post, named:"+arg0+" and body: \n"+arg1);
    }

}
