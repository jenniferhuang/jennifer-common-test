package com.jennifer.switchclean.withsubclass;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public class Salesman extends Employee {
    @Override
    int getType() {
        return Employee.SALESMAN;
    }

    @Override
    void getSalary() {
        System.out.println("manager salary");
    }
}
