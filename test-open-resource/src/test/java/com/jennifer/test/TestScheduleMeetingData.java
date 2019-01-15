package com.jennifer.test;

import com.google.gson.Gson;
import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 11/5/18.
 */
public class TestScheduleMeetingData {

    @Test
    public void testInstance() {
        ScheduleMeetingData scheduleMeetingData = new ScheduleMeetingData();
        System.out.println(new Gson().toJson(scheduleMeetingData));
        Boolean isa = scheduleMeetingData.isAllowJoinBeforeHost();
        System.out.println(isa);
    }


}
