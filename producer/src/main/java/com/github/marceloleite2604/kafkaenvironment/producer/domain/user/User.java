package com.github.marceloleite2604.kafkaenvironment.producer.domain.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {
  private final Name name;
  private final String email;
}
