package com.czh.xc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:CZH
 * Date:2024-05-28
 * Description:TODO
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("test1", 1);
        concurrentHashMap.put("test2", 2);
        concurrentHashMap.put("test3", 3);

        System.out.println(concurrentHashMap.toString());

        concurrentHashMap.compute("test1", (key, value) -> value + 10);

        System.out.println(concurrentHashMap.toString());
    }

}
