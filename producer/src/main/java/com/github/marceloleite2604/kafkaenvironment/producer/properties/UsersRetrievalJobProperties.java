package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.Jobs.USERS_RETRIEVAL)
public class UsersRetrievalJobProperties extends ItemsRetrievalJobProperties {
  protected UsersRetrievalJobProperties(int totalItems, int chunkSize, int bufferedItems) {
    super(totalItems, chunkSize, bufferedItems);
  }
}
