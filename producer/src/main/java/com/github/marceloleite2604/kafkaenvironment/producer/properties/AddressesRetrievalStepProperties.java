package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.Steps.ADDRESSES_RETRIEVAL)
public class AddressesRetrievalStepProperties extends ItemsRetrievalStepProperties {

  public AddressesRetrievalStepProperties(
      int totalItems,
      int chunkSize,
      int bufferedItems,
      float failureChanceRate) {
    super(totalItems, chunkSize, bufferedItems, failureChanceRate);
  }
}
