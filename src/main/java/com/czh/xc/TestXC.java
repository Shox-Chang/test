package com.czh.xc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestXC {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new RunnableTask());
        }
        executorService.shutdown();
    }



}
