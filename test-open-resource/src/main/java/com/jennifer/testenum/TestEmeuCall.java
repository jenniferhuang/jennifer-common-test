package com.jennifer.testenum;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jennifer.huang
 * Date: 11/3/2017
 */
public class TestEmeuCall {


    public static void main(String[] args) {
        for(EntryPathEnums temp:EntryPathEnums.values()) {
            System.out.println("key: "+temp.name()+" value: " +temp.getPathValue());
//            System.out.println(temp.getDisplayName());
        }

        TestEmeuCall testEmeuCall = new TestEmeuCall();
        testEmeuCall.open(EntryPathEnums.AdvancedIVR);

        System.out.println(EntryPathEnums.AdvancedIVR.getPathValue());
    }

    public void open(EntryPathEnums entryPathEnum){
        System.out.println("The specific value: "+ entryPathEnum.getPathValue());


    }


    public static enum GLIP_ENV {
        PRODUCTION,
        QA,
        XMN_LAB
    }

    @Test
    public void test(){
        System.out.println(GLIP_ENV.PRODUCTION);
        System.out.println(GLIP_ENV.PRODUCTION.name());
        System.out.println(GLIP_ENV.valueOf("PRODUCTION"));
        System.out.println(GLIP_ENV.values()[1]);
        for(GLIP_ENV glip_env:GLIP_ENV.values() ){
            if(glip_env.name().equalsIgnoreCase("xmn_lab")){
                System.out.println("xmn_lab");
            }
        }

    }
}
