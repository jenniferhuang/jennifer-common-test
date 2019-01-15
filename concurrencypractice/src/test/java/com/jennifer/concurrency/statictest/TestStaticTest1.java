package com.jennifer.concurrency.statictest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jennifer.huang on 10/31/18.
 */
public class TestStaticTest1 {


    @org.testng.annotations.Test
    public void test() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task());
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        executor.shutdown();

    }


    class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("111");
            try {
                StaticThreadLocalParallel.getPageObject("meetingViewPage");
                System.out.println(StaticThreadLocalParallel.getPageObject("meetingViewPage"));
            }catch (Exception e){
                System.out.println(e);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
