package com.czh.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerTest {
    public static void main(String[] args) {
//        Configurator.setRootLevel(Level.ERROR);
        // Kafka 服务器地址和端口号
        String bootstrapServers = "43.137.19.38:9092,43.137.19.38:9093,43.137.19.38:9094";
        // 消费者组 ID
        String groupId = "test";
        // 要订阅的主题
        String topic = "test";

        // 配置消费者属性
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // 创建 Kafka 消费者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));

        // 持续接收消息
        // 持续接收消息
        while (true) {
            // 从 Kafka 中拉取消息
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            // 如果没有消息，继续等待
            if (records.isEmpty()) {
                continue;
            }

            // 处理接收到的消息
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Received message: offset = %d, key = %s, value = %s%n",
                        record.offset(), record.key(), record.value());
            }
        }
    }
}
