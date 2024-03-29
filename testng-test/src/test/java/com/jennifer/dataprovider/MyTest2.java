package com.jennifer.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MyTest2 {
    private List<String> testData2 = new ArrayList<>();


    //This method will provide data to any test method that declares that its Data Provider
    //is named "test1"
    @DataProvider(name = "test1", parallel = true)
    public Object[][] createData1() {
        return new Object[][]{
                {"Cedric", new Integer(36)},
                {"Anne", new Integer(37)},
                {"test", new Integer(37)},
                {"Cedric1", new Integer(36)},
                {"Anne1", new Integer(37)},
                {"test1", new Integer(37)},
        };
    }

    //https://wiki.saucelabs.com/display/DOCS/Parallel+Testing+in+Java+with+Maven+and+TestNG#ParallelTestinginJavawithMavenandTestNG-Option1:UsingConfigurationPropertiesinthepom.xmlFile


    //This test method declares that its data should be supplied by the Data Provider
    //named "test1"
    @Test(dataProvider = "test1")
    public void verifyData1(String n1, Integer n2) throws InterruptedException {
        Long id = Thread.currentThread().getId();
        Thread.sleep(3000);
        System.out.println(n1 + " " + n2 + "   current thread:" + id);

    }


    @DataProvider(name = "test3")
    public Object[][] createData3() {
        return new Object[][]{
                {"Cedric"},
                {"Anne"},
                {"test"},
        };
    }

    @Test(dataProvider = "test3")
    public void verifyData3(String n1) {
        System.out.println("test3" + n1);

    }


    @DataProvider(name = "test2")
    public Object[][] createData2() {
        testData2.add("1111");
        testData2.add("1122");
        testData2.add("1133");

        Object[][] params = new Object[testData2.size()][1];
        for (int i = 0; i < testData2.size(); i++) {
            String[] temp = new String[]{testData2.get(i)};
            params[i] = temp;
        }
        return params;
    }

    @Test(dataProvider = "test2")
    public void verifyData2(String n1) {
        System.out.println("test2: " + n1);

    }


    @DataProvider(name = "testAmounts", parallel = true)
    public Object[][] createDataAmounts() {
        int amounts = Integer.parseInt(System.getProperty("amounts", "1"));
        return new Object[amounts][1];
    }

    @Test(dataProvider = "testAmounts")
    public void verifyData1222(String amounts) throws InterruptedException {
        Long id = Thread.currentThread().getId();
        Thread.sleep(3000);
        System.out.println("   current thread:" + id);

    }





    @DataProvider(name = "testScenario", parallel = true)
    public Object[][] scenarios() {
        int amounts = Integer.parseInt(System.getProperty("amounts", "1"));
        String accounttype = System.getProperty("accounttype");
        Object[][] params = new Object[amounts][1];
        for(int i =0; i<amounts;i++){
            params[i]=new String[]{accounttype};
        }
        return params;
    }


    @Test(dataProvider = "testScenario")
    public void testscenario(String accountType){
        System.out.println(accountType);
    }


}
