package com.jennnifer.cucumber.steps.multistepdata;

import com.jennnifer.cucumber.domain.AccountDetail;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 8/15/2017
 */
public class DatatableSteps {


//    @When("^I login as a user, accountType: (.*), number: (.*), password: (.*)$")
    @When("^I login as a user the following:$")
    public void login(List<AccountDetail> accountDetails) {
        for(AccountDetail accountDetail: accountDetails){
            System.out.println("\nLogin with:" +accountDetail.account_type +" "+accountDetail.rc_username+" "+accountDetail.rc_password);
        }

    }

}
