package com.github.marceloleite2604.kafkaenvironment.producer.step.address.reader;

import java.net.URI;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.AddressesRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.step.reader.RandomUserItemBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Component
@Slf4j
public class AddressBuffer extends RandomUserItemBuffer<Address> {

  public AddressBuffer(AddressesRetrievalJobProperties addressesRetrievalJobProperties, WebClient randomUserWebClient) {
    super(addressesRetrievalJobProperties.getBufferedItems(), randomUserWebClient);
  }

  @Override
  protected URI buildUri(UriBuilder uriBuilder, int amount) {
    return uriBuilder.queryParam("results", amount)
        .queryParam("inc", "location")
        .build();
  }

  @Override
  protected ParameterizedTypeReference<RandomUserResponse<Address>> retrieveParameterizedTypeReference() {
    return new ParameterizedTypeReference<>() {};
  }
}
