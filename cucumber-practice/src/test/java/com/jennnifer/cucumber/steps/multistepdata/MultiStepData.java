package com.jennnifer.cucumber.steps.multistepdata;

import com.jennnifer.cucumber.domain.AccountDetail;
import com.ringcentral.definitions.MeetingInfo;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 8/15/2017
 */
public class MultiStepData {


    @When("^I check account infos as the following:$")  //datable with table header, define the headers to a class.
    public void checkAccounts(List<AccountDetail> accountDetails) {   //1.   List -> datatable.   add ":"
        for (AccountDetail accountDetail : accountDetails) {
            System.out.println("There is account: " + accountDetail.account_type + " " + accountDetail.rc_username + " " + accountDetail.rc_password);
        }


    }

    @When("^I check account infos as the following with Datatable:$")
    //datable with table header, define the headers to a class.
    public void checkAccounts2(DataTable dataTable) {   //1.   List -> datatable.   add ":"

        AccountDetail accountDetail = new AccountDetail();
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        for (Map map : list) {
            if (map.containsKey("meetingType")) {
                accountDetail.account_type = (String) map.get("meetingType");
            }
            System.out.println(accountDetail.account_type);
        }


    }

    @When("^I check account infos as the following with Class:$")
    //datable with table header, define the headers to a class.
    public void withClass(List<MeetingInfo> meetinginfos) {   //1.   List -> datatable.   add ":"
        for (MeetingInfo meetinginfo : meetinginfos) {
            System.out.println(meetinginfo.allowJoinBeforeHost);

        }


    }


    @Then("^we have the following accounts?:\"([^\"]*)\"$") //automatically generate step definition
    public void weHaveTheFollowingAccount(List<String> accounts) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        for (String temp : accounts) {
            System.out.println("logined accounts: " + temp);
        }

    }

    @Given("^I login as a user with accountType:\"([^\"]*)\" and username:\"([^\"]*)\" and password:\"([^\"]*)\"$")
    //automatically generate step definition
    public void iLoginAsAUserWithAccountTypeAndUsernameAndPassword(String arg0, String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        System.out.println("logined with:  " + arg0 + " " + arg1 + " " + arg2);

    }

    @Given("^a blog post named \"([^\"]*)\" with Markdown body$")
    public void aBlogPostNamedWithMarkdownBody(String arg0, String arg1) throws Throwable {
        System.out.println("a blog post, named:" + arg0 + " and body: \n" + arg1);
    }

    @Given("^the price list for a coffee shop$") //datable with no table header.
    public void thePriceListForACoffeeShop(Map<String, Integer> priceList) throws Throwable {  //2.  map-> datatable
        System.out.println("items count:" + priceList.size());
        for (String key : priceList.keySet()) {
            System.out.println(key + " : " + priceList.get(key));
        }
    }

    //http://www.thinkcode.se/blog/2014/06/30/cucumber-data-tables
}
