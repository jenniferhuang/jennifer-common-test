package com.jennifer.switchclean.withsubclass;

import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public class EndCallTest {

    @Test
    public void test(){
        Employee.create(Employee.ENGINEER).getSalary();
    }
}
