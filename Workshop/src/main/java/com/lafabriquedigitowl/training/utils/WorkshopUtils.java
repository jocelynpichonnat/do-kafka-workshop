package com.lafabriquedigitowl.training.utils;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.DescribeConsumerGroupsResult;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.common.utils.Utils;

public class WorkshopUtils {

  private static final Logger LOG = System.getLogger(WorkshopUtils.class.getName());

  private static final int NUM_KEYS = 1000;
  private static final int NUM_VALUES = 1000;
  private static final String[] KEYS = new String[NUM_KEYS];
  private static final String[] VALUES = new String[NUM_VALUES];
  private static final Random RANDOM = new Random();

  static {
    for (int i = 0; i < NUM_KEYS; i++) {
      KEYS[i] = "key" + (i + 1);
    }
    for (int i = 0; i < NUM_VALUES; i++) {
      VALUES[i] = "value" + (i + 1);
    }
  }

  private WorkshopUtils() {
    //never be used
  }

  public static String randomKey() {
    int randomIndex = RANDOM.nextInt(NUM_KEYS);
    return KEYS[randomIndex];
  }

  public static int partitionFromMurMur2(byte[] serializedKey, int numPartitions) {
    return Utils.toPositive(Utils.murmur2(serializedKey)) % numPartitions;
  }

  public static String randomValue() {
    int randomIndex = RANDOM.nextInt(NUM_VALUES);
    return VALUES[randomIndex];
  }

  public static int extractPartitionNumber(String key, int numberOfPartitions) {
    String xValue = key.substring(3);
    try {
      return Integer.parseInt(xValue) % (numberOfPartitions - 1);
    } catch (NumberFormatException e) {
      return numberOfPartitions - 1;
    }
  }

  public static void displayOtherConsumerFromGroup(String groupId)
      throws ExecutionException, InterruptedException {
    Properties properties = CommonConfig.commonProperties();

    AdminClient adminClient = AdminClient.create(properties);

    ListConsumerGroupsResult consumerGroupsResult = adminClient.listConsumerGroups();
    Collection<ConsumerGroupListing> consumerGroupListings = consumerGroupsResult.all().get();

    for (ConsumerGroupListing consumerGroupListing : consumerGroupListings) {
      if (groupId.equals(consumerGroupListing.groupId())) {
        DescribeConsumerGroupsResult describeConsumerGroupsResult = adminClient.describeConsumerGroups(
            Collections.singletonList(groupId));
        ConsumerGroupDescription consumerGroupDescription = describeConsumerGroupsResult.all().get()
            .get(groupId);

        if (consumerGroupDescription.members().size() > 1) {
          LOG.log(Level.WARNING, "There are other active consumers in the group " + groupId);
        } else {
          LOG.log(Level.INFO, "Your consumer is the only active in the group " + groupId);
        }
      }
    }

    adminClient.close();
  }
}
