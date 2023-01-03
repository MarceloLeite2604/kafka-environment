package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.Steps.USERS_RETRIEVAL)
public class UsersRetrievalJobProperties extends ItemsRetrievalStepProperties {
  protected UsersRetrievalJobProperties(
      int totalItems,
      int chunkSize,
      int bufferedItems,
      float failureChanceRate) {
    super(totalItems, chunkSize, bufferedItems, failureChanceRate);
  }
}
