package com.jennnifer.cucumber.pages.pages2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 9/12/2017
 */
public class WelcomePage extends IPage {

    @FindBy(css = "h3.RTL")
    WebElement welcomeLabel;

    public WelcomePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }


    public String getWelcomeLabel(){
        return welcomeLabel.getText();
    }




}
