package com.jennnifer.cucumber.pages.pages2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 9/12/2017
 */
public class LoginPage extends IPage{



    @FindBy(name = "username")
    WebElement email;
    @FindBy(name = "password")
    WebElement password;

    @FindBy(css = ".loginbtn")
    WebElement login;


    public LoginPage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void openLoginPage(){
        webDriver.get("http://www.phptravels.net/login");  //user@phptravels.com  demouser

    }

    public void setEmail(String emailInput){
        email.sendKeys(emailInput);
    }

    public void setPassword(String passwordInput){
        password.sendKeys(passwordInput);
    }

    public void login(){
        login.click();
    }
}
