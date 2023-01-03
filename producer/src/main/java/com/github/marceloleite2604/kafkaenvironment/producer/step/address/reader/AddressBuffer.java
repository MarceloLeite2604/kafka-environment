package com.github.marceloleite2604.kafkaenvironment.producer.step.address.reader;

import java.util.Iterator;
import java.util.List;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.AddressesRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.step.reader.ItemBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class AddressBuffer extends ItemBuffer<Address> {

  private final WebClient randomUserWebClient;

  public AddressBuffer(WebClient randomUserWebClient, AddressesRetrievalJobProperties addressesRetrievalJobProperties) {
    super(addressesRetrievalJobProperties.getBufferedItems());
    this.randomUserWebClient = randomUserWebClient;
  }

  @Override
  protected Iterator<Address> retrieveIterator(int amount) {
    return randomUserWebClient.get()
        .uri(uriBuilder -> uriBuilder.queryParam("results", amount)
            .queryParam("inc", "location,")
            .build())
        .exchangeToMono(
            clientResponse -> clientResponse.bodyToMono(
                new ParameterizedTypeReference<RandomUserResponse<Address>>() {}))
        .blockOptional()
        .map(RandomUserResponse::getResults)
        .map(List::iterator)
        .orElseThrow(() -> new IllegalStateException("Could not retrieve addresses from randomuser.me."));
  }
}
