package com.czh.xc;

public class RunnableTask implements Runnable{
    @Override
    public void run() {
        System.out.println("当前线程ID"+Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
