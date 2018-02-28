package com.jennifer.aoptest;
import org.springframework.stereotype.Component;
@Component
/**
 * Created by jennifer.huang on 2/26/18.
 */
public class SayHelloService {

    public void say(){
        System.out.print("Hello  AspectJ");
    }

}
