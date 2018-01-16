package com.jennifer.testlifecycle;

import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 3/16/2017
 */
public class ObjectStorageTest {

    public void test(ObjectStorage objectStorage){
        ObjectStorage objectStorage1 = new ObjectStorage();
        objectStorage1.setS("new in method Object");
        System.out.println("Pass from main: "+ objectStorage.toString());
        System.out.println("new in method: "+ objectStorage1.toString());
        System.out.println("objectStorage.getS(): " +objectStorage.getS());
        System.out.println("objectStorage1.getS(): "+objectStorage1.getS());



        objectStorage1 = objectStorage;
        System.out.println("After objectStorage1 = objectStorage: "+ objectStorage1.toString());
        System.out.println("After be =, objectStorage1.getS: "+objectStorage1.getS());
    }


    public static void main(String[] args) {
        ObjectStorageTest objectStorageTest = new ObjectStorageTest();
        ObjectStorage objectStorage = new ObjectStorage();
        System.out.println("in main: "+ objectStorage.toString());
        objectStorage.setS("main object");
        objectStorageTest.test(objectStorage);

    }



    @Test
    public void testPassParameter(){
        ObjectStorage objectStorage = new ObjectStorage();
        objectStorage.setS("111");
        System.out.println(objectStorage.s);
        System.out.println(objectStorage.s2);
        testChangeObject(objectStorage);

        System.out.println(objectStorage.s);
        System.out.println(objectStorage.s2);
    }


    public void testChangeObject(ObjectStorage objectStorage){
        objectStorage.setS("22222222");
        objectStorage.setS2("TEST2222");
    }

}
