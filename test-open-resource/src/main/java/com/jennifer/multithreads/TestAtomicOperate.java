package com.jennifer.multithreads;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jennifer.huang on 4/10/18.
 */
public class TestAtomicOperate {

    @Test
    public void testAddIfSave() throws InterruptedException, ExecutionException {
        ExecutorService cachedThreadPool1 = Executors.newCachedThreadPool();
        IntRunner intRunner = new IntRunner();
        List tasks1 = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            tasks1.add(intRunner);
        }
        List<Future<Integer>> futures = cachedThreadPool1.invokeAll(tasks1); //10000 threads invoke add at the same time.  [2. count...置位器材]
        for (int i = 0; i < 10000; i++) {
            futures.get(i).get();
        }
        System.out.println("result:" + intRunner.getI());





        ExecutorService cachedThreadPool2 = Executors.newCachedThreadPool();
        AtomicRunner atomicRunner = new AtomicRunner();
        List task2 = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            task2.add(atomicRunner);
        }
        List<Future<Integer>> futuresAtomic = cachedThreadPool2.invokeAll(task2); //10000 threads invoke add at the same time.
        for (int i = 0; i < 10000; i++) {
            futuresAtomic.get(i).get();
        }
        System.out.println("result:" + atomicRunner.getI());
        System.out.println("result:" + intRunner.getI());


    }


    class IntRunner implements Callable<Integer> {
        private int i = 0;

        public int add() {
            return i++;
        }

        public int getI() {
            return i;
        }

        @Override
        public Integer call() throws Exception {
            add();
            return i;
        }
    }


    class AtomicRunner implements Callable {
        private AtomicInteger i = new AtomicInteger(0);

        public int add() {
            return i.addAndGet(1);
        }

        public AtomicInteger getI() {
            return i;
        }

        @Override
        public AtomicInteger call() throws Exception {
            add();
            return i;
        }
    }
}
