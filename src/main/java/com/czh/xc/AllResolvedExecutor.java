package com.czh.xc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 统一管理项目的线程池
 */
@Configuration
@EnableAsync
public class AllResolvedExecutor implements AsyncConfigurer {

    public static final String mainExecutorName = "CZH-MAIN-EXECUTOR";
    public static final String singleExecutorName = "CZH-SINGLE-EXECUTOR";

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    //主线程池，任务级别高，拒绝策略为线程池满了则继续调用
    @Bean(mainExecutorName)
    @Primary
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setThreadNamePrefix("CZH---");
        executor.setQueueCapacity(200);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    //次线程池，任务级较低，拒绝策略为线程池满了则报错
    @Bean(singleExecutorName)
    public ThreadPoolTaskExecutor singleExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setThreadNamePrefix("CZH---");
        executor.setQueueCapacity(200);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }
}
