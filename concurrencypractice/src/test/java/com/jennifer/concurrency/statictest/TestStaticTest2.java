package com.jennifer.concurrency.statictest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by jennifer.huang on 1/5/19.
 */
public class TestStaticTest2 {

    @org.testng.annotations.Test
    public void test() throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        List<Task> tasks = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            tasks.add(new Task());
        }
        List<Future<String>> futureList = cachedThreadPool.invokeAll(tasks);
        futureList.forEach(temp -> {
            try {
                temp.get(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        });


    }


    class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("111");
            String s = "";
            try {
                s = StaticThreadLocalParallel.getPageObject("meetingViewPage");
                System.out.println(StaticThreadLocalParallel.getPageObject("meetingViewPage"));
            } catch (Exception e) {
                System.out.println(e);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}
