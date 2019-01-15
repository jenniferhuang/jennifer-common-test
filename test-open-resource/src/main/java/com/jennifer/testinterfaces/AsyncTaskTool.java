package com.jennifer.testinterfaces;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 3/13/2017
 */
public class AsyncTaskTool implements ActionResultInterface {
    private static final long waitTime = Long.parseLong(System.getProperty("centralizedwebphone.time","1"));
    public final static String DONE = "done";
    private BlockingQueue<String> values;
    private String errorMessage = "";

    public AsyncTaskTool() {
        values = new LinkedBlockingQueue<String>();
    }


    public Boolean getResult(){  //set time out,   when socket colse????
        String result = null;
        try {
            result = values.poll(waitTime,TimeUnit.MINUTES);
            int i=1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(null!=result&&result.equals(DONE)){
            return true;
        }else{
            System.out.println("Action fail!!!!!!!!!");
            return false;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    protected Boolean offer(String s){
        return values.offer(s);
    }





}
