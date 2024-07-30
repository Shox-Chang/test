package com.czh.xc.CreateThreadType;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        ExecutorService executorService = new ThreadPoolExecutor(1,1,
                0L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.execute(new ThreadPool());

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(20);
        executor.setThreadNamePrefix("czh--");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
    }
}
