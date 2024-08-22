package com.rtm.application.protocol.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerExample {

    private  static Properties properties;

    static {
        properties = kafkaProperties();
        producer = new KafkaProducer<>(properties);
    }

    private static Producer<String, String> producer;

    public static Properties kafkaProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.149.82:9094"); // Kafka服务器地址
        props.put("acks", "all"); // 生产者等待所有副本确认
        props.put("retries", 0); // 生产者重试次数
        props.put("batch.size", 16384); // 每批次发送消息大小
        props.put("linger.ms", 1); // 消息延迟时间
        props.put("buffer.memory", 33554432); // 缓存大小
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }




    public static void main(String[] args) throws ExecutionException, InterruptedException {

        sendMessage("rtm-test", "userTest", "admin-rtm");

//        ProducerRecord<String, String> record = new ProducerRecord<>("rtm-test", "userTest", "admin-rtm");
//        RecordMetadata recordMetadata = producer.send(record).get();
//        System.out.println(recordMetadata);

    }

    public static void sendMessage(String topic, String key, String value) {
        producer.send(new ProducerRecord<>(topic, key, value));
    }
}
