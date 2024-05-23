package com.czh.xc.CreateThreadType;

/**
 * 继承Thread
 * Author:CZH
 * Date:2024-05-23
 * Description:TODO
 */
public class ExtendThread extends Thread{
    @Override
    public void run() {
        System.out.println("Thread:"+Thread.currentThread().getId());
    }

    // 缺点，java是单继承
    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();
    }
}
