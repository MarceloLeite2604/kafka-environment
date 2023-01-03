package com.github.marceloleite2604.kafkaenvironment.producer.step.user.reader;

import java.util.Iterator;
import java.util.List;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.step.reader.ItemBuffer;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalJobProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class UserBuffer extends ItemBuffer<User> {

  private final WebClient randomUserWebClient;

  public UserBuffer(WebClient randomUserWebClient, UsersRetrievalJobProperties usersRetrievalJobProperties) {
    super(usersRetrievalJobProperties.getBufferedItems());
    this.randomUserWebClient = randomUserWebClient;
  }

  @Override
  protected Iterator<User> retrieveIterator(int amount) {
    log.debug("Retrieving {} user(s) from randomuser.me.", amount);
    return randomUserWebClient.get()
        .uri(uriBuilder -> uriBuilder.queryParam("results", amount)
            .queryParam("inc", "name,location,email")
            .build())
        .exchangeToMono(
            clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<RandomUserResponse<User>>() {}))
        .blockOptional()
        .map(RandomUserResponse::getResults)
        .map(List::iterator)
        .orElseThrow(() -> new IllegalStateException("Could not retrieve users from randomuser.me."));
  }
}
