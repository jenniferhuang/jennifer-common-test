package com.jennifer.testextends;

/**
 * Created by com.jennifer.huang on 5/11/18.
 */
public class MySupperClass {

    private static Class<? extends MySupperClass> tClass;
    String firstName = "first_super";
    String lastName;
    long assigneeid;
    ThirdClass thirdClass = new ThirdClass();

    public void showValue() {
        System.out.println("firstName:" + firstName);
        System.out.println("lastName:" + lastName);
        System.out.println("assigneeid:" + assigneeid);
    }


    public void waitElement() {
        System.out.println(this.getClass().getName());
        this.waitElement(); //死循环
    }

    public static void waitElement2() {
        System.out.println("test222");
    }

    protected synchronized static <T extends MySupperClass> T getInstance(Class<? extends MySupperClass> tClass) throws IllegalAccessException, InstantiationException {

        waitElement2();

        return (T) tClass.newInstance();

    }

}
