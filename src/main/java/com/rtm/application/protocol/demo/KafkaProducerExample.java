package com.rtm.application.protocol.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerExample {

    public static void main(String[] args) {
        // 配置Kafka生产者属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.149.82:9094"); // Kafka服务器地址
        props.put("acks", "all"); // 生产者等待所有副本确认
        props.put("retries", 0); // 生产者重试次数
        props.put("batch.size", 16384); // 每批次发送消息大小
        props.put("linger.ms", 1); // 消息延迟时间
        props.put("buffer.memory", 33554432); // 缓存大小
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建Kafka生产者
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 1; i++) {
            String key = "key-" + i;
            String value = "value-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", key, value);

            try {
                // 发送消息并获取元数据
                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("Message sent to topic %s partition %d with offset %d\n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 关闭生产者
        producer.close();
    }
}
