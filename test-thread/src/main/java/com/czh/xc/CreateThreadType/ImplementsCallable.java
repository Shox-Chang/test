package com.czh.xc.CreateThreadType;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Author:CZH
 * Date:2024-05-23
 * Description:实现Callable
 */
public class ImplementsCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getId()+"";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new ImplementsCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        String result = futureTask.get();
        System.out.println(result);
    }
}
