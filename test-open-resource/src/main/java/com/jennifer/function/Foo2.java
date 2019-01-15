package com.jennifer.function;

import java.util.function.Function;

/**
 * Created by jennifer.huang on 1/10/19.
 */
public class Foo2 {
    private Foo2(String parameter) {
        System.out.println("I'm a Foo " + parameter);
    }

    public static Foo2 method(final String parameter) {
        return new Foo2(parameter);
    }

    private static Function parametrisedMethod(Function<String, Foo2> function) {
        return function;
    }

    public static void main(String[] args) {

        parametrisedMethod(Foo2::method).apply("from a method");
    }
}
