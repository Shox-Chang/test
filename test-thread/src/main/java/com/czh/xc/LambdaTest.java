package com.czh.xc;

import com.czh.xc.CreateThreadType.ImplementsCallable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * Author:CZH
 * Date:2024-05-25
 * Description:测试lambda
 */
public class LambdaTest {

    // 静态内部类
    static class M4A4 implements Weapon{
        @Override
        public void shot(String name) {
            System.out.println("M4A4 shot-->"+name);
        }
    }

    public static void main(String[] args) {
        AK47 ak47 = new AK47();
        ak47.shot("ccc");

        M4A4 m4A4 = new M4A4();
        m4A4.shot("ddd");

        // 局部内部类
        class AWP implements Weapon{
            @Override
            public void shot(String name) {
                System.out.println("AWP shot-->"+name);
            }
        }
        AWP awp = new AWP();
        awp.shot("fff");

        // 匿名内部类
        Weapon weapon = new Weapon() {
            @Override
            public void shot(String name) {
                System.out.println("111"+name);
            }
        };
        weapon.shot("222");

        // lambda
        weapon = (String name) -> {
            System.out.println(name);
        };
        weapon.shot("com/czh");

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setThreadNamePrefix("czh666-");
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(200);
        threadPoolTaskExecutor.setKeepAliveSeconds(3000);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(1);
        threadPoolTaskExecutor.initialize();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdownNow();
        ImplementsCallable implementsCallable = new ImplementsCallable();
        Future<String> submit = threadPoolTaskExecutor.submit(implementsCallable);
        System.out.println(threadPoolTaskExecutor.getThreadNamePrefix());
        try {
            String s = submit.get();
            System.out.println("111--->"+s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolTaskExecutor.shutdown();
    }

}

interface Weapon{
    void shot(String name);
}

//实现类
class AK47 implements Weapon{

    @Override
    public void shot(String name) {
        System.out.println("AK47 shot-->"+name);
    }
}
