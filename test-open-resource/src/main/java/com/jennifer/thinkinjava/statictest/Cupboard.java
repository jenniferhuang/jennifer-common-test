package com.jennifer.thinkinjava.statictest;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 5/22/2017
 */
public class Cupboard {
    Bowl b3 = new Bowl(3);
    Bowl b33 = new Bowl(333);

    static Bowl b4 = new Bowl(4);

    Cupboard() {
        System.out.println("Cupboard()");
        b4.f(2);
    }

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }

    static Bowl b5 = new Bowl(5);

    static{
        new Bowl(555);
    }
}
