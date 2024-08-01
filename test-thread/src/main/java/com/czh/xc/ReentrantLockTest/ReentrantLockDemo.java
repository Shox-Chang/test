package com.czh.xc.ReentrantLockTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Author:CZH
 * Date:2024-05-26
 * Description:测试ReentrantLock
 */
public class ReentrantLockDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public void fun() {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        new Thread(() ->{
            reentrantLockDemo.fun();
        },"A").start();
        new Thread(() ->{
            reentrantLockDemo.fun();
        },"B").start();
    }


}
