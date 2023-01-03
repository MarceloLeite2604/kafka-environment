package com.github.marceloleite2604.kafkaenvironment.consumer.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@Jacksonized
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User {

  @Id
  private final String id;

  private final String firstName;

  private final String lastName;

  private final String email;
}
