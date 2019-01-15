package com.jennifer.dataprovider;

import javafx.util.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by jennifer.huang on 6/14/18.
 */
public class MyTest3 {

    @DataProvider(name = "dp")
    public Object[][] createData(Method m) {
        System.out.println(m.getName());  // print test method name
        return new Object[][]{new Object[]{"Cedric"}};
    }


    @Test(dataProvider = "dp")
    public void test1(String s) {
        System.out.println(s);
    }

    @Test(dataProvider = "dp")
    public void test2(String s) {
        System.out.println(s);
        System.out.println("111:" + System.getProperty("xxx"));
    }


    @DataProvider(name = "dp2")
    public Object[][] createData2(Method m) {
        String sysParameter = "";
        if (m.getName().contains("test3")) {
            sysParameter = "rcBetaUserAccount";
        }
        int amounts = Integer.parseInt(System.getProperty(m.getName() + ".amounts"));
        return new Object[amounts][1];
    }

    @Test(dataProvider = "dp2")
    public void test3(String s) {
        System.out.println("test3" + s);
    }

    @Test(dataProvider = "dp2")
    public void test4(String s) {
        System.out.println("test2" + s);
        System.out.println("mvn clean test -Dtest=GenerateAccounts#rcBetaUserAccount+rcNonBetaUserAccount -Denvironment=glpci2xmn -DrcBetaUserAccount.amounts=1  -DrcNonBetaUserAccount.amounts=2");
        System.out.println("mvn clean test -Dtest=RCAccountDirectorTests#generateGlipUser -DaccountTypes=GlipBetaUser(1210,3281):1;GlipNonBetaUser(1210,3281):2;GlipBetaUser(1210,4488):1 -Denvironment=glpci2xmn ");

        System.out.println("mvn clean test -Dtest=GenerateAccounts#rcBetaUserAccount+rcNonBetaUserAccount,RCAccountDirectorTests#generateGlipUser -Denvironment=glpci2xmn -DrcBetaUserAccount.amounts=1  -DrcNonBetaUserAccount.amounts=2  -DaccountType=GlipBetaUser(1210,4488) -DGlipBetaUser(1210,4488).amounts=1");
    }

//    @DataProvider(name = "dp3")
//    public Object[][] createData3(Method m) {
//        String sysParameter="";
//        if(m.getName().contains("test3")){
//            sysParameter="rcBetaUserAccount";
//        }
//        int amounts = Integer.parseInt(System.getProperty(m.getName() + ".amounts"));
//        return new Object[amounts][1];
//    }


    @Test
    public void testStrings() {
        String accountTypeFromSystem = "GlipBetaUser(1210,3281):1;GlipNonBetaUser(1210,3281):2;GlipBetaUser(1210,4488):1";
        String[] temp = accountTypeFromSystem.split(";");
        List<Pair> accountTypesAndAmounts = Lists.newArrayList();
        for (String accountTypeAndAmounts : temp) {
            String accountType = accountTypeAndAmounts.split(":")[0];
            String amounts = accountTypeAndAmounts.split(":")[1];
            accountTypesAndAmounts.add(new Pair(accountType, amounts));
        }
        accountTypesAndAmounts.stream().forEach(my -> System.out.println(my.getKey() + " : " + my.getValue()));





    }

}
