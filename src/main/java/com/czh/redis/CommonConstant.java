package com.czh.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommonConstant {

    @Autowired
    StringRedisTemplate redisTemplate;

    void test(){
        redisTemplate.opsForHash().size("112");
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("key", "value", 60L, TimeUnit.SECONDS);
        String test = "hello";
        System.out.println(test);
        redisTemplate.opsForValue().get("");




    }

}
