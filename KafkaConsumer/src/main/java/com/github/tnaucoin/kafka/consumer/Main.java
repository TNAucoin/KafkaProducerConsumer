package com.github.tnaucoin.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Properties;

import static java.util.Arrays.asList;

@Slf4j
public class Main {
    public static void main(String[] args){
        SuggestionEngine suggestionEngine = new SuggestionEngine();

        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9093, localhost:9094");
        props.put("group.id","user-tracking-consumer");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // subscribe this consumer to the user-tracking topic
        consumer.subscribe(asList("user-tracking"));
        // keep polling for duration of running application.
        while(true){
            //poll kafka with consumer to fetch any messages
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));

            //iterate over messages
            for(ConsumerRecord<String,String> record : records){
                suggestionEngine.processSuggestion(record.key(),record.value());
            }
        }
    }
}
