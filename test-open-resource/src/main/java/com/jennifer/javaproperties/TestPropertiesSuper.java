package com.jennifer.javaproperties;

import com.jennifer.javaproperties.android.TestPropertiesSameClass;
import org.testng.annotations.Test;

public class TestPropertiesSuper {

    @Test
   public void testAllProperties(){
       TestPropertiesClass testPropertiesClass = new TestPropertiesClass();
       testPropertiesClass.testGetProperty();
       System.out.println("=============");

       TestPropertiesClassSub testPropertiesClassSub = new TestPropertiesClassSub();
       testPropertiesClassSub.testGetProperty();
       System.out.println("=============");


       TestPropertiesClass2 testPropertiesClass2 = new TestPropertiesClass2();
       testPropertiesClass2.testGetProperty();
       System.out.println("=============");


       String s = "android";

       ITestPropertiesSameClass iTestPropertiesSameClass;
       if(s.equals("android")){
           iTestPropertiesSameClass = new TestPropertiesSameClass();
       }else {
           iTestPropertiesSameClass = new com.jennifer.javaproperties.ios.TestPropertiesSameClass();
       }
       iTestPropertiesSameClass.testGetProperty();


   }
}
