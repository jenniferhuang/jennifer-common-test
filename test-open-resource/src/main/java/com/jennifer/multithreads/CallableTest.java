package com.jennifer.multithreads;


import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 3/10/2017
 */
public class CallableTest {
        public static void main(String[] args) {
            //第一种方式
            ExecutorService executor = Executors.newCachedThreadPool();
            ApiClientTask task = new ApiClientTask();
            FutureTask<Boolean> futureTask = new FutureTask<Boolean>(task); //Call API client
            executor.submit(futureTask);
            executor.shutdown();


            try {  //Continue to work
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            System.out.println("主线程在执行任务");

//
//            try {
//                System.out.println("task运行结果1"+futureTask.get(1000, TimeUnit.MILLISECONDS));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }

            try {
                System.out.println("task运行结果2: "+futureTask.get()); //wait until task done. and get a true return.
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println("所有任务执行完毕");
        }


}


//http://www.cnblogs.com/dolphin0520/p/3949310.html