package com.jennifer.classloader;

import com.ringcentral.qa.model.AccountMapper;
import com.ringcentral.qa.utils.JsonFileHelper;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jennifer.huang on 9/21/18.
 */
public class TestReadFileFromJar {


    @Test
    public void testReadFile() {
        AccountMapper accountMapper = new JsonFileHelper().readJsonFile("/json/kaminoAccount.json", AccountMapper.class);
        System.out.println(accountMapper.getBrand());
    }

    @Test
    public void test() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class jsonType= JsonFileHelper.class;
        Object invokertest = jsonType.newInstance();
        Method addMethod = jsonType.getMethod("readJsonFile", String.class, AccountMapper.class);
        AccountMapper accountMapper = (AccountMapper) addMethod.invoke(invokertest, "/json/kaminoAccount.json", Class.class);

    }
}
