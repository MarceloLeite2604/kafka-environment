package com.github.marceloleite2604.kafkaenvironment.producer.step.address.reader;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.step.reader.BufferedItemStreamReader;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.AddressesRetrievalStepProperties;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class AddressStreamReader extends BufferedItemStreamReader<Address> {
  public AddressStreamReader(
      AddressesRetrievalStepProperties addressRetrievalJobProperties,
      AddressBuffer addressBuffer) {
    super(addressRetrievalJobProperties, addressBuffer);
  }
}
