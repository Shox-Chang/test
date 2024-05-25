package com.czh.xc.CreateThreadType;

import java.util.concurrent.*;

/**
 * Author:CZH
 * Date:2024-05-23
 * Description:线程池来创建线程
 */
public class ThreadPool implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread:"+Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ThreadPool());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1, 0L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
    }
}
