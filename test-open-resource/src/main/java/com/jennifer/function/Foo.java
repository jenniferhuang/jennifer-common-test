package com.jennifer.function;

import java.util.function.Function;

/**
 * Created by jennifer.huang on 1/10/19.
 */
public class Foo {
    private Foo(String parameter) {
        System.out.println("I'm a Foo " + parameter);
    }

    public static Foo method(final String parameter) {
        return new Foo(parameter);
    }

    private static Function parametrisedMethod(Function<String, Foo> function) {
        return function;
    }

    public static void main(String[] args) {
        parametrisedMethod(Foo::method).apply("from a method");
    }
}
