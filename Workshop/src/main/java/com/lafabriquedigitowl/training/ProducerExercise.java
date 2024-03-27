package com.lafabriquedigitowl.training;

import com.lafabriquedigitowl.training.utils.WorkshopUtils;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;

public final class ProducerExercise {

  private static final Logger LOG = System.getLogger(ProducerExercise.class.getName());

  public static void main(String[] args) {
    //Set up the properties for the producer
    //TODO Put the topic name
    String TOPIC_NAME = "";

    Properties properties = new Properties();
    //TODO Add properties needed for the producer (bootstrap server, security, serializer...)
    // Hint: 9 properties are needed here ;) Check the AdminClientExercise

    // create a producer with String key and String value
    // KafkaProducer extends (by Producer) AutoCloseable, so we use it to auto close when the task is done
    try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
      // produce 10000 messages
      for (int i = 0; i < 10000; i++) {
        String key = WorkshopUtils.randomKey();
        String value = WorkshopUtils.randomValue();

        //TODO Create the instance of ProducerRecord with topic, key and value

        //TODO Send the ProducerRecord with the method send from KafkaProducer
        // Future<RecordMetadata> send(ProducerRecord<K, V> record)

        LOG.log(Level.INFO,
            "Message {0}: {1} has been sent to topic {2}", key, value, TOPIC_NAME);
      }
    }
  }
}