package com.rtm.application.protocol.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {

    public static void main(String[] args) {
        // 配置Kafka消费者属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.149.82:9094"); // Kafka服务器地址
        props.put("group.id", "test-group"); // 消费者组ID
        props.put("enable.auto.commit", "true"); // 自动提交偏移量
        props.put("auto.commit.interval.ms", "1000"); // 自动提交偏移量间隔
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 创建Kafka消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅主题
        consumer.subscribe(Collections.singletonList("my-topic"));

        // 不断轮询获取消息
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received message: key = %s, value = %s, topic = %s, partition = %d, offset = %d\n",
                            record.key(), record.value(), record.topic(), record.partition(), record.offset());
                }
            }
        } finally {
            consumer.close();
        }
    }

}
