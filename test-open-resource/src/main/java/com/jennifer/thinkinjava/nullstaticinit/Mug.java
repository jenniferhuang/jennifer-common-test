package com.jennifer.thinkinjava.nullstaticinit;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 5/22/2017
 */
public class Mug {
    Mug(int marker) {
        System.out.println("Mug(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}
