package com.jennifer.testenum;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 11/3/2017
 */
public class TestEmeuCall {


    public static void main(String[] args) {
        for(EntryPathEnums temp:EntryPathEnums.values()) {
            System.out.println("key: "+temp.name()+" value: " +temp.getPathValue());
//            System.out.println(temp.getDisplayName());
        }

        TestEmeuCall testEmeuCall = new TestEmeuCall();
        testEmeuCall.open(EntryPathEnums.AdvancedIVR);

        System.out.println(EntryPathEnums.AdvancedIVR.getPathValue());
    }

    public void open(EntryPathEnums entryPathEnum){
        System.out.println("The specific value: "+ entryPathEnum.getPathValue());


    }
}
