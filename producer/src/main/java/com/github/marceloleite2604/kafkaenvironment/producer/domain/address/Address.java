package com.github.marceloleite2604.kafkaenvironment.producer.domain.address;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Address {
  private final Street street;

  private final String city;

  private final String state;

  private final String country;
}
