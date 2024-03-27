package com.lafabriquedigitowl.training;

import java.lang.System.Logger;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;

public class KafkaStreamsExercise {

  private static final Logger LOG = System.getLogger(KafkaStreamsExercise.class.getName());

  private static final String regex = "value\\d+";
  private static final Pattern pattern = Pattern.compile(regex);

  public static void main(String[] args) {
    String INPUT_TOPIC_NAME = ""; //TODO to define
    String OUTPUT_TOPIC_NAME = ""; //TODO to

    Properties properties = new Properties();
    //TODO Add properties needed for the kafka streams (bootstrap server, security, serdes, ...)

    StreamsBuilder streamsBuilder = new StreamsBuilder();

    KStream<String, String> kStream = streamsBuilder.stream(INPUT_TOPIC_NAME);

    //TODO Add the filter logic here

    final CountDownLatch latch = new CountDownLatch(1);

    try (KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), properties)) {
      streams.start();
      latch.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}