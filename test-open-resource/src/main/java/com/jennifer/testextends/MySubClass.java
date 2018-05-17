package com.jennifer.testextends;

import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 5/11/18.
 */
public class MySubClass extends MySupperClass {

    String assignName = "subAssignName";

    public void showValue() {
        System.out.println("firstName:" + firstName);
        System.out.println("lastName:" + lastName);
        System.out.println("assigneeid:" + assigneeid);
        System.out.println("assignName:" + assignName);
    }


    @Test

    public void testInstance() {
        MySubClass mySubClass = new MySubClass();
        mySubClass.assignName = "subAssignName111";
        mySubClass.lastName = "subLastName";
//        mySubClass.firstName="firstNameValueOverride";


        MySupperClass supperClass = new MySupperClass();
        supperClass.firstName = "11111";
        supperClass.thirdClass.testString = "11111111";


        supperClass = mySubClass;

        long assigneeid = 2;//change from the sub class.
        supperClass.assigneeid = assigneeid;
        supperClass.showValue();


    }
}
