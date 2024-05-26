package com.czh.xc;

/**
 * Author:CZH
 * Date:2024-05-26
 * Description:synchronized锁的机制
 */
public class SynchronizedTest {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Data data = new Data();
            new Thread(() -> {
                data.func();
            }).start();
        }
    }

}


class Data{
    public void func(){
        synchronized (this){
            System.out.println("Start...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End...");
        }
    }
}
