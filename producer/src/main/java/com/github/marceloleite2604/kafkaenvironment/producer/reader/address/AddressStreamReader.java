package com.github.marceloleite2604.kafkaenvironment.producer.reader.address;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.AddressesRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.reader.BufferedItemStreamReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class AddressStreamReader extends BufferedItemStreamReader<Address> {
  public AddressStreamReader(
      AddressesRetrievalJobProperties addressRetrievalJobProperties,
      AddressBuffer addressBuffer) {
    super(addressRetrievalJobProperties, addressBuffer);
  }
}
