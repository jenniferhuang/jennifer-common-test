package com.jennifer.javaproperties;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 8/16/2017
 */
public class TestReadProperty {

    public static void main(String[] args) {
        //VM arguments
        System.out.println("java home is: " + System.getProperty("java.home")); //JVM defined
        System.out.println("self defined property, browsertype: " + System.getProperty("browsertype")); //self defined,  -Dbrowsertype

        //Program arguments
        System.out.println("Program arguments: "+args[0]);
        System.out.println("Program arguments: "+args[1]);
    }


    /**
     * System.getProperty() gets a property as defined by the JVM
     * (either the JVM itself or any -D options you may have passed at the command line).
     *
     * RUN:
     * //java -Dbrowsertype=jennifer com.jennifer.java.properties.TestReadProperty argvalue0=1 argvalue1=2
     */

    //http://note.youdao.com/noteshare?id=295c2877d277d145a57f0cc2e361efa4

//    https://stackoverflow.com/questions/17151547/what-is-the-difference-between-system-getproperty-and-properties-getproperty-in
}
