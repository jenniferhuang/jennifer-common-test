package com.jennifer.concurrency.statictest;

import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jennifer.huang on 10/31/18.
 */
public class StaticThreadLocalParallel {

    private static ThreadLocal<Map<String, String>> instance = ThreadLocal.withInitial(ConcurrentHashMap::new);


    public static String getPageObject(String key) {
        System.out.println("2222");
        if (!instance.get().containsKey(key)) {
            instance.get().put(key, "value:" + Thread.currentThread().getName());
        }
        return instance.get().get(key);
    }

}
