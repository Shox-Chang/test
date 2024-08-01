package com.czh.xc.ReentrantLockTest;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void lockExample() throws InterruptedException {
        lock.lock();
        try {
            // Critical section
            System.out.println("lockExample acquired and executing critical section");
            Thread.sleep(3000);
        } finally {
            lock.unlock();
        }
    }

    public void tryLockExample() throws InterruptedException {
        if (lock.tryLock()) {
            try {
                // Critical section
                System.out.println("tryLockExample acquired and executing critical section");
                Thread.sleep(3000);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Could not acquire lock, performing alternative actions");
            Thread.sleep(3000);
        }
    }

    public void tryLockWithTimeoutExample() throws InterruptedException {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    // Critical section
                    System.out.println("tryLockWithTimeoutExample acquired and executing critical section");
                    Thread.sleep(3000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not acquire lock within timeout, performing alternative actions");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while waiting for lock");
            Thread.sleep(3000);
        }
    }

//    public void lockInterruptiblyExample() {
//        try {
//            lock.lockInterruptibly();
//            try {
//                // Critical section
//                System.out.println("Lock acquired and executing critical section");
//            } finally {
//                lock.unlock();
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Thread interrupted while waiting for lock");
//        }
//    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        new Thread(() ->{
            try {
                example.lockExample();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() ->{
            try {
                example.lockExample();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() ->{
            try {
                example.tryLockExample();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() ->{
            try {
                example.tryLockWithTimeoutExample();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
//        example.lockInterruptiblyExample();
    }
}
