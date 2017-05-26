package com.jennifer.thinkinjava.nullstaticinit;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 5/22/2017
 */
public class Mugs {
    Mug c1;
    Mug c2;

    {
        c1 = new Mug(1);
        c2 = new Mug(2);
        System.out.println("c1 & c2 initialized");
    }

    Mugs() {
        System.out.println("Mugs()");
    }

    public static void main(String[] args) {
        System.out.println("Inside main()");
        Mugs x = new Mugs();
    }
}
