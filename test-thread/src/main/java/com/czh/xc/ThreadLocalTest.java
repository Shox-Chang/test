package com.czh.xc;

import java.util.HashMap;

/**
 * Author:CZH
 * Date:2024-05-26
 * Description:ThreadLocal
 */
public class ThreadLocalTest {

    private static ThreadLocal<HashMap<String,?>> threadLocal = new ThreadLocal<>();

    private static volatile HashMap<String, String> hashMap = new HashMap<>();

    static void funA(){
        hashMap.put("1", "2");
        threadLocal.set(hashMap);
    }

    static String funB(){
        funA();
        return (String) threadLocal.get().get("1");
    }

    static void funC(){
        hashMap.remove("1");
    }

    public static void main(String[] args) {
        String str = funB();
        System.out.println(str);
        funC();
        System.out.println(threadLocal.get().get("1"));
    }

}
