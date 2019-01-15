package com.jennifer.switchclean.withsubclass;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public class Manager extends Employee {
    @Override
    int getType() {
        return Employee.MANAGER;
    }

    @Override
    void getSalary() {
        System.out.println("manager salary");
    }
}
