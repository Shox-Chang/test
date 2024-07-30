package com.czh.xc.CallableTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableOne implements Callable<Integer> {

    private int count;

    public CallableOne(int count) {
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(count);
        count = atomicInteger.incrementAndGet();
        return count;
    }

    public static void main(String[] args) {
        Integer i = 1;
//        CallableOne callable01 = new CallableOne(i);
//        CallableOne callable02 = new CallableOne(i);
//        CallableOne callable03 = new CallableOne(i);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        List<Callable<Integer>> list = new ArrayList<>();
        for (int m = 0; m < 100; m++) {
            CallableOne callable01 = new CallableOne(i);
            list.add(callable01);
        }
//        list.add(callable01);
//        list.add(callable02);
//        list.add(callable03);
        try {
            List<Future<Integer>> futureTaskList = threadPoolExecutor.invokeAll(list);
            for (int j = 0; j < futureTaskList.size(); j++) {
                Future<Integer> future = futureTaskList.get(j);
                i = future.get();
                System.out.println(i);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdownNow();
        }
    }
}
