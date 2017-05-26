package com.jennifer.test;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 5/8/2017
 */
public class TestString {

    public static void main(String[] args) {
//        String s = "01:01";
        String s = "01:10";
        String[] ss = s.split(":");
        System.out.println(ss[0]);
        System.out.println(ss[1]);

        long timeS = Long.parseLong(ss[0])*60+Long.parseLong(ss[1]);
        System.out.println(timeS);

        long sss=24934;
        System.out.println(sss/1000);
    }
}
