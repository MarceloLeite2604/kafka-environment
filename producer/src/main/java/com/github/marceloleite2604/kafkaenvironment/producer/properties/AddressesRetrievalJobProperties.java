package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.Steps.ADDRESSES_RETRIEVAL)
public class AddressesRetrievalJobProperties extends ItemsRetrievalStepProperties {

  public AddressesRetrievalJobProperties(
      int totalItems,
      int chunkSize,
      int bufferedItems,
      float failureChanceRate) {
    super(totalItems, chunkSize, bufferedItems, failureChanceRate);
  }
}
