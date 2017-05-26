package com.jennifer.testinterfaces;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 3/23/2017
 */
public class ActionResult implements ActionResultInterface {

    private Boolean result;
    private String errorMessage = "";



    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
