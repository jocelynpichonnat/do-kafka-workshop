package com.lafabriquedigitowl.training;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import com.lafabriquedigitowl.training.utils.CommonConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public final class ConsumerExercise {

  public static void main(String[] args) {
    //Set up the properties for the consumer
    //TODO Put the topic name
    String TOPIC_NAME = "";

    Properties properties = new Properties();
    //TODO Add properties needed for the consumer (bootstrap server, security, deserializer, group.id...)
    // Hint: 11 properties are needed here ;) Check the AdminClientExercise
    // Hint: 2 additional properties can save you an headache :)

    properties = CommonConfig.commonProperties();

    // create a producer with String key and String value
    // KafkaConsumer extends (by Consumer) AutoCloseable, so we use it to auto close when the task is done
    try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
      consumer.subscribe(List.of(TOPIC_NAME));
      while (true) {
        ConsumerRecords<String, String> messages = consumer.poll(Duration.ofMillis(1000));
        //TODO Display the offset, the key and value of each record
      }
    }
  }
}