package com.github.marceloleite2604.kafkaenvironment.producer.service;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddressService {

  private final WebClient randomUserWebClient;

  public Iterable<Address> retrieve(int amount) {
    log.debug("Retrieving {} address(es) from randomuser.me.", amount);
    final var response = randomUserWebClient.get()
        .uri(uriBuilder -> uriBuilder.queryParam("results", amount)
            .queryParam("inc","location,")
            .build())
        .exchangeToMono(
            clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<RandomUserResponse<Address>>() {}))
        .blockOptional()
        .orElseThrow(() -> new IllegalStateException("Could not retrieve addresses from randomuser.me."));

    if (CollectionUtils.isEmpty(response.getResults())) {
      throw new IllegalStateException("Response from RandomUser.me is empty.");
    }

    return response.getResults();
  }
}
