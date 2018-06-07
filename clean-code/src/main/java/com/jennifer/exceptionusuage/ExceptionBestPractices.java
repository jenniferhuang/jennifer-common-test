package com.jennifer.exceptionusuage;


import java.io.IOException;



/**
 * Created by jennifer.huang on 5/28/18.
 */
public class ExceptionBestPractices {


    /**
     * demo1: provide a helper to getAccounts before tests.
     * need to throw exception to test?
     * tell the client(test), if account server is down. then you will run failed.
     */
    public static void getAccountsFromAccountPool() {
        try {
            //getAccountFrom Account Server
            throw new IOException("Failed to connect to /10.32.36.63:3303"); //just for demo

        } catch (IOException e) {
//            logger.error("Can not connect to account pool: " + accountPoolBaseUrl + ", Get account fail.");
            throw new RuntimeException(e.getMessage()); //unchecked exception.
        }
    }



    /**
     * demo2: throw the specific exception/custom exception for Client for better handling
     * when implementing custom exception, there are also some best practices for it.
     * https://dzone.com/articles/implementing-custom-exceptions-in-java
     *
     * business exception messages will extend in ErrorCode.java and throw to client.
     */

    public void someServiceMethod() throws BusinessException{

    }


    //other custom exception:
    //Selenium NoSuchElementException,



















    /**
     * demo3: catch exception
     * This method is intended to check if one element is present, so catch the specific exception
     * to tell the client(test) element is not present (when NoSuchElementException occurs.)
     */

//    @FindBy(how = How.XPATH, using =”//input[@name=’username’]”) WebElement userName;
//    public static boolean isElementPresent(WebElement element) {
//        try {
//            element.isDisplayed();
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }



    /**
     * demo4:
     */
//    public static void deactiveCompany(String companyName) throws ApiException {
//        try {
//            glipSandboxMonitorApiClient.deactiveCompany(companyName);
//        } catch (ApiException e) {
//            if (e.getMessage().contains("No company found for " + companyName)) {
//                logger.info("Company " + companyName + " not found. skipped deactive company for our test action.");
//            } else {
//                throw e;  //throw the exception back to the system
//            }
//        }
//    }


}
