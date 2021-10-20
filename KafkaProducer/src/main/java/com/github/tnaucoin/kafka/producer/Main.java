package com.github.tnaucoin.kafka.producer;

import com.github.tnaucoin.kafka.producer.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import static org.apache.kafka.common.utils.Utils.sleep;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        EventGenerator eventGenerator = new EventGenerator();

//        Properties props = new Properties();
//        props.put("bootstrap.servers","localhost:9093,localhost:9094");
//        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName());
        config.put("bootstrap.servers", "localhost:9092");
        config.put("acks", "all");
        config.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String, String>(config);

        for(int i = 0; i <= 10; i++){
            log.info("Generating Event #" + i);
            Event event = eventGenerator.generateEvent();
            String key = extractKey(event);
            String value = extractValue(event);

            ProducerRecord<String,String> producerRecord = new ProducerRecord<>("user-tracking",key,value);
            log.info("Sending Event to Kafka: " + key + ":" + value);
            producer.send(producerRecord);
            sleep(1000);
        }

        producer.close();
    }

    private static String extractKey(Event event) {
        return event.getUser().getUserId().toString();
    }

    private static String extractValue(Event event) {
        return String.format("%s, %s, %s",event.getProduct().getProductType(), event.getProduct().getColor(), event.getProduct().getDesignType());
    }
}
