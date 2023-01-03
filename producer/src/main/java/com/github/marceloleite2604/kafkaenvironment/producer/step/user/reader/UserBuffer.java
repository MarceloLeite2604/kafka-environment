package com.github.marceloleite2604.kafkaenvironment.producer.step.user.reader;

import java.net.URI;

import com.github.marceloleite2604.kafkaenvironment.producer.domain.RandomUserResponse;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.address.Address;
import com.github.marceloleite2604.kafkaenvironment.producer.domain.user.User;
import com.github.marceloleite2604.kafkaenvironment.producer.properties.UsersRetrievalJobProperties;
import com.github.marceloleite2604.kafkaenvironment.producer.step.reader.RandomUserItemBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

@Component
@Slf4j
public class UserBuffer extends RandomUserItemBuffer<User> {

  public UserBuffer(UsersRetrievalJobProperties usersRetrievalJobProperties, WebClient randomUserWebClient) {
    super(usersRetrievalJobProperties.getBufferedItems(), randomUserWebClient);
  }

  @Override
  protected URI buildUri(UriBuilder uriBuilder, int amount) {
    return uriBuilder.queryParam("results", amount)
        .queryParam("inc", "name,location,email")
        .build();
  }

  @Override
  protected ParameterizedTypeReference<RandomUserResponse<User>> retrieveParameterizedTypeReference() {
    return new ParameterizedTypeReference<>() {};
  }
}
