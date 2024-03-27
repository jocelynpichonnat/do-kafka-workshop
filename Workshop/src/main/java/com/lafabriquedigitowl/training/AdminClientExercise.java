package com.lafabriquedigitowl.training;

import com.lafabriquedigitowl.training.utils.CommonConfig;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;

public class AdminClientExercise {

  private static final Logger LOG = System.getLogger(AdminClientExercise.class.getName());

  public static void main(String[] args) {
    // Basic configurations of the topic to create
    String topicName = "FirstTopic_"
        + "CHANGE_BY_YOUR_NAME"; //TODO add your firstname and your lastname (trying to be unique)
    Integer partition = 3;
    short replicationFactor = 3;

    // Connection information to the Kafka Cluster
    //TODO go to FirstExerciseConfig to fill the values
    Properties properties = CommonConfig.commonProperties();

    try (AdminClient adminClient = AdminClient.create(properties)) {
      try {
        createTopic(adminClient, topicName, partition, replicationFactor);
      } catch (ExecutionException | InterruptedException e) {
        LOG.log(Level.ERROR, e);
      }
    }
  }

  private static void createTopic(AdminClient adminClient, String topicName, Integer partitions,
      short replicationFactor)
      throws ExecutionException, InterruptedException {
    NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

    adminClient.createTopics(List.of(newTopic)).values().get(topicName).get();

    //We will retrieve the list of topic's name inside the cluster and try to see if ours is present
    if (retrieveNamesOfTopics(adminClient).contains(topicName)) {
      LOG.log(Level.INFO, "{0} is created", topicName);
      describeTopic(adminClient, topicName);
    } else {
      LOG.log(Level.ERROR, "{0} not found", topicName);
    }
  }

  private static List<String> retrieveNamesOfTopics(AdminClient adminClient)
      throws ExecutionException, InterruptedException {
    return new ArrayList<>(adminClient.listTopics().names().get());
  }

  private static void describeTopic(AdminClient adminClient, String topicName)
      throws ExecutionException, InterruptedException {
    DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(List.of(topicName));
    TopicDescription topicDescription = describeTopicsResult.topicNameValues().get(topicName).get();
    LOG.log(Level.INFO, topicDescription);
  }

}