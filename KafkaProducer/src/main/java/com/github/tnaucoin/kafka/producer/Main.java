package com.github.tnaucoin.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException{
        EventGenerator eventGenerator = new EventGenerator();

        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9093,localhost:9094");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String, String>(props);
    }
}
