package com.github.marceloleite2604.kafkaenvironment.producer.configuration;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaConfiguration {

  @UtilityClass
  public static class Topic {
    public static final String USERS = "users";
  }
}
