package com.jennifer.aoptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by com.jennifer.huang on 2/27/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring/spring-aspectj.xml")
public class TestSayHelloService {
    @Autowired
    SayHelloService sayHelloService;

    public void aspectJTest(){
        sayHelloService.say();
    }






}
