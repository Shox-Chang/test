package com.czh;

import cn.hutool.core.thread.NamedThreadFactory;
import com.czh.xc.AllResolvedExecutor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.*;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void testCollection() {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        Collection collection = new ArrayList();
        collection.add("iii");
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    void kafkaProducer(){
        // Kafka 生产者配置
        Properties props = new Properties();
        props.put("bootstrap.servers", "43.137.19.38:9092,43.137.19.38:9093,43.137.19.38:9094"); // Kafka 服务器地址
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建 Kafka 生产者
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 发送消息到 Kafka
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "111111111111111111111111111111111111111111");
        producer.send(record);

        // 关闭 Kafka 生产者
        producer.close();
    }

    @Qualifier(AllResolvedExecutor.mainExecutorName)
    @Autowired
    ThreadPoolTaskExecutor executor;

    @Test
    void testExecutor(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Boolean> future = executorService.submit(() -> {
            Thread.sleep(5000);
            System.out.println("111");
            return true;
        });
        System.out.println("还没有开始执行");
//        try {
//            future.get(2, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(200), new NamedThreadFactory("1111",false));
    }

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().setIfAbsent("czh","mm");
        String x = (String) redisTemplate.opsForValue().get("czh");
        System.out.println(x);

//        redisTemplate.opsForZSet().add("user", "czh", 10);
//        redisTemplate.opsForZSet().add("user", "mm", 5);
//        redisTemplate.opsForZSet().add("user", "dusk", 3);
//        redisTemplate.opsForZSet().add("user", "blank", 1);
        Set userSet = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("user", 1, 5, 2, 2);
        userSet.stream().forEach((Object user) -> {
            System.out.println(user.toString());
        });
    }

}
