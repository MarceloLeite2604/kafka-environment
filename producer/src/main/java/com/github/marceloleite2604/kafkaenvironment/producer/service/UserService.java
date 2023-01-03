package com.github.marceloleite2604.kafkaenvironment.producer.service;

import com.github.marceloleite2604.kafkaenvironment.producer.entity.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final WebClient randomUserWebClient;

  public Iterable<User> retrieve(int amount) {
    log.debug("Retrieving {} user(s) from randomuser.me.", amount);
    final var response = randomUserWebClient.get()
        .uri(uriBuilder -> uriBuilder.queryParam("results", amount)
            .build())
        .exchangeToMono(
            clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<RandomUserResponse<User>>() {}))
        .blockOptional()
        .orElseThrow(() -> new IllegalStateException("Could not retrieve users from randomuser.me."));

    if (CollectionUtils.isEmpty(response.getResults())) {
      throw new IllegalStateException("Response from RandomUser.me is empty.");
    }

    return response.getResults();
  }
}
