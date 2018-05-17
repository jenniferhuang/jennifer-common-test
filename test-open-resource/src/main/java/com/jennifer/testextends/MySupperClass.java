package com.jennifer.testextends;

/**
 * Created by jennifer.huang on 5/11/18.
 */
public class MySupperClass {

    String firstName = "first_super";
    String lastName;
    long assigneeid;
    ThirdClass thirdClass = new ThirdClass();

    public void showValue() {
        System.out.println("firstName:" + firstName);
        System.out.println("lastName:" + lastName);
        System.out.println("assigneeid:" + assigneeid);
    }


}
