package com.jennifer;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 6/4/2017
 */
public class GuardClasses {
    /**
     * 密码有效性：
     * 密码不为空；
     * 密码要含有数字；
     * 密码要含有字母；
     * 密码长度要超过12位；
     */

    public boolean isPasswordValid(String password){
        boolean b;
        if(null==password){
            b=false;
        }else if(!hasDigital(password)){
            b=false;
        }else if(!hasLetter(password)){
            b=false;
        }else if(password.length()<12){
            b=false;
        }else{
            b=true;
        }
        return b;
    }

    private Boolean hasLetter(String password) {
        //to do
        return null;
    }

    private Boolean hasDigital(String password) {
        //to do
        return null;
    }




    public boolean isPasswordValid_refactor(String password){
        if(null==password) return false;
        if(!hasDigital(password)) return false;
        if(!hasLetter(password)) return false;
        if(password.length()<12) return false;
        return true;
    }


    /**
     * 说明：
     * 条件表达式提供的答案只有一种是正常行为，其他都不是正常的情况。
     *
     * 能写直接判断， 就不要写if...else， 特别是多层嵌套， 更是不易读懂。
     *
     */





}
