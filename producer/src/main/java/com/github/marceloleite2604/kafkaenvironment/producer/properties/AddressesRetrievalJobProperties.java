package com.github.marceloleite2604.kafkaenvironment.producer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(PropertiesPath.Jobs.ADDRESSES_RETRIEVAL)
public class AddressesRetrievalJobProperties extends ItemsRetrievalJobProperties {

  public AddressesRetrievalJobProperties(int totalItems, int chunkSize, int bufferedItems) {
    super(totalItems, chunkSize, bufferedItems);
  }
}
