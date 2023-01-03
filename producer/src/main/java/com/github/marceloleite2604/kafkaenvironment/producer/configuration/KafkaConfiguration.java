package com.github.marceloleite2604.kafkaenvironment.producer.configuration;

import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

  @UtilityClass
  public static class Topic {
    public static final String USERS = "users";
  }
}
