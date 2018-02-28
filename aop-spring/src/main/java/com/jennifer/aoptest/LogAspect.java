package com.jennifer.aoptest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component


/**
 * Created by jennifer.huang on 2/26/18.
 */
public class LogAspect {

    @After("execution(* com.jennifer.aoptest.SayHelloService.*(..))")
    public void log(){
        System.out.println("记录日志 ...");
    }
}
