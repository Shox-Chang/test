package com.czh.xc.CreateThreadType;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
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
        ExecutorService executorService = new ThreadPoolExecutor(1,1,
                0L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.execute(new ThreadPool());
        /**
         * 如何优雅的停止线程池
         */
        //停止线程池，不接受新任务，等旧任务执行完毕
        executorService.shutdown();
        try {
            //超过10秒之后，判断是否执行剩下的任务，全部执行完毕，则返回true，没有则返false
            boolean awaitTermination = executorService.awaitTermination(10L, TimeUnit.SECONDS);
            if(!awaitTermination){
                //线程池中任务没有执行完毕，返回未执行的任务
                List<Runnable> tasks = executorService.shutdownNow();
                System.out.println(tasks.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /**
         * Spring提供的线程池ThreadPoolTaskExecutor
         */
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setRejectedExecutionHandler( new ThreadPoolExecutor.AbortPolicy());
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(20);
        executor.setThreadNamePrefix("czh--");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
    }
}
