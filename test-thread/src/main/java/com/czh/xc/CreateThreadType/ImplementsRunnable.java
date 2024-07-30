package com.czh.xc.CreateThreadType;

/**
 * Author:CZH
 * Date:2024-05-23
 * Description:实现Runnable
 */
public class ImplementsRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread:"+Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ImplementsRunnable());
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread:"+Thread.currentThread().getId());
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> System.out.println("Thread:" + Thread.currentThread().getId()));
        thread2.start();
    }
}
