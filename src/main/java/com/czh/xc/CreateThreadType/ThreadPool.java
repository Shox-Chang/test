package com.czh.xc.CreateThreadType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池来创建线程
 * Author:CZH
 * Date:2024-05-23
 * Description:TODO
 */
public class ThreadPool implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread:"+Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ThreadPool());
    }
}
