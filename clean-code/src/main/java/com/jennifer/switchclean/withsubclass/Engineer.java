package com.jennifer.switchclean.withsubclass;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public class Engineer extends Employee {
    @Override
    void getSalary() {
        System.out.println("engineer salary");
    }

    @Override
    int getType() {
        return Employee.ENGINEER;
    }
}
