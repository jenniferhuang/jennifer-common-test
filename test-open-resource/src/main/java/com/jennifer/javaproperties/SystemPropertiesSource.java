package com.jennifer.javaproperties;

import org.testng.annotations.Test;

/**
 * Created by com.jennifer.huang on 1/14/18.
 */
public class SystemPropertiesSource {


    @Test
    public void test(){
        System.out.println(System.getProperty("user.dir"));  //get current project path

    }
}
