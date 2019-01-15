package com.jennifer.testinnerclass;

/**
 * Created by com.jennifer.huang on 1/29/18.
 */
public interface EventHandler<T> {
    void receive(T t);
}
