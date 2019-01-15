package com.jennifer.workflows;

import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 10/23/18.
 */
public class TestFlow {


    @org.testng.annotations.Test
    public void test() {
        WorkFlowFactory.getInstance().getWorkFlow(PathConstant.myProfilePath1).execute();
        WorkFlowFactory.getInstance().getWorkFlow(PathConstant.conversationPath1).execute();
    }
}
