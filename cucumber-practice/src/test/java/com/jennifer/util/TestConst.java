package com.jennifer.util;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 10/25/2017
 */
public class TestConst {

//    public static final String AutoReceptionist = "/company/index.html#receptionist";
//    public static final String Billing = "/settings/billing.html";
//    public static final String CompanyNumbers = "/company/index.html#numbers";
//    public static final String PhoneNumbers = "/company/index.html#/phoneNumbers";
//    public static final String CompanyInfo = "/company/index.html#/companyInfo/companyAddress";
//    public static final String Contacts = "/contacts/index.html";
//    public static final String Departments = "/company/index.html#/departments";
//    public static final String SharedLineGroups = "/company/index.html#/departments/sharedLines";
//    public static final String PagingGroups = "/company/index.html#/departments/pagingOnly";
//    public static final String GroupsOthers = "/company/index.html#/departments/others";
//    public static final String InternationalCalling = "/settings/billing.html#iCalling";
//    public static final String Overview = "/company/index.html";

    public static final Map entrysMap = new HashMap();


    static{
        entrysMap.put("AutoReceptionist","/company/index.html#receptionist");
        entrysMap.put("Billing","/settings/billing.html");
        entrysMap.put("CompanyNumbers","/company/index.html#numbers");
    }




//    public static final Field[] test(){
//        final Field[] fields =  TestConst.class.getDeclaredFields();
//        for(Field field: fields){
//            System.out.println(field.getName());
//            field.setAccessible(true);
//            try {
//                System.out.println(field.get(null));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return fields;
//    }
//
//    public static void main(String[] args) {
//        TestConst.test();
//    }
}
