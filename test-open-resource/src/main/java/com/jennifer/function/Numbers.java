package com.jennifer.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by jennifer.huang on 1/10/19.
 */
public class Numbers {


    public static boolean isMoreThanFifty(int n1, int n2) {
        return (n1 + n2) > 50;
    }

    public static List<Integer> findNumbers(List<Integer> l, BiPredicate<Integer, Integer> p) {
        List<Integer> newList = new ArrayList<>();
        for (Integer i : l) {
            if (p.test(i, i + 10)) {
                newList.add(i);
            }
        }
        return newList;
    }


    public void test() {
        List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);

        // Using an anonymous class
        findNumbers(list, new BiPredicate<Integer, Integer>() {
            public boolean test(Integer i1, Integer i2) {
                return Numbers.isMoreThanFifty(i1, i2);
            }
        });

        // Using a lambda expression
        findNumbers(list, (i1, i2) -> Numbers.isMoreThanFifty(i1, i2));

        // Using a method reference
        findNumbers(list, Numbers::isMoreThanFifty);

    }


}
