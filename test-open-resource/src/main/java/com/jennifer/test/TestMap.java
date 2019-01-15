//package com.com.jennifer.test;
//import java.util.IdentityHashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Created by IntelliJ IDEA.
// * User: com.jennifer.huang
// * Date: 3/20/2017
// */
//public class TestMap {
//
//
//    public static void main(String[] args) {
//        String accountId1 = "123456";
//        String accountId2 = "1234567";
//        String extensionId1 = "123456_1";
//        String extensionId2 = "123456_2";
//
//        Map identityMap = new IdentityHashMap();
//        identityMap.put(accountId1, extensionId1);
//        identityMap.put(accountId1, extensionId2);
//
//        System.out.println(identityMap.get(accountId1));
//
//
//        System.out.println(extensionId1.substring(0, extensionId1.indexOf("_")));
//
//        ConcurrentHashMap<String, String> tokens = new ConcurrentHashMap<String, String>();
//        tokens.put(accountId1, extensionId1);
//        Iterator i = tokens.entrySet().iterator();
////        while(i.hasNext()){
////            String tokenKey = (String) i.next();
////            tokens.remove(tokenKey);
//////            if(tokenKey.substring(0,tokenKey.indexOf("_")).equals(accountId)){
//////                tokens.remove(tokenKey);
//////            }
////        }
//
//        for (Map.Entry<String, String> e : tokens.entrySet()) {
//            String tokenKey = e.getKey();
//            tokens.remove(tokenKey);
//        }
//
//        System.out.println(tokens.size());
//
//
//    }
//}
