package com.jennifer.test;

import javafx.util.Pair;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    @Test
    public void testregular(){
        String s1 = "test loginWith <accountType> and Key AccountType=\"<accoutType>\"afdaf";
        String s2 = "test loginWithKey - For RC AccountType=\"normalAccount1\"";
        String s3 = "test loginWithKey - For RC accounttype=\"rcBetaUserAccount1\" test3";
        String s4 = "test loginWithKey - For RC accountType=\"rcBetaUserAccount2\"\"test4\"";
        String s5 = "test loginWithKey - For RC AccountType =\"rcBetaUserAccount3\"\"test4\"";
        String s6 = "test loginWithKey - For RC AccountType = \"rcBetaUserAccount4\"\"test4\"";
        String s7 = "test loginWithKey - For RC AccountType= \"rcBetaUserAccount5\"\"test4\"";
        List<String> testStrings = new ArrayList();
        testStrings.add(s1);
        testStrings.add(s2);
        testStrings.add(s3);
        testStrings.add(s4);
        testStrings.add(s5);
        testStrings.add(s6);
        testStrings.add(s7);



        String regEx11 = "(AccountType|accountType|accounttype)\\s*=\\s*\"([^\"]*)\"";

        String regEx = "loginWithKey";


        Pattern pattern = Pattern.compile(regEx11);

        for(int i=0;i<testStrings.size();i++){
            Matcher m = pattern.matcher(testStrings.get(i));
            while(m.find()){
                String s = m.group(2);
                System.out.println(s);

//                Matcher m2 = Pattern.compile("\"([^\"]*)\"").matcher(s);
//                if(m2.find()){
//                    String result = m2.group();
//                    System.out.println(result.replaceAll("\"",""));
//                }

            }
        }






    }
}
