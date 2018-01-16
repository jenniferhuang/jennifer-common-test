package com.jennifer.test;

import javafx.util.Pair;
import org.testng.annotations.Test;

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



    @Test
    public void testParameters(){
        String locator1 = "div[@class = 'abc']";
        String locator2 = "div[@class = '%s']";
        String locator3 = "div[@class = '%s' and @id ='%s']";

        System.out.println(getProperties(locator1));
        System.out.println(getProperties(locator2, "abc"));
        System.out.println(getProperties(locator3, "abc", "def"));
    }


    private String getProperties(String s, String ...locatorReplaceValues){
        return String.format(s, locatorReplaceValues);
    }

    @Test
    public  void getKeyAndDidNumber(){
        String userkeyAndDid = "user701.did2";
        String[] ss = userkeyAndDid.split("\\.");
        Pair pair = new Pair(ss[0], ss[1].replaceAll("\\D",""));
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());

        String key1 = "abd";
        String key2 = "abd.did2";
        System.out.println(key1.contains("."));
        System.out.println(key2.contains("."));


    }
}
