package com.jennifer.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 10/25/2017
 */
public class TestConstRetriveFromJS {
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
